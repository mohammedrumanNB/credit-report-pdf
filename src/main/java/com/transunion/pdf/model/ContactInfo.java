package com.transunion.pdf.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo {
    private String contactType;
    private String contactNumber;
    private String residenceCode; //E.g., +91
}
