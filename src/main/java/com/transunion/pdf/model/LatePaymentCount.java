package com.transunion.pdf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LatePaymentCount {
    private int days1To30; // Represents payments that are 1 to 30 days late
    private int days31To60; // Represents payments that are 31 to 60 days late
    private int days61To90; // Represents payments that are 61 to 90 days late
    private int moreThan90Days; // Represents payments that are more than 90 days late
}

