package com.transunion.pdf.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LatePaymentRemarkCount {
    private int subStandardRemark;
    private int specialMentionRemark;
    private int doubtfulRemark;
    private int lossRemark;
}
