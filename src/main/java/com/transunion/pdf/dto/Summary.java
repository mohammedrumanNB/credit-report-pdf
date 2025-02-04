package com.transunion.pdf.dto;

import com.transunion.pdf.model.LatePaymentRemarkCount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Summary {
    private int cibilScore;
    private String cibilGraph;
    private String name;
    private String dob;
    private String gender;
    private String idType;
    private String idNumber;
    private String email;
    private String mobileNumber;
    private String address;
    private int totalEnquiries;
    private int totalDisputes;
    private String currentBalance;
    private String amountOverdue;
    private int totalAccounts;
    private int closedCreditCards;
    private int closedLoans;
    private int openCreditCards;
    private int openLoans;
    private JRBeanCollectionDataSource pieChartDataSource;
    private boolean isLatePayment12;
    private JRBeanCollectionDataSource latePayment12DataSource;
    private boolean isLatePayment36;
    private JRBeanCollectionDataSource latePayment36DataSource;
    private LatePaymentRemarkCount latePaymentRemarkCount12;
    private LatePaymentRemarkCount latePaymentRemarkCount36;

}
