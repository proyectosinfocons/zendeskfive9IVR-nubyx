package com.inference.nubyx.integration.dto.responsezendesk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

    private String line1;
    private String city;

    @JsonProperty("postal_code")
    private String postalCode;
    private String state;
    private String country;
}
