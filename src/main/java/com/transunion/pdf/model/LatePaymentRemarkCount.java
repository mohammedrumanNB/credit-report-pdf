package com.transunion.pdf.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LatePaymentRemarkCount {
    private int subStandardRemark=0;
    private int specialMentionRemark=0;
    private int doubtfulRemark=0;
    private int lossRemark=0;
}
