package com.transunion.pdf.dto;

import lombok.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressInformation {
    private JRBeanCollectionDataSource addressTableDataSource;
    private boolean addressEmpty;
}
