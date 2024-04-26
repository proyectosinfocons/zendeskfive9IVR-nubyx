package com.inference.nubyx.integration.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.inference.nubyx.integration.client.Five9Client;
import com.inference.nubyx.integration.dto.RequestDto;
import com.inference.nubyx.integration.dto.responsezendesk.ItemDto;
import com.inference.nubyx.integration.mapper.request.Envelope;
import com.inference.nubyx.integration.service.Five9Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Five9ServiceImpl implements Five9Service {

    private final Five9Client five9Client;

    @Value("${client.prefix.environment}")
    private Integer environment;

    public ResponseEntity<?> registerLead(RequestDto requestDto, ItemDto itemDto) {
        ObjectMapper objectMapper = new ObjectMapper();

        List<String> fields = List.of(itemDto.getData().getId().toString(),
                requestDto.getId(),
                addPrefix(requestDto.getNumber()),
                "Lead Web",
                "Lead Web"
                );

        Envelope envelope = Envelope.create(fields);

        XmlMapper xmlMapper = new XmlMapper();

        String xmlEnvelop = null;
        try {
            xmlEnvelop = xmlMapper.writeValueAsString(envelope);
            log.info("MI XML{}", xmlEnvelop);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(five9Client.postLeadFive9(envelope).getBody());
    }

    public String addPrefixLab(String numTelephone) {
        if (numTelephone.length() == 9) {
            return "01151".concat(numTelephone);
        } else if (numTelephone.length() == 7) {
            return "011511".concat(numTelephone);
        } else {
            return numTelephone;
        }
    }

    public String addPrefixProd(String numTelephone) {
        if (numTelephone.length() == 9) {
            return "51".concat(numTelephone);
        } else if (numTelephone.length() == 7) {
            return "511".concat(numTelephone);
        } else {
            return numTelephone;
        }
    }

    public String addPrefix(String numTelephone) {
        if (environment == 0) {
            return addPrefixLab(numTelephone);
        } else if (environment == 1) {
            return addPrefixProd(numTelephone);
        } else {
            return numTelephone;
        }
    }
}
