package com.transunion.pdf.model;

import lombok.*;

import static com.transunion.pdf.constant.ApplicationConstant.DEFAULT_HYPHEN;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnquiryDetails {
    private String dateOfEnquiry = DEFAULT_HYPHEN;
    private String memberName = DEFAULT_HYPHEN;
    private String enquiryPurpose = DEFAULT_HYPHEN;
}
