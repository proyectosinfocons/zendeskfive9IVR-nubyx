package com.inference.nubyx.integration.controller;

import com.inference.nubyx.integration.client.ZendeskLeadClient;
import com.inference.nubyx.integration.dto.RequestDto;
import com.inference.nubyx.integration.dto.ResponseDto;
import com.inference.nubyx.integration.dto.responsezendesk.ResponseZendeskDto;
import com.inference.nubyx.integration.service.impl.IntegrationServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zendeskfive9IVR")
@RequiredArgsConstructor
@Slf4j
public class LeadController {

    private final ZendeskLeadClient leadClient;

    private final IntegrationServiceImpl integration;


    @GetMapping("/lead")
    public ResponseEntity<ResponseZendeskDto> getLead(@RequestBody RequestDto request){
        log.info(" \n------- GET  [METHOD] TO NUBYX------------- {}", request);
        return leadClient.getLeadZendesk(request.getId());
    }

    @PostMapping("/lead")
    public ResponseEntity<ResponseDto> createLead(@RequestBody RequestDto req){
        log.info(" \n------- POST  [METHOD] TO NUBYX------------- {}", req);
        return integration.leadProcess(req);
    }

}
