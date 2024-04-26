package com.inference.nubyx.integration.dto.responsezendesk;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetaLinkDto {
    private String type;
    private Long count;
    private LinkDto links;
}
