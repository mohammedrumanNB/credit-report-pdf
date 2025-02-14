package com.transunion.pdf.model;

import com.transunion.pdf.util.CibilGraphUtil;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class IndexInfo {
    private String controlNumber;
    private int cibilScore;
    private String reportGeneratedDate; // Format should be like this: 11-11-2024
    private String cibilScoreDate; // Format should be like this: 12th Dec 2024
    private String cibilGraph;
    private String name;

    public IndexInfo(int cibilScore, String reportGeneratedDate, String cibilScoreDate, String controlNumber, String name) {
        this.cibilScore = cibilScore;
        this.controlNumber = controlNumber;
        this.reportGeneratedDate = reportGeneratedDate;
        this.cibilScoreDate = cibilScoreDate;
        this.name = name;
        this.cibilGraph = CibilGraphUtil.getCibilGraph(cibilScore);
    }

}

