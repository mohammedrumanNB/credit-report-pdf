package com.transunion.pdf.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountSummary {
    private String srNo;
    private String memberName;
    private String accountType;
    private String status;
    private String dateOpened;
    private String dateReported;
}
