package com.transunion.pdf.enums;

import lombok.Getter;

@Getter
public enum SummaryIdType {
    TaxId("TAX ID"),
    PassportId("PASSPORT"),
    VoterId("VOTER ID"),
    RationCardId("RATION CARD"),
    DriversLicenseId("DRIVER'S LICENSE ID"),
    NregaId("NREGA CARD NUMBER"),
    CkycId("CKYC");

    private final String code;

    SummaryIdType(String code) {
        this.code = code;
    }

}