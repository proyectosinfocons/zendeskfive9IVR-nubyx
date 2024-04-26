package com.inference.nubyx.integration.client;

import com.inference.nubyx.integration.config.Five9CsvClientConfig;
import com.inference.nubyx.integration.mapper.request.Envelope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "five9-lead-client", url = "https://api.five9.com/wsadmin", configuration = Five9CsvClientConfig.class)
public interface Five9Client {

    @PostMapping(value = "/addToListCsv",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<?> postLeadFive9(@RequestBody Envelope envelope);

}
