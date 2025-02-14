package com.transunion.pdf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnquiryTableInformation {
    private String enquiryYear;
    private String enquiryCount;
    private JRBeanCollectionDataSource enquiryTableDataSource;
    private boolean enquiryTableDetailsPresent;
}
