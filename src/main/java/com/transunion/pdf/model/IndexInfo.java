package com.transunion.pdf.model;

import com.transunion.pdf.constant.ApplicationConstant;
import com.transunion.pdf.util.CibilGraphUtil;
import com.transunion.pdf.util.CommonUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;


@NoArgsConstructor
@Data
    public class IndexInfo {
    private String controlNumber;
    private int cibilScore;
    private String reportGeneratedDate; // Format should be like this: 11-11-2024
    private String cibilScoreDate; // Format should be like this: 12th Dec 2024
    private String cibilGraph;
    private String name;

    public IndexInfo(int cibilScore, String reportGeneratedDate,String cibilScoreDate, String controlNumber, String name) {
        this.cibilScore = cibilScore;
        this.controlNumber = controlNumber;
        this.reportGeneratedDate = reportGeneratedDate;
        this.cibilScoreDate=cibilScoreDate;
        this.name=name;
        this.cibilGraph = CibilGraphUtil.getCibilGraph(cibilScore);
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("d'th' MMM yyyy");
        String formattedDate = sdf.format(date);

        // Adjust "1th", "2th", "3th" for proper ordinal suffixes
        if (formattedDate.startsWith("1th")) {
            formattedDate = formattedDate.replace("1th", "1st");
        } else if (formattedDate.startsWith("2th")) {
            formattedDate = formattedDate.replace("2th", "2nd");
        } else if (formattedDate.startsWith("3th")) {
            formattedDate = formattedDate.replace("3th", "3rd");
        }

        return formattedDate;
    }
}

