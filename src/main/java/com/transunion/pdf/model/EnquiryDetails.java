package com.transunion.pdf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EnquiryDetails {
    private String dateOfEnquiry;
    private String memberName;
    private String enquiryPurpose;
}
