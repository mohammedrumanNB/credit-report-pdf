package com.transunion.pdf.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TwelveMonthLatePaymentInfo {
    private LatePaymentCount latePaymentCount;
    private LatePaymentRemarkCount latePaymentRemarkCount;
}
