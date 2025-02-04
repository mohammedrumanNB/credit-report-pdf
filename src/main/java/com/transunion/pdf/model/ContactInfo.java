package com.transunion.pdf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo {
    private String contactType;
    private String contactNumber;
    private String residenceCode; //E.g., +91
}
