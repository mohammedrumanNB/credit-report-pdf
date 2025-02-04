package com.transunion.pdf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationInfo {
    private String identificationType;
    private String idNumber;
    private String issueDate;
    private String expiryDate;
}
