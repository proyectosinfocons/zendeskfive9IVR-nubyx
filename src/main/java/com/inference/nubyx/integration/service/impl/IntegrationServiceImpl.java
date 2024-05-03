package com.inference.nubyx.integration.service.impl;

import com.inference.nubyx.integration.client.ZendeskLeadClient;
import com.inference.nubyx.integration.dto.RequestDto;
import com.inference.nubyx.integration.dto.ResponseDto;
import com.inference.nubyx.integration.dto.requestzendesk.DataReqDto;
import com.inference.nubyx.integration.dto.requestzendesk.RequestZendeskDto;
import com.inference.nubyx.integration.dto.responsezendesk.ItemDto;
import com.inference.nubyx.integration.dto.responsezendesk.ResponseZendeskDto;
import com.inference.nubyx.integration.service.IntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class IntegrationServiceImpl implements IntegrationService {

    private final ZendeskLeadClient zendeskLeadClient;

    private final Five9ServiceImpl five9Service;

    public ResponseEntity<ResponseDto> leadProcess(RequestDto request) {

        if (!validateId(request) || !validateTelephone(request) || !validateChannel(request)) {

            ResponseDto response = new ResponseDto();
            response.setLeadCode(null);
            if (!validateId(request)) {
                response.setResult("El documento es inválido");
            }
            if (!validateTelephone(request)) {
                response.setResult("El teléfono es inválido");
            }
            if (!validateChannel(request)) {
                response.setResult("El canal es inválido");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }


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
            log.info(" \n ========= SE REGISTRO EN ZENDESK ========= : \n {} ", responseZendesk.toString());

        } catch (Exception e) {
            log.error("registerZendesk:", e);
        }
        return item;
    }

    public ResponseDto createResponse(Boolean registered, ItemDto itemDto) {

        ResponseDto response = new ResponseDto();
        if (registered) {
            response.setResult("registered");
            response.setLeadCode(itemDto.getData().getId().toString());

        } else {
            response.setResult("unregistered");
            response.setLeadCode(null);
        }
        return response;
    }

    public Boolean validateId(RequestDto requestDto) {

        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(requestDto.getId());

        if (matcher.matches() && (requestDto.getId().length() == 8
                || requestDto.getId().length() == 9
                || requestDto.getId().length() == 11)) {
            return true;
        }
        return false;
    }

    public Boolean validateTelephone(RequestDto requestDto) {

        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(requestDto.getNumber());

        if (matcher.matches() && (requestDto.getNumber().length() == 7 ||
                requestDto.getNumber().length() == 9)) {
            return true;
        }
        return false;
    }

    public Boolean validateChannel(RequestDto requestDto) {
        var channel = requestDto.getChannel();
        if (!channel.equals("Voice IVR")) {
            return false;
        }
        return true;
    }
}
