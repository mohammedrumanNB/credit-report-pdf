package com.transunion.pdf.model;

import lombok.*;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import static com.transunion.pdf.constant.ApplicationConstant.DEFAULT_HYPHEN;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountInfo {
    private String srNo;
    private String accountType;
    private String bankName;
    private String accountNumber;
    private String ownerShipType;
    private boolean accountUnderDispute;
    private DisputeInfo disputeInfo;
    private String accountStatus;

    private String creditLimit;
    private String highCredit;
    private String highCreditOrAmountSanctionLabel;
    private String currentBalance;
    private String cashLimit;
    private String amountOverdue;
    private String rateOfInterest;
    private String repaymentTenure;
    private String emiAmount;
    private String paymentFrequency;
    private String actualPaymentAmount;

    private String dateOpenedOrDisbursed;
    private String dateLastPayment;
    private String dateClosed;
    private String dateReportedAndCertified;

    private String paymentStartDate;
    private String paymentEndDate;

    private JRBeanCollectionDataSource pastDueMonthlyDataSource;
    private JasperReport pastDueMonthlyReport;

    private String valueOfCollateral;
    private String typeOfCollateral;
    private String suitFiledOrWillfulDefault;
    private String creditFacilityStatus;
    private String writtenOffAmountTotal;
    private String writtenOffAmountPrincipal;
    private String settlementAmount;


}
