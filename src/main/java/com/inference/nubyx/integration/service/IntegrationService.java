package com.inference.nubyx.integration.service;


import com.inference.nubyx.integration.dto.RequestDto;
import com.inference.nubyx.integration.dto.ResponseDto;
import com.inference.nubyx.integration.dto.requestzendesk.RequestZendeskDto;
import com.inference.nubyx.integration.dto.responsezendesk.ItemDto;
import com.inference.nubyx.integration.dto.responsezendesk.ResponseZendeskDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface IntegrationService {

    public ResponseEntity<ResponseDto> leadProcess(RequestDto req);

    public ResponseZendeskDto findLeadByDni(String nroDni);

    public Boolean dniExist(String nroDni, ResponseZendeskDto responseZendeskDto);

    public Boolean createdAtEqualsToday(LocalDateTime createAt);

    public ItemDto registerZendesk(RequestZendeskDto reqZendeskDto);

    public ResponseDto createResponse(Boolean registered, ItemDto itemDto);

}
