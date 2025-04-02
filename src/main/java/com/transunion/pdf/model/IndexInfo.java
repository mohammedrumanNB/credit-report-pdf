package com.transunion.pdf.model;

import lombok.*;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class IndexInfo {
    private String controlNumber;
    private int cibilScore;
    private String reportGeneratedDate;
    private String cibilScoreDate;
    private String name;
    private String coverPageName;

    public IndexInfo(int cibilScore, String reportGeneratedDate, String cibilScoreDate, String controlNumber, String name, String coverPageName) {
        this.cibilScore = cibilScore;
        this.controlNumber = controlNumber;
        this.reportGeneratedDate = reportGeneratedDate;
        this.cibilScoreDate = cibilScoreDate;
        this.name = name;
        this.coverPageName=coverPageName;
    }

}

