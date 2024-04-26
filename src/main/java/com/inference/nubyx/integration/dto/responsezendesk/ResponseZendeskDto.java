package com.inference.nubyx.integration.dto.responsezendesk;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseZendeskDto {
    private List<ItemDto> items;
    private MetaLinkDto meta;
}
