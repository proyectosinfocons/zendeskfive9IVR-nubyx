package com.inference.nubyx.integration.dto.responsezendesk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomFieldDto {

    @JsonProperty("Nro Documento")
    private String nroDocumento;
}
