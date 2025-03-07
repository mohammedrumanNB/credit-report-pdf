package com.transunion.pdf.model;

import lombok.*;
import static com.transunion.pdf.constant.ApplicationConstant.DEFAULT_HYPHEN;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationInfo {
    private String identificationType = DEFAULT_HYPHEN;
    private String idNumber = DEFAULT_HYPHEN;
    private String issueDate = DEFAULT_HYPHEN;
    private String expiryDate = DEFAULT_HYPHEN;
}
