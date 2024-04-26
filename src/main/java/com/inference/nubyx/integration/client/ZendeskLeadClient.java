package com.inference.nubyx.integration.client;

import com.inference.nubyx.integration.config.ZendeskLeadClientConfig;
import com.inference.nubyx.integration.dto.requestzendesk.RequestZendeskDto;
import com.inference.nubyx.integration.dto.responsezendesk.ItemDto;
import com.inference.nubyx.integration.dto.responsezendesk.ResponseZendeskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "zendesk-lead-client", url = "https://api.getbase.com/v2", configuration = ZendeskLeadClientConfig.class)
public interface ZendeskLeadClient {

    @GetMapping("/leads")
    ResponseEntity<ResponseZendeskDto> getLeadZendesk(@RequestParam(value = "custom_fields%5BNro%20Documento%5D") String nroDocumento);


    @PostMapping("/leads")
    ResponseEntity<ItemDto> postLeadZendesk(@RequestBody RequestZendeskDto req);
}
