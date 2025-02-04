package com.transunion.pdf.dto;

import com.transunion.pdf.model.AddressInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressInformation {
    private JRBeanCollectionDataSource addressTableDataSource;
    private boolean addressEmpty;
}
