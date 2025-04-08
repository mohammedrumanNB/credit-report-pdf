package com.transunion.pdf.util;

import com.ibm.icu.text.NumberFormat;
import com.transunion.pdf.dto.PDFData;
import com.transunion.pdf.exception.InvalidDataException;
import com.transunion.pdf.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

public class CommonUtil {

    public static String formatIndianCurrency(BigDecimal value) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        currencyFormatter.setMaximumFractionDigits(0); // Remove decimals
        currencyFormatter.setMinimumFractionDigits(0);
        return currencyFormatter.format(value);
    }

    public static void validateDisputeInfo(DisputeInfo disputeInfo) {
        if (disputeInfo == null) {
            throw new InvalidDataException(1024, "Dispute Info is Empty or Null");
        }
        if (disputeInfo.getDisputeDate() == null || disputeInfo.getDisputeDate().isBlank()) {
            throw new InvalidDataException(1007, "Dispute Date is Empty or Null.");
        }
        if (disputeInfo.getCibilRemarks() == null || disputeInfo.getCibilRemarks().isBlank()) {
            throw new InvalidDataException(1008, "Dispute Cibil Remark is Empty or Null.");
        }
    }

    public static void validateIndexInfo(PDFData pdfData) {
        if (pdfData == null) {
            throw new InvalidDataException(1010, "PDF Data is Empty or Null");
        }
        if (pdfData.getCibilScore() == null) {
            throw new InvalidDataException(1001, "Cibil Score cannot be empty.");
        } else if (pdfData.getControlNumber() == null) {
            throw new InvalidDataException(1000, "Control Number cannot be empty.");
        } else if (pdfData.getCibilScore() > 900) {
            throw new InvalidDataException(1002, "Cibil Score cannot be more than 900. Provided value: " + pdfData.getCibilScore());
        } else {
            validatePersonalInfo(pdfData.getPersonalInfo());
        }
    }

    public static void validatePersonalInfo(PersonalInfo personalInfo) {
        if (personalInfo == null) {
            throw new InvalidDataException(1003, "Personal Information Details is Empty.");
        }

        if (personalInfo.getFullName() == null || personalInfo.getFullName().isBlank()) {
            throw new InvalidDataException(1004, "Full Name is Empty or Null.");
        }

        if (personalInfo.getGender() == null || personalInfo.getGender().isEmpty()) {
            throw new InvalidDataException(1005, "Gender is Empty or Null.");
        }

        if (personalInfo.getDateOfBirth() == null || personalInfo.getDateOfBirth().isEmpty()) {
            throw new InvalidDataException(1006, "Date of Birth is Empty or Null.");
        }


    }





    public static void validateEmploymentInfo(EmploymentInfo employmentInfo) {
        if (employmentInfo == null) {
            throw new InvalidDataException(1017, "Employment Details is Empty ");
        }
        if (employmentInfo.getAccountType() == null || employmentInfo.getAccountType().isBlank()) {
            throw new InvalidDataException(1018, "Account Type is Empty or Null");
        }
        if (employmentInfo.getOccupation() == null || employmentInfo.getOccupation().isBlank()) {
            throw new InvalidDataException(1019, "Occupation is Empty or Null");
        }
        if (employmentInfo.getDateReported() == null || employmentInfo.getDateReported().isBlank()) {
            throw new InvalidDataException(1020, "Date Report is Empty or Null");
        }
        if (employmentInfo.getIncome() == null) {
            throw new InvalidDataException(1021, "Income value cannot be Null");
        }
        if (employmentInfo.getMonthlyAnnualIncome() == null) {
            throw new InvalidDataException(1022, "Monthly/Annual Income value cannot be Null");
        }
        if (employmentInfo.getNetGrossIncome() == null) {
            throw new InvalidDataException(1023, "Net/Gross Income value cannot be Null");
        }
    }

    public static void validateAccountDetails(AccountDetails accountDetails) {
        if (accountDetails == null) {
            throw new InvalidDataException(1025, "Account Details is Null. Atleast send the empty Account Details Object");
        }
    }

    public static void validateAccountDates(AccountDates accountDates) {
        if (accountDates == null) {
            throw new InvalidDataException(1026, "Account Dates is Null. Atleast send the empty Account Dates Object");
        }
    }


    public static void validateUserPassword(String userPassword) {
        if (userPassword == null || userPassword.isBlank()) {
            throw new InvalidDataException(1030, "User Password cannot be null when encrypting the pdf");
        }
    }
}
