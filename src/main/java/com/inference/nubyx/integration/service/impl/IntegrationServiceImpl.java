package com.inference.nubyx.integration.service.impl;

import com.inference.nubyx.integration.client.ZendeskLeadClient;
import com.inference.nubyx.integration.controller.LeadController;
import com.inference.nubyx.integration.dto.RequestDto;
import com.inference.nubyx.integration.dto.ResponseDto;
import com.inference.nubyx.integration.dto.requestzendesk.DataReqDto;
import com.inference.nubyx.integration.dto.requestzendesk.RequestZendeskDto;
import com.inference.nubyx.integration.dto.responsezendesk.ItemDto;
import com.inference.nubyx.integration.dto.responsezendesk.ResponseZendeskDto;
import com.inference.nubyx.integration.service.IntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class IntegrationServiceImpl implements IntegrationService {

    private static final Logger logger = LoggerFactory.getLogger(LeadController.class);
    private final ZendeskLeadClient zendeskLeadClient;

    private final Five9ServiceImpl five9Service;

    public ResponseEntity<ResponseDto> leadProcess(RequestDto request) {

        ResponseDto responseDto;
        var lead = findLeadByDni(request.getId());

        if (dniExist(request.getId(), lead)) {

            Collections.sort(lead.getItems(), (item1, item2) -> {
                        return item2.getData().getCreatedAt().compareTo(item1.getData().getCreatedAt());
                    }
            );

            var lastItem = lead.getItems().get(0);
            if (createdAtEqualsToday(lastItem.getData().getCreatedAt())) {
                responseDto = createResponse(false, null);
            } else {
                RequestZendeskDto requestZendeskDto = new RequestZendeskDto();
                requestZendeskDto.setData(DataReqDto.getData(null, request.getId()));
                var item = registerZendesk(requestZendeskDto);

                if (item == null) {
                    responseDto = createResponse(false, null);
                } else {
                    five9Service.registerLead(request, item);
                    responseDto = createResponse(true, item);
                }
            }

        } else {
            RequestZendeskDto requestZendeskDto = new RequestZendeskDto();
            requestZendeskDto.setData(DataReqDto.getData(null, request.getId()));
            var item = registerZendesk(requestZendeskDto);

            if (item == null) {
                responseDto = createResponse(false, null);
            } else {

                five9Service.registerLead(request, item);
                responseDto = createResponse(true, item);
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    public ResponseZendeskDto findLeadByDni(String nroDni) {
        return zendeskLeadClient.getLeadZendesk(nroDni).getBody(); //sacamos el cuerpo de la Respuesta
    }

    public Boolean dniExist(String nroDni, ResponseZendeskDto responseZendeskDto) {
        var existDni = false;
        var items = responseZendeskDto.getItems();

        if (items.isEmpty()) {
            existDni = false;
        } else {
            var itemFilter = items.stream()
                    .filter(itemDto -> Objects.equals(itemDto.getData().getCustomFields().getNroDocumento(), nroDni))
                    .collect(Collectors.toList());

            if (itemFilter.isEmpty()) {
                existDni = false;
            } else {
                existDni = true;
            }
        }
        return existDni;
    }

    public Boolean createdAtEqualsToday(LocalDateTime createAt) {

        LocalDateTime createAtLocal = createAt.minusHours(5);

        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("createAtLocal {}, localDateTime {}", createAtLocal, localDateTime);

        if (createAtLocal.toLocalDate().equals(localDateTime.toLocalDate())) {
            return true;
        }
        return false;
    }

    public ItemDto registerZendesk(RequestZendeskDto reqZendeskDto) {

        ItemDto item = null;
        try {
            var responseZendesk = zendeskLeadClient.postLeadZendesk(reqZendeskDto);
            item = responseZendesk.getBody();
        } catch (Exception e) {
            logger.error("registerZendesk:", e);
        }
        return item;
    }

    public ResponseDto createResponse(Boolean registered, ItemDto itemDto) {

        if (registered) {
            return ResponseDto.builder()
                    .result("registered")
                    .leadCode(itemDto.getData().getId().toString())
                    .build();
        } else {
            return ResponseDto.builder()
                    .result("unregistered")
                    .leadCode(null)
                    .build();
        }
    }
}
