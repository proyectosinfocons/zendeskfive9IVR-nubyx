package com.inference.nubyx.integration.controller;

import com.inference.nubyx.integration.client.ZendeskLeadClient;
import com.inference.nubyx.integration.dto.RequestDto;
import com.inference.nubyx.integration.dto.ResponseDto;
import com.inference.nubyx.integration.dto.responsezendesk.ResponseZendeskDto;
import com.inference.nubyx.integration.service.impl.IntegrationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zendeskfive9IVR")
@RequiredArgsConstructor
public class LeadController {

    private final ZendeskLeadClient leadClient;

    private final IntegrationServiceImpl integration;

    private static final Logger logger = LoggerFactory.getLogger(LeadController.class);

    @GetMapping("/lead")
    public ResponseEntity<ResponseZendeskDto> getLead(@RequestBody RequestDto request){
        logger.info("Called endpoint of NUBYX");
        return leadClient.getLeadZendesk(request.getId());
    }

    @PostMapping("/lead")
    public ResponseEntity<ResponseDto> createLead(@RequestBody RequestDto req){
        return integration.leadProcess(req);
    }

}
