package com.inference.nubyx.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDto {

    private String result;

    @JsonProperty("lead_code")
    private String leadCode;

}
