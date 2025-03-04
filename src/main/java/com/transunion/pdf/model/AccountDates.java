package com.transunion.pdf.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDates {
    private String dateOpenedOrDisbursed;
    private String dateLastPayment;
    private String dateClosed;
    private String dateReportedAndCertified;
}
