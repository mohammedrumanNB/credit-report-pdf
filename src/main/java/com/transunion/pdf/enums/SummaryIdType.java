package com.transunion.pdf.enums;

import lombok.Getter;

@Getter
public enum SummaryIdType {
    PAN("PAN"),
    PASSPORT("PASSPORT"),
    VOTER_ID("VOTER ID"),
    RATION_CARD("RATION CARD"),
    DRIVERS_LICENSE("DRIVER'S LICENSE");

    private final String code;

    SummaryIdType(String code) {
        this.code = code;
    }

}
