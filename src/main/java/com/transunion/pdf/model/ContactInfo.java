package com.transunion.pdf.model;

import lombok.*;

import static com.transunion.pdf.constant.ApplicationConstant.DEFAULT_HYPHEN;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo {
    private String contactType = DEFAULT_HYPHEN;
    private String contactNumber = DEFAULT_HYPHEN;
    private String residenceCode = DEFAULT_HYPHEN; //E.g., +91
}
