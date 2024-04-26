package com.inference.nubyx.integration.dto.responsezendesk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class DataDto {
    private Long id;
    @JsonProperty("owner_id")
    private Long ownerId;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("unqualified_reason_id")
    private String unqualifiedReasonId;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    private String twitter;
    private String phone;
    private String mobile;
    private String facebook;
    private String email;
    private String title;
    private String skype;
    private String linkedin;
    private String description;
    private String industry;
    private String fax;
    private String website;

    private AddressDto address;

    private String status;

    @JsonProperty("creator_id")
    private Long creatorId;

    @JsonProperty("organization_name")
    private String organizationName;

    private List<String> tags;

    @JsonProperty("custom_fields")
    private CustomFieldDto customFields;
}
