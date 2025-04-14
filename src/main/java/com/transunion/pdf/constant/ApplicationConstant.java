package com.transunion.pdf.constant;

import java.math.BigDecimal;

public class ApplicationConstant {

    //Contact Type Constants
    public static final String CONTACT_NOT_CLASSIFIED = "NOT CLASSIFIED";
    public static final String CONTACT_HOME = "HOME PHONE";
    public static final String CONTACT_MOBILE = "MOBILE PHONE";
    public static final String CONTACT_OFFICE = "OFFICE PHONE";

    //Address Type Constants
    public static final String ADDRESS_PERMANENT = "Permanent Address";
    public static final String ADDRESS_OFFICE = "Office Address";
    public static final String ADDRESS_RESIDENCE = "Residence Address";
    public static final String ADDRESS_NOT_CATEGORISED = "Not Categorised";

    //Identification Type Constants
    public static final String ID_TYPE_PAN = "Income Tax ID Number (PAN)";
    public static final String ID_TYPE_PASSPORT = "Passport Number";
    public static final String ID_TYPE_DRIVER_LICENSE = "Driver’s License Number";
    public static final String ID_TYPE_VOTER_ID = "Voter ID Number";
    public static final String ID_TYPE_RATION_CARD = "Ration Card Number";


    //PDF MetaData
    public static final String PDF_AUTHOR = "TransUnion";
    public static final String PDF_TITLE = "CIBIL Report";
    public static final String PDF_SUBJECT = "CIBIL Report";
    public static final String PDF_KEYWORDS = "cibil,report";
    public static final String PDF_PRODUCER = "TransUnion";
    public static final String PDF_CREATOR = "TransUnion";

    //INDIRECT JRXML PATH
    public static final String INDIRECT_MAIN_JRXML_PATH = "src/main/resources/templates/indirect/jrxml/main.jrxml";
    public static final String INDIRECT_INDEX_JRXML_PATH = "src/main/resources/templates/indirect/jrxml/toc.jrxml";
    public static final String INDIRECT_PERSONALINFO_JRXML_PATH = "src/main/resources/templates/indirect/jrxml/personal_information.jrxml";
    public static final String INDIRECT_ACCOUNTINFO_JRXML_PATH = "src/main/resources/templates/indirect/jrxml/account_information.jrxml";
    public static final String INDIRECT_ENQUIRYINFO_JRXML_PATH = "src/main/resources/templates/indirect/jrxml/enquiry_information.jrxml";

    //INDIRECT JASPER PATH
    public static final String INDIRECT_MAIN_JASPER_PATH = "src/main/resources/templates/indirect/jasper/main.jasper";
    public static final String INDIRECT_INDEX_JASPER_PATH = "src/main/resources/templates/indirect/jasper/toc.jasper";
    public static final String INDIRECT_PERSONALINFO_JASPER_PATH = "src/main/resources/templates/indirect/jasper/personal_information.jasper";
    public static final String INDIRECT_ACCOUNTINFO_JASPER_PATH = "src/main/resources/templates/indirect/jasper/account_information.jasper";
    public static final String INDIRECT_ENQUIRYINFO_JASPER_PATH = "src/main/resources/templates/indirect/jasper/enquiry_information.jasper";

    //Common JRXML PATH
    public static final String PAST_DUE_MONTHLY_JRXML_PATH = "src/main/resources/templates/common/jrxml/past_due_table.jrxml";
    public static final String OPEN_ACCOUNT_JRXML_PATH_INDIRECT = "src/main/resources/templates/common/jrxml/open_account_indirect.jrxml";
    public static final String CLOSED_ACCOUNT_JRXML_PATH_INDIRECT = "src/main/resources/templates/common/jrxml/closed_account_indirect.jrxml";
    public static final String OPEN_ACCOUNT_JRXML_PATH_DIRECT = "src/main/resources/templates/common/jrxml/open_account_direct.jrxml";
    public static final String CLOSED_ACCOUNT_JRXML_PATH_DIRECT = "src/main/resources/templates/common/jrxml/closed_account_direct.jrxml";
    public static final String ENQUIRY_TABLE_JRXML_PATH = "src/main/resources/templates/common/jrxml/enquiry table.jrxml";
    public static final String GLOSSARY_1_JRXML_PATH = "src/main/resources/templates/common/jrxml/glossary-1.jrxml";
    public static final String GLOSSARY_2_JRXML_PATH = "src/main/resources/templates/common/jrxml/glossary-2.jrxml";

    //Common JASPER PATH
    public static final String PAST_DUE_MONTHLY_JASPER_PATH = "src/main/resources/templates/common/jasper/past_due_monthly.jasper";
    public static final String OPEN_ACCOUNT_JASPER_PATH_INDIRECT = "src/main/resources/templates/common/jasper/open_account_indirect.jasper";
    public static final String CLOSED_ACCOUNT_JASPER_PATH_INDIRECT = "src/main/resources/templates/common/jasper/closed_account_indirect.jasper";
    public static final String OPEN_ACCOUNT_JASPER_PATH_DIRECT = "src/main/resources/templates/common/jasper/open_account_direct.jasper";
    public static final String CLOSED_ACCOUNT_JASPER_PATH_DIRECT = "src/main/resources/templates/common/jasper/closed_account_direct.jasper";
    public static final String ENQUIRY_TABLE_JASPER_PATH = "src/main/resources/templates/common/jasper/enquiry_table.jasper";
    public static final String GLOSSARY_1_JASPER_PATH = "src/main/resources/templates/common/jasper/glossary-1.jasper";
    public static final String GLOSSARY_2_JASPER_PATH = "src/main/resources/templates/common/jasper/glossary-2.jasper";


    //NH JRXML PATH
    public static final String NH_MAIN_JRXML_PATH = "src/main/resources/templates/nh/jrxml/main.jrxml";
    public static final String NH_INDEX_JRXML_PATH = "src/main/resources/templates/nh/jrxml/toc.jrxml";
    public static final String NH_SUMMARY_JRXML_PATH = "src/main/resources/templates/nh/jrxml/summary.jrxml";
    public static final String NH_PERSONALINFO_JRXML_PATH = "src/main/resources/templates/nh/jrxml/personal_information.jrxml";
    public static final String NH_ACCOUNTINFO_JRXML_PATH = "src/main/resources/templates/nh/account_information.jrxml";
    public static final String NH_ENQUIRYINFO_JRXML_PATH = "src/main/resources/templates/nh/enquiry_information.jrxml";

    //NH JASPER PATH
    public static final String NH_MAIN_JASPER_PATH = "src/main/resources/templates/nh/jasper/main.jasper";
    public static final String NH_INDEX_JASPER_PATH = "src/main/resources/templates/nh/jasper/toc.jasper";
    public static final String NH_SUMMARY_JASPER_PATH = "src/main/resources/templates/nh/jasper/summary.jasper";
    public static final String NH_PERSONALINFO_JASPER_PATH = "src/main/resources/templates/nh/jasper/personal_information.jasper";
    public static final String NH_ACCOUNTINFO_JASPER_PATH = "src/main/resources/templates/nh/jasper/account_information.jasper";
    public static final String NH_ENQUIRYINFO_JASPER_PATH = "src/main/resources/templates/nh/jasper/enquiry_information.jasper";

    //STARTER JRXML PATH
    public static final String STARTER_MAIN_JRXML_PATH = "src/main/resources/templates/starter/jrxml/main.jrxml";
    public static final String STARTER_INDEX_JRXML_PATH = "src/main/resources/templates/starter/jrxml/toc.jrxml";
    public static final String STARTER_PERSONALINFO_JRXML_PATH = "src/main/resources/templates/starter/jrxml/personal_information.jrxml";
    public static final String STARTER_ACCOUNTINFO_JRXML_PATH = "src/main/resources/templates/starter/jrxml/account_information.jrxml";
    public static final String STARTER_ENQUIRYINFO_JRXML_PATH = "src/main/resources/templates/starter/jrxml/enquiry_information.jrxml";
    public static final String STARTER_SUMMARY_JRXML_PATH = "src/main/resources/templates/starter/jrxml/summary.jrxml";


    //STARTER JASPER PATH
    public static final String STARTER_MAIN_JASPER_PATH = "src/main/resources/templates/starter/jasper/main.jasper";
    public static final String STARTER_INDEX_JASPER_PATH = "src/main/resources/templates/starter/jasper/toc.jasper";
    public static final String STARTER_SUMMARY_JASPER_PATH = "src/main/resources/templates/starter/jasper/summary.jasper";
    public static final String STARTER_PERSONALINFO_JASPER_PATH = "src/main/resources/templates/starter/jasper/personal_information.jasper";
    public static final String STARTER_ACCOUNTINFO_JASPER_PATH = "src/main/resources/templates/starter/jasper/account_information.jasper";
    public static final String STARTER_ENQUIRYINFO_JASPER_PATH = "src/main/resources/templates/starter/jasper/enquiry_information.jasper";

    //PAID JRXML PATH
    public static final String PAID_MAIN_JRXML_PATH = "src/main/resources/templates/paid/jrxml/main.jrxml";
    public static final String PAID_INDEX_JRXML_PATH = "src/main/resources/templates/paid/jrxml/toc.jrxml";
    public static final String PAID_SUMMARY_JRXML_PATH = "src/main/resources/templates/paid/jrxml/summary.jrxml";
    public static final String PAID_PERSONALINFO_JRXML_PATH = "src/main/resources/templates/paid/jrxml/personal_information.jrxml";
    public static final String PAID_ACCOUNTINFO_JRXML_PATH = "src/main/resources/templates/paid/jrxml/account_information.jrxml";
    public static final String PAID_ENQUIRYINFO_JRXML_PATH = "src/main/resources/templates/paid/jrxml/enquiry_information.jrxml";

    //PAID JASPER PATH
    public static final String PAID_MAIN_JASPER_PATH = "src/main/resources/templates/paid/jasper/main.jasper";
    public static final String PAID_INDEX_JASPER_PATH = "src/main/resources/templates/paid/jasper/toc.jasper";
    public static final String PAID_SUMMARY_JASPER_PATH = "src/main/resources/templates/paid/jasper/summary.jasper";
    public static final String PAID_PERSONALINFO_JASPER_PATH = "src/main/resources/templates/paid/jasper/personal_information.jasper";
    public static final String PAID_ACCOUNTINFO_JASPER_PATH = "src/main/resources/templates/paid/jasper/account_information.jasper";
    public static final String PAID_ENQUIRYINFO_JASPER_PATH = "src/main/resources/templates/paid/jasper/enquiry_information.jasper";

    public static final String DEFAULT_HYPHEN = "-";
    public static final BigDecimal DEFAULT_BIG_DECIMAL = BigDecimal.valueOf(-1);
}
