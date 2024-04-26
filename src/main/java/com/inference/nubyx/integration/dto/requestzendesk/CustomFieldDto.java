package com.inference.nubyx.integration.dto.requestzendesk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomFieldDto {

    @JsonProperty("Nro Documento")
    private String nroDocument;

    @JsonProperty("Tipo Documento")
    private String documentType;
}
