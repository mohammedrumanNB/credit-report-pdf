package com.transunion.pdf.dto;

import lombok.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnquiryTableInformation {
    private String enquiryYear;
    private String enquiryCount;
    private JRBeanCollectionDataSource enquiryTableDataSource;
    private boolean enquiryTableDetailsPresent;
}
