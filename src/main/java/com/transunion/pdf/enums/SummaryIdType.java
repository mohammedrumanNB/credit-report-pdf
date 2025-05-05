package com.transunion.pdf.enums;

import lombok.Getter;

@Getter
public enum SummaryIdType {
    TAX_ID("TAX ID"),
    PASSPORT_ID("PASSPORT"),
    VOTER_ID("VOTER ID"),
    RATION_CARD_ID("RATION CARD"),
    DRIVERS_LICENSE_ID("DRIVER'S LICENSE ID"),
    NREGA_ID("NREGA CARD NUMBER"),
    CKYC_ID("CKYC");

    private final String code;

    SummaryIdType(String code) {
        this.code = code;
    }

}