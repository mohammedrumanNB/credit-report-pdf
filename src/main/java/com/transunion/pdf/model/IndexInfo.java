package com.transunion.pdf.model;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class IndexInfo {
    private String controlNumber;
    private int cibilScore;
    private String reportGeneratedDate;
    private String cibilScoreDate;
    private String name;

    public IndexInfo(int cibilScore, String reportGeneratedDate, String cibilScoreDate, String controlNumber, String name) {
        this.cibilScore = cibilScore;
        this.controlNumber = controlNumber;
        this.reportGeneratedDate = reportGeneratedDate;
        this.cibilScoreDate = cibilScoreDate;
        this.name = name;
    }

}

