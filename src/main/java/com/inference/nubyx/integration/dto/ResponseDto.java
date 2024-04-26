package com.inference.nubyx.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDto {

    private String result;

    @JsonProperty("lead_code")
    private String leadCode;

}
