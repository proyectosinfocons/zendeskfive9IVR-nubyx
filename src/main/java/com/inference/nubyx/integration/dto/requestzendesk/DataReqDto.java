package com.inference.nubyx.integration.dto.requestzendesk;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inference.nubyx.integration.constant.DataLead;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataReqDto {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String description;
    private String phone;
    private String mobile;
    private String status;
    private List<String> tags;

    @JsonProperty("custom_fields")
    private CustomFieldDto customField;

    public static DataReqDto getData(Long leadCode, String nroDocument) {
        DataReqDto data = new DataReqDto();
        data.setFirstName(DataLead.firstName);
        data.setLastName(DataLead.lastName);
        data.setDescription(DataLead.description);

        if (leadCode != null) {
            if (leadCode.toString().length() == 7) {
                data.setPhone(leadCode.toString());
            }

            if (leadCode.toString().length() == 9) {
                data.setMobile(leadCode.toString());
            }
        }

        data.setStatus(DataLead.status);
        data.setTags(List.of(DataLead.tag));

        CustomFieldDto customField = new CustomFieldDto();
        customField.setNroDocument(nroDocument);
        if (nroDocument.length() == 8) {
            customField.setDocumentType("DNI");
        } else if (nroDocument.length() == 9) {
            customField.setDocumentType("CARNET/PASAPORTE");
        } else if (nroDocument.length() == 11) {
            customField.setDocumentType("RUC");
        }
        data.setCustomField(customField);
        return data;
    }
}
