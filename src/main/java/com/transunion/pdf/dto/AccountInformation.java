package com.transunion.pdf.dto;

import lombok.*;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
