package com.transunion.pdf.service;

import com.transunion.pdf.constant.ApplicationConstant;
import com.transunion.pdf.dto.AddressInformation;
import com.transunion.pdf.dto.PDFData;
import com.transunion.pdf.dto.PersonalInformation;
import com.transunion.pdf.enums.PdfVersion;
import com.transunion.pdf.exception.InvalidDataException;
import com.transunion.pdf.model.*;
import com.transunion.pdf.util.CommonUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class PersonalInfoReportService {
    public JasperReport getPersonalInfoReport(PdfVersion pdfVersion) throws JRException {
        String filePath;
        switch (pdfVersion) {
            case INDIRECT:
                //Get PersonalInfo Report jrxml and compile it
                filePath = ApplicationConstant.INDIRECT_PERSONALINFO_JASPER_PATH;
                break;
            case NH:
                //Get PersonalInfo Report jrxml and compile it
                filePath = ApplicationConstant.NH_PERSONALINFO_JASPER_PATH;
                break;

            default:
                throw new InvalidDataException(1016, "Unsupported PDF version: " + pdfVersion);
        }
        return (JasperReport) JRLoader.loadObject(new File(filePath));

    }

    public PersonalInformation getPersonalInformationParam(PDFData pdfData) {
        PersonalInformation personalInformation = new PersonalInformation();

        personalInformation.setControlNumber(pdfData.getControlNumber());
        personalInformation.setReportGeneratedDate(pdfData.getReportGeneratedDate());

        PersonalInfo personalInfo = pdfData.getPersonalInfo();
        List<IdentificationInfo> identificationInfoList = pdfData.getIdentificationInfoList();
        List<AddressInfo> addressInfoList = pdfData.getAddressInfoList();
        List<ContactInfo> contactInfoList = pdfData.getContactInfoList();
        List<EmailInfo> emailInfoList = pdfData.getEmailInfoList();
        EmploymentInfo employmentInfo = pdfData.getEmploymentInfo();


        //Validate Personal Info, Identification Info
        CommonUtil.validatePersonalInfo(personalInfo);
        CommonUtil.validateIdentificationInfo(identificationInfoList);
        CommonUtil.validateEmploymentInfo(employmentInfo);

        personalInformation.setPersonalInfo(personalInfo);
        personalInformation.setIdentificationTableDataSource(getIdentificationTableDataSource(identificationInfoList));
        personalInformation.setAddressInformation(getAddressInformation(addressInfoList));
        personalInformation.setContactInfoList(contactInfoList);
        personalInformation.setEmailInfoList(emailInfoList);
        personalInformation.setAccountType(employmentInfo.getAccountType());
        personalInformation.setDateReported(employmentInfo.getDateReported());
        personalInformation.setOccupation(employmentInfo.getOccupation());
        personalInformation.setIncome(CommonUtil.formatIndianCurrency(employmentInfo.getIncome()));
        personalInformation.setMonthlyAnnualIncome(CommonUtil.formatIndianCurrency(employmentInfo.getMonthlyAnnualIncome()));
        personalInformation.setNetGrossIncome(CommonUtil.formatIndianCurrency(employmentInfo.getNetGrossIncome()));
        return personalInformation;

    }

    private AddressInformation getAddressInformation(List<AddressInfo> addressInfoList) {
        AddressInformation addressInformation = new AddressInformation();
        if (addressInfoList.isEmpty()) {
            addressInformation.setAddressEmpty(true);
        } else {
            addressInformation.setAddressTableDataSource(new JRBeanCollectionDataSource(addressInfoList));
        }
        return addressInformation;
    }


    private JRBeanCollectionDataSource getIdentificationTableDataSource(List<IdentificationInfo> identificationInfoList) {
        return new JRBeanCollectionDataSource(identificationInfoList);
    }
}
