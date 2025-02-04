package com.transunion.pdf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnquiryInformation {
    private String controlNumber;
    private String reportGeneratedDate;
    private boolean enquiryInfoPresent;
    private int totalEnquiries;
    private JRBeanCollectionDataSource enquiryInfoDataSource;
    private JasperReport enquiryTableReport;
}
