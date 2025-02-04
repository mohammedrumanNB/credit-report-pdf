package com.transunion.pdf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
