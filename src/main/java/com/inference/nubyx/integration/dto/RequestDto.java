package com.inference.nubyx.integration.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestDto {

    @ApiModelProperty(position = 0, example = "80928031")
    private String id;
    @ApiModelProperty(position = 1, example = "985049091")
    private String number;
    @ApiModelProperty(position = 2, example = "Voice Web")
    private String channel;
}
