package com.transunion.pdf.service;

import com.transunion.pdf.constant.ApplicationConstant;
import com.transunion.pdf.dto.AccountInformation;
import com.transunion.pdf.dto.PDFData;
import com.transunion.pdf.enums.PdfVersion;
import com.transunion.pdf.exception.FileNotFoundException;
import com.transunion.pdf.exception.InvalidDataException;
import com.transunion.pdf.model.*;
import com.transunion.pdf.util.CommonUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountInfoReportService {

    public JasperReport getAccountInfoReport(PdfVersion pdfVersion) throws JRException {
        String filePath;
        switch (pdfVersion) {
            case INDIRECT:
                filePath = ApplicationConstant.INDIRECT_ACCOUNTINFO_JASPER_PATH;
                break;
            case STARTER:
                filePath = ApplicationConstant.STARTER_ACCOUNTINFO_JASPER_PATH;
                break;
            case PAID:
                filePath = ApplicationConstant.PAID_ACCOUNTINFO_JASPER_PATH;
                break;
            default:
                throw new IllegalArgumentException("Unsupported PDF version: " + pdfVersion);
        }
        return (JasperReport) JRLoader.loadObject(new File(filePath));

    }

    public AccountInformation getAccountInformationParam(PDFData pdfData, PdfVersion pdfVersion) {
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
                .highCreditOrAmountSanctionLabel(accountDetails.getHighCreditOrAmountSanctionLabel())
                .accountUnderDispute(accountDetails.isAccountUnderDispute())
                .disputeInfo(accountDetails.isAccountUnderDispute() ? accountDetails.getDisputeInfo() : null)
                .creditLimit(checkDefaultBigDecimal(accountDetails.getCreditLimit()))
                .highCredit(checkDefaultBigDecimal(accountDetails.getHighCredit()))
                .currentBalance(checkDefaultBigDecimal(accountDetails.getCurrentBalance()))
                .cashLimit(checkDefaultBigDecimal(accountDetails.getCashLimit()))
                .amountOverdue(checkDefaultBigDecimal(accountDetails.getAmountOverdue()))
                .rateOfInterest(checkDefaultRateofInterest(accountDetails.getRateOfInterest()))
                .repaymentTenure(getRepaymentTenure(accountDetails))
                .emiAmount(checkDefaultBigDecimal(accountDetails.getEmiAmount()))
                .paymentFrequency(accountDetails.getPaymentFrequency())
                .actualPaymentAmount(checkDefaultBigDecimal(accountDetails.getActualPaymentAmount()))
                //Account Dates
                .dateOpenedOrDisbursed(accountDates.getDateOpenedOrDisbursed())
                .dateLastPayment(accountDates.getDateLastPayment())
                .dateClosed(accountDates.getDateClosed())
                .dateReportedAndCertified(accountDates.getDateReportedAndCertified())

                .paymentStartDate(closedAccountInfo.getPaymentStartDate())
                .paymentEndDate(closedAccountInfo.getPaymentEndDate())

                .pastDueMonthlyDataSource(getPastDueMontlyDataSource(closedAccountInfo.getPastDueMonthlyStatusList()))
                .pastDueMonthlyReport(getPastDueMonthlyReport())

                .valueOfCollateral(checkDefaultBigDecimal(closedAccountInfo.getValueOfCollateral()))
                .typeOfCollateral(closedAccountInfo.getTypeOfCollateral())
                .suitFiledOrWillfulDefault(closedAccountInfo.getSuitFiledOrWillfulDefault())
                .creditFacilityStatus(closedAccountInfo.getCreditFacilityStatus())
                .writtenOffAmountTotal(checkDefaultBigDecimal(closedAccountInfo.getWrittenOffAmountTotal()))
                .writtenOffAmountPrincipal(checkDefaultBigDecimal(closedAccountInfo.getWrittenOffAmountPrincipal()))
                .settlementAmount(checkDefaultBigDecimal(closedAccountInfo.getSettlementAmount()))
                .build();
    }

    private static String getRepaymentTenure(AccountDetails accountDetails) {
        return accountDetails.getRepaymentTenure().equals(ApplicationConstant.DEFAULT_BIG_DECIMAL) ? ApplicationConstant.DEFAULT_HYPHEN : accountDetails.getRepaymentTenure().toString();
    }

    private JasperReport getClosedAccountReport(PdfVersion pdfVersion) {
        try {
            if (pdfVersion.equals(PdfVersion.STARTER) || pdfVersion.equals(PdfVersion.PAID)) {
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
                throw new InvalidDataException(1001, ApplicationConstant.COMPILING_THE_ADDRESS_REPORT);
            }
        }
    }


    private JasperReport getOpenAccountReport(PdfVersion pdfVersion) {
        try {
            if (pdfVersion.equals(PdfVersion.STARTER) || pdfVersion.equals(PdfVersion.PAID)) {
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
                throw new InvalidDataException(1001, ApplicationConstant.COMPILING_THE_ADDRESS_REPORT);
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
                .highCreditOrAmountSanctionLabel(accountDetails.getHighCreditOrAmountSanctionLabel())
                .accountUnderDispute(accountDetails.isAccountUnderDispute())
                .disputeInfo(accountDetails.isAccountUnderDispute() ? accountDetails.getDisputeInfo() : null)
                .creditLimit(checkDefaultBigDecimal(accountDetails.getCreditLimit()))
                .highCredit(checkDefaultBigDecimal(accountDetails.getHighCredit()))
                .currentBalance(checkDefaultBigDecimal(accountDetails.getCurrentBalance()))
                .cashLimit(checkDefaultBigDecimal(accountDetails.getCashLimit()))
                .amountOverdue(checkDefaultBigDecimal(accountDetails.getAmountOverdue()))
                .rateOfInterest(checkDefaultRateofInterest(accountDetails.getRateOfInterest()))
                .repaymentTenure(getRepaymentTenure(accountDetails))
                .emiAmount(checkDefaultBigDecimal(accountDetails.getEmiAmount()))
                .paymentFrequency(accountDetails.getPaymentFrequency())
                .actualPaymentAmount(checkDefaultBigDecimal(accountDetails.getActualPaymentAmount()))
                //Account Dates
                .dateOpenedOrDisbursed(accountDates.getDateOpenedOrDisbursed())
                .dateLastPayment(accountDates.getDateLastPayment())
                .dateClosed(accountDates.getDateClosed())
                .dateReportedAndCertified(accountDates.getDateReportedAndCertified())

                .paymentStartDate(openAccountInfo.getPaymentStartDate())
                .paymentEndDate(openAccountInfo.getPaymentEndDate())

                .pastDueMonthlyDataSource(getPastDueMontlyDataSource(openAccountInfo.getPastDueMonthlyStatusList()))
                .pastDueMonthlyReport(getPastDueMonthlyReport())

                .valueOfCollateral(checkDefaultBigDecimal(openAccountInfo.getValueOfCollateral()))
                .typeOfCollateral(openAccountInfo.getTypeOfCollateral())
                .suitFiledOrWillfulDefault(openAccountInfo.getSuitFiledOrWillfulDefault())
                .creditFacilityStatus(openAccountInfo.getCreditFacilityStatus())
                .writtenOffAmountTotal(checkDefaultBigDecimal(openAccountInfo.getWrittenOffAmountTotal()))
                .writtenOffAmountPrincipal(checkDefaultBigDecimal(openAccountInfo.getWrittenOffAmountPrincipal()))
                .settlementAmount(checkDefaultBigDecimal(openAccountInfo.getSettlementAmount()))
                .build();
    }

    private static String checkDefaultRateofInterest(BigDecimal roi) {
        return ApplicationConstant.DEFAULT_BIG_DECIMAL.compareTo(roi) == 0 ? ApplicationConstant.DEFAULT_HYPHEN : roi + "%";
    }

    private String checkDefaultBigDecimal(BigDecimal decimal) {
        return decimal.equals(ApplicationConstant.DEFAULT_BIG_DECIMAL) ? ApplicationConstant.DEFAULT_HYPHEN : CommonUtil.formatIndianCurrency(decimal);
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
                throw new InvalidDataException(1001, ApplicationConstant.COMPILING_THE_ADDRESS_REPORT);
            }
        }
    }

    private JRBeanCollectionDataSource getPastDueMontlyDataSource(List<PastDueMonthlyStatus> pastDueMonthlyStatusList) {
        return new JRBeanCollectionDataSource(pastDueMonthlyStatusList);
    }
}
