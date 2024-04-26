package com.inference.nubyx.integration.dto.requestzendesk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagsDto {

    @JsonProperty("web lead")
    private String webLead;
}
