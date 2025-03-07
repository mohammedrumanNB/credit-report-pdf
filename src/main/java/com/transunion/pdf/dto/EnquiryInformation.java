package com.transunion.pdf.dto;

import com.transunion.pdf.model.DisputeInfo;
import lombok.*;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnquiryInformation {
    private String controlNumber;
    private String reportGeneratedDate;
    private boolean enquiryInfoPresent;
    private int totalEnquiries;
    private JRBeanCollectionDataSource enquiryInfoDataSource;
    private JasperReport enquiryTableReport;
    private boolean enquiryInfoDisputePresent;
    private DisputeInfo enquiryInfoDisputeInfo;
}
