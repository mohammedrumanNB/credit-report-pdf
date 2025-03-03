package com.transunion.pdf.service;

import com.transunion.pdf.constant.ApplicationConstant;
import com.transunion.pdf.dto.AccountInformation;
import com.transunion.pdf.dto.PDFData;
import com.transunion.pdf.enums.PdfVersion;
import com.transunion.pdf.exception.FileNotFoundException;
import com.transunion.pdf.model.*;
import com.transunion.pdf.util.CommonUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountInfoReportService {
    public JasperReport getAccountInfoReport(PdfVersion pdfVersion) throws JRException {
        String filePath;
        switch (pdfVersion) {
            case INDIRECT:
                //Get AccountInfo Report jrxml and compile it
                filePath = ApplicationConstant.INDIRECT_ACCOUNTINFO_JASPER_PATH;
                break;
            case NH:
                //Get AccountInfo Report jrxml and compile it
                filePath = ApplicationConstant.NH_ACCOUNTINFO_JASPER_PATH;
                break;
            case STARTER:
                //Get AccountInfo Report jrxml and compile it
                filePath = ApplicationConstant.STARTER_ACCOUNTINFO_JASPER_PATH;
                break;
            case PAID:
                //Get AccountInfo Report jrxml and compile it
                filePath = ApplicationConstant.PAID_ACCOUNTINFO_JASPER_PATH;
                break;
            default:
                throw new IllegalArgumentException("Unsupported PDF version: " + pdfVersion);
        }
        return (JasperReport) JRLoader.loadObject(new File(filePath));

    }

    public AccountInformation getAccountInformationParam(PDFData pdfData,PdfVersion pdfVersion) {
        AccountInformation accountInformation = new AccountInformation();

        accountInformation.setControlNumber(pdfData.getControlNumber());
        accountInformation.setReportGeneratedDate(pdfData.getReportGeneratedDate());


        List<OpenAccountInfo> openAccountInfoList = pdfData.getOpenAccountInfoList();
        List<ClosedAccountInfo> closedAccountInfoList = pdfData.getClosedAccountInfoList();

        boolean hasOpenAccounts = openAccountInfoList != null && !openAccountInfoList.isEmpty();
        boolean hasClosedAccounts = closedAccountInfoList != null && !closedAccountInfoList.isEmpty();

        if (!hasOpenAccounts && !hasClosedAccounts) {
            accountInformation.setAccountInformationPresent(false);
        } else {
            accountInformation.setAccountInformationPresent(true);
            assert openAccountInfoList != null;
            accountInformation.setAccountSummaryDataSource(getAccountSummaryDataSource(openAccountInfoList, closedAccountInfoList));

            if (hasOpenAccounts) {
                accountInformation.setOpenAccountInformationPresent(true);
                accountInformation.setOpenAccountReport(getOpenAccountReport(pdfVersion));
                accountInformation.setOpenAccountInfoDataSource(new JRBeanCollectionDataSource(getOpenAccountInformation(openAccountInfoList)));
            }

            if (hasClosedAccounts) {
                accountInformation.setClosedAccountInformationPresent(true);
                accountInformation.setClosedAccountReport(getClosedAccountReport(pdfVersion));
                accountInformation.setClosedAccountInfoDataSource(new JRBeanCollectionDataSource(getClosedAccountInformation(closedAccountInfoList, openAccountInfoList.size())));
            }
        }

        return accountInformation;
    }

    private JRBeanCollectionDataSource getAccountSummaryDataSource(List<OpenAccountInfo> openAccountInfoList, List<ClosedAccountInfo> closedAccountInfoList) {
        List<AccountSummary> accountSummaryList = new ArrayList<>();
        int i = 1;
        for (OpenAccountInfo openAccountInfo : openAccountInfoList) {
            AccountSummary accountSummary = AccountSummary.builder()
                    .srNo(String.format("%02d", i))
                    .memberName(openAccountInfo.getAccountDetails().getBankName())
                    .accountType(openAccountInfo.getAccountDetails().getAccountType())
                    .status(openAccountInfo.getAccountDetails().getAccountOpenOrClosed())
                    .dateOpened(openAccountInfo.getAccountDates().getDateOpenedOrDisbursed())
                    .dateReported(openAccountInfo.getAccountDates().getDateReportedAndCertified())
                    .build();
            accountSummaryList.add(accountSummary);
            i++;
        }

        for (ClosedAccountInfo closedAccountInfo : closedAccountInfoList) {
            AccountSummary accountSummary = AccountSummary.builder()
                    .srNo(String.format("%02d", i))
                    .memberName(closedAccountInfo.getAccountDetails().getBankName())
                    .accountType(closedAccountInfo.getAccountDetails().getAccountType())
                    .status(closedAccountInfo.getAccountDetails().getAccountOpenOrClosed())
                    .dateOpened(closedAccountInfo.getAccountDates().getDateOpenedOrDisbursed())
                    .dateReported(closedAccountInfo.getAccountDates().getDateReportedAndCertified())
                    .build();
            accountSummaryList.add(accountSummary);
            i++;
        }

        return new JRBeanCollectionDataSource(accountSummaryList);
    }

    private List<AccountInfo> getClosedAccountInformation(List<ClosedAccountInfo> closedAccountInfoList, int size) {
        List<AccountInfo> accountInfoList = new ArrayList<>();
        int i = size + 1;
        for (ClosedAccountInfo closedAccountInfo : closedAccountInfoList) {
            AccountInfo accountInfoClosed = getAccountInfoClosed(closedAccountInfo);
            accountInfoClosed.setSrNo(String.format("%02d", i));
            i++;
            accountInfoList.add(accountInfoClosed);
        }

        return accountInfoList;

    }

    private AccountInfo getAccountInfoClosed(ClosedAccountInfo closedAccountInfo) {

        AccountDetails accountDetails = closedAccountInfo.getAccountDetails();
        AccountDates accountDates = closedAccountInfo.getAccountDates();

        //Validate the obj
        CommonUtil.validateAccountDetails(accountDetails);
        CommonUtil.validateAccountDates(accountDates);
        if (accountDetails.isAccountUnderDispute()) {
            CommonUtil.validateDisputeInfo(accountDetails.getDisputeInfo());
        }

        return AccountInfo.builder()
                //Account Details
                .accountType(accountDetails.getAccountType())
                .bankName(accountDetails.getBankName())
                .accountNumber(accountDetails.getAccountNumber())
                .ownerShipType(accountDetails.getOwnerShipType())
                .accountUnderDispute(accountDetails.isAccountUnderDispute())
                .disputeInfo(accountDetails.isAccountUnderDispute() ? accountDetails.getDisputeInfo() : null)
                .accountStatus(accountDetails.getAccountStatus())
                .creditLimit(CommonUtil.formatIndianCurrency(accountDetails.getCreditLimit()))
                .highCredit(CommonUtil.formatIndianCurrency(accountDetails.getHighCredit()))
                .currentBalance(CommonUtil.formatIndianCurrency(accountDetails.getCurrentBalance()))
                .cashLimit(CommonUtil.formatIndianCurrency(accountDetails.getCashLimit()))
                .amountOverdue(CommonUtil.formatIndianCurrency(accountDetails.getAmountOverdue()))
                .rateOfInterest(accountDetails.getRateOfInterest())
                .repaymentTenure(accountDetails.getRepaymentTenure())
                .emiAmount(CommonUtil.formatIndianCurrency(accountDetails.getEmiAmount()))
                .paymentFrequency(accountDetails.getPaymentFrequency())
                .actualPaymentAmount(CommonUtil.formatIndianCurrency(accountDetails.getActualPaymentAmount()))
                //Account Dates
                .dateOpenedOrDisbursed(accountDates.getDateOpenedOrDisbursed())
                .dateLastPayment(accountDates.getDateLastPayment())
                .dateClosed(accountDates.getDateClosed())
                .dateReportedAndCertified(accountDates.getDateReportedAndCertified())

                .paymentStartDate(closedAccountInfo.getPaymentStartDate())
                .paymentEndDate(closedAccountInfo.getPaymentEndDate())

                .pastDueMonthlyDataSource(getPastDueMontlyDataSource(closedAccountInfo.getPastDueMonthlyStatusList()))
                .pastDueMonthlyReport(getPastDueMonthlyReport())

                .valueOfCollateral(CommonUtil.formatIndianCurrency(closedAccountInfo.getValueOfCollateral()))
                .typeOfCollateral(closedAccountInfo.getTypeOfCollateral())
                .suitFiledOrWillfulDefault(closedAccountInfo.getSuitFiledOrWillfulDefault())
                .creditFacilityStatus(closedAccountInfo.getCreditFacilityStatus())
                .writtenOffAmountTotal(CommonUtil.formatIndianCurrency(closedAccountInfo.getWrittenOffAmountTotal()))
                .writtenOffAmountPrincipal(CommonUtil.formatIndianCurrency(closedAccountInfo.getWrittenOffAmountPrincipal()))
                .settlementAmount(CommonUtil.formatIndianCurrency(closedAccountInfo.getSettlementAmount()))
                .build();
    }

    private JasperReport getClosedAccountReport(PdfVersion pdfVersion) {
        try {
            if(pdfVersion.equals(PdfVersion.STARTER) || pdfVersion.equals(PdfVersion.PAID)){
                return (JasperReport) JRLoader.loadObject(new File(ApplicationConstant.CLOSED_ACCOUNT_JASPER_PATH_DIRECT));

            }
            return (JasperReport) JRLoader.loadObject(new File(ApplicationConstant.CLOSED_ACCOUNT_JASPER_PATH_INDIRECT));

        } catch (JRException e) {
            // Check if the cause of JRException is a FileNotFoundException
            if (e.getCause() instanceof java.io.FileNotFoundException) {
                throw new FileNotFoundException(1029, "Closed Account Jrxml / Jasper Report Not Found at this directory: "
                        + ApplicationConstant.CLOSED_ACCOUNT_JASPER_PATH_INDIRECT);
            } else {
                // Re-throw JRException as a runtime exception if the cause is different
                throw new RuntimeException("An error occurred while compiling the address report.", e);
            }
        }
    }


    private JasperReport getOpenAccountReport(PdfVersion pdfVersion) {
        try {
            if(pdfVersion.equals(PdfVersion.STARTER) || pdfVersion.equals(PdfVersion.PAID)){
                return (JasperReport) JRLoader.loadObject(new File(ApplicationConstant.OPEN_ACCOUNT_JASPER_PATH_DIRECT));
            }
            return (JasperReport) JRLoader.loadObject(new File(ApplicationConstant.OPEN_ACCOUNT_JASPER_PATH_INDIRECT));

        } catch (JRException e) {
            // Check if the cause of JRException is a FileNotFoundException
            if (e.getCause() instanceof java.io.FileNotFoundException) {
                throw new FileNotFoundException(1028, "Open Account Jrxml / Jasper Report Not Found at this directory: "
                        + ApplicationConstant.OPEN_ACCOUNT_JASPER_PATH_INDIRECT);
            } else {
                // Re-throw JRException as a runtime exception if the cause is different
                throw new RuntimeException("An error occurred while compiling the address report.", e);
            }
        }
    }

    private List<AccountInfo> getOpenAccountInformation(List<OpenAccountInfo> openAccountInfoList) {
        List<AccountInfo> accountInfoList = new ArrayList<>();
        int i = 1;
        for (OpenAccountInfo openAccountInfo : openAccountInfoList) {
            AccountInfo accountInfoOpen = getAccountInfoOpen(openAccountInfo);
            accountInfoOpen.setSrNo(String.format("%02d", i));
            accountInfoList.add(accountInfoOpen);
            i++;
        }

        return accountInfoList;
    }

    private AccountInfo getAccountInfoOpen(OpenAccountInfo openAccountInfo) {
        AccountDetails accountDetails = openAccountInfo.getAccountDetails();
        AccountDates accountDates = openAccountInfo.getAccountDates();

        //Validate the obj
        CommonUtil.validateAccountDetails(accountDetails);
        CommonUtil.validateAccountDates(accountDates);
        if (accountDetails.isAccountUnderDispute()) {
            CommonUtil.validateDisputeInfo(accountDetails.getDisputeInfo());
        }

        return AccountInfo.builder()
                //Account Details
                .accountType(accountDetails.getAccountType())
                .bankName(accountDetails.getBankName())
                .accountNumber(accountDetails.getAccountNumber())
                .ownerShipType(accountDetails.getOwnerShipType())
                .accountUnderDispute(accountDetails.isAccountUnderDispute())
                .disputeInfo(accountDetails.isAccountUnderDispute() ? accountDetails.getDisputeInfo() : null)
                .accountStatus(accountDetails.getAccountStatus())
                .creditLimit(CommonUtil.formatIndianCurrency(accountDetails.getCreditLimit()))
                .highCredit(CommonUtil.formatIndianCurrency(accountDetails.getHighCredit()))
                .currentBalance(CommonUtil.formatIndianCurrency(accountDetails.getCurrentBalance()))
                .cashLimit(CommonUtil.formatIndianCurrency(accountDetails.getCashLimit()))
                .amountOverdue(CommonUtil.formatIndianCurrency(accountDetails.getAmountOverdue()))
                .rateOfInterest(accountDetails.getRateOfInterest())
                .repaymentTenure(accountDetails.getRepaymentTenure())
                .emiAmount(CommonUtil.formatIndianCurrency(accountDetails.getEmiAmount()))
                .paymentFrequency(accountDetails.getPaymentFrequency())
                .actualPaymentAmount(CommonUtil.formatIndianCurrency(accountDetails.getActualPaymentAmount()))
                //Account Dates
                .dateOpenedOrDisbursed(accountDates.getDateOpenedOrDisbursed())
                .dateLastPayment(accountDates.getDateLastPayment())
                .dateClosed(accountDates.getDateClosed())
                .dateReportedAndCertified(accountDates.getDateReportedAndCertified())

                .paymentStartDate(openAccountInfo.getPaymentStartDate())
                .paymentEndDate(openAccountInfo.getPaymentEndDate())

                .pastDueMonthlyDataSource(getPastDueMontlyDataSource(openAccountInfo.getPastDueMonthlyStatusList()))
                .pastDueMonthlyReport(getPastDueMonthlyReport())

                .valueOfCollateral(CommonUtil.formatIndianCurrency(openAccountInfo.getValueOfCollateral()))
                .typeOfCollateral(openAccountInfo.getTypeOfCollateral())
                .suitFiledOrWillfulDefault(openAccountInfo.getSuitFiledOrWillfulDefault())
                .creditFacilityStatus(openAccountInfo.getCreditFacilityStatus())
                .writtenOffAmountTotal(CommonUtil.formatIndianCurrency(openAccountInfo.getWrittenOffAmountTotal()))
                .writtenOffAmountPrincipal(CommonUtil.formatIndianCurrency(openAccountInfo.getWrittenOffAmountPrincipal()))
                .settlementAmount(CommonUtil.formatIndianCurrency(openAccountInfo.getSettlementAmount()))
                .build();
    }

    private JasperReport getPastDueMonthlyReport() {
        try {
            return (JasperReport) JRLoader.loadObject(new File(ApplicationConstant.PAST_DUE_MONTHLY_JASPER_PATH));

        } catch (JRException e) {
            // Check if the cause of JRException is a FileNotFoundException
            if (e.getCause() instanceof java.io.FileNotFoundException) {
                throw new FileNotFoundException(1027, "Past Due Table Jrxml / Jasper Report Not Found at this directory: "
                        + ApplicationConstant.PAST_DUE_MONTHLY_JASPER_PATH);
            } else {
                // Re-throw JRException as a runtime exception if the cause is different
                throw new RuntimeException("An error occurred while compiling the address report.", e);
            }
        }
    }

    private JRBeanCollectionDataSource getPastDueMontlyDataSource(List<PastDueMonthlyStatus> pastDueMonthlyStatusList) {
        return new JRBeanCollectionDataSource(pastDueMonthlyStatusList);
    }
}
