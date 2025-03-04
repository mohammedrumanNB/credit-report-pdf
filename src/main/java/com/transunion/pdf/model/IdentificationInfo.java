package com.transunion.pdf.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationInfo {
    private String identificationType;
    private String idNumber;
    private String issueDate;
    private String expiryDate;
}
