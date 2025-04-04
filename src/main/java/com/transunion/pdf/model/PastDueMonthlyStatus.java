package com.transunion.pdf.model;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PastDueMonthlyStatus {
    private int year;
    private String janDue;
    private String febDue;
    private String marDue;
    private String aprDue;
    private String mayDue;
    private String junDue;
    private String julDue;
    private String augDue;
    private String sepDue;
    private String octDue;
    private String novDue;
    private String decDue;
}
