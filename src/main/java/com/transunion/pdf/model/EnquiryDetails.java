package com.transunion.pdf.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnquiryDetails {
    private String dateOfEnquiry;
    private String memberName;
    private String enquiryPurpose;
}
