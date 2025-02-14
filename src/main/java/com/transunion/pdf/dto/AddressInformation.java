package com.transunion.pdf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressInformation {
    private JRBeanCollectionDataSource addressTableDataSource;
    private boolean addressEmpty;
}
