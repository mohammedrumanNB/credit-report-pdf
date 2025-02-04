package com.transunion.pdf.dto;

import com.transunion.pdf.model.EnquiryDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnquiryTableInformation {
    private String enquiryYear;
    private String enquiryCount;
    private JRBeanCollectionDataSource enquiryTableDataSource;
    private boolean enquiryTableDetailsPresent;
}
