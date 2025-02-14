package com.transunion.pdf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInformation {
    private String controlNumber;
    private String reportGeneratedDate;
    private boolean accountInformationPresent;
    private JRBeanCollectionDataSource accountSummaryDataSource;
    private boolean openAccountInformationPresent;
    private JasperReport openAccountReport;
    private JRBeanCollectionDataSource openAccountInfoDataSource;

    private boolean closedAccountInformationPresent;
    private JasperReport closedAccountReport;
    private JRBeanCollectionDataSource closedAccountInfoDataSource;
}
