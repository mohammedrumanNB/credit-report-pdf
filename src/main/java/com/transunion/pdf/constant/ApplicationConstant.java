package com.transunion.pdf.constant;

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
    public static final String INDIRECT_PERSONALINFO_JRXML_PATH = "src/main/resources/templates/indirect/jrxml/pi.jrxml";
    public static final String INDIRECT_ACCOUNTINFO_JRXML_PATH = "src/main/resources/templates/indirect/jrxml/ai.jrxml";
    public static final String INDIRECT_ENQUIRYINFO_JRXML_PATH = "src/main/resources/templates/indirect/jrxml/ei.jrxml";

    //INDIRECT JASPER PATH
    public static final String INDIRECT_MAIN_JASPER_PATH = "src/main/resources/templates/indirect/jasper/main.jasper";
    public static final String INDIRECT_INDEX_JASPER_PATH = "src/main/resources/templates/indirect/jasper/toc.jasper";
    public static final String INDIRECT_PERSONALINFO_JASPER_PATH = "src/main/resources/templates/indirect/jasper/personal_information.jasper";
    public static final String INDIRECT_ACCOUNTINFO_JASPER_PATH = "src/main/resources/templates/indirect/jasper/account_information.jasper";
    public static final String INDIRECT_ENQUIRYINFO_JASPER_PATH = "src/main/resources/templates/indirect/jasper/enquiry_information.jasper";

    //Common JRXML PATH
    public static final String PAST_DUE_MONTHLY_JRXML_PATH = "src/main/resources/templates/indirect/jrxml/past_due_table.jrxml";
    public static final String OPEN_ACCOUNT_JRXML_PATH = "src/main/resources/templates/indirect/jrxml/open_account.jrxml";
    public static final String CLOSED_ACCOUNT_JRXML_PATH = "src/main/resources/templates/indirect/jrxml/closed_account.jrxml";
    public static final String ENQUIRY_TABLE_JRXML_PATH = "src/main/resources/templates/indirect/jrxml/enquiry table.jrxml";
    public static final String GLOSSARY_1_JRXML_PATH = "src/main/resources/templates/nh/jrxml/glossary-1.jrxml";
    public static final String GLOSSARY_2_JRXML_PATH = "src/main/resources/templates/nh/jrxml/glossary-2.jrxml";

    //Common JASPER PATH
    public static final String PAST_DUE_MONTHLY_JASPER_PATH = "src/main/resources/templates/indirect/jasper/past_due_monthly.jasper";
    public static final String OPEN_ACCOUNT_JASPER_PATH = "src/main/resources/templates/indirect/jasper/open_account.jasper";
    public static final String CLOSED_ACCOUNT_JASPER_PATH = "src/main/resources/templates/indirect/jasper/closed_account.jasper";
    public static final String ENQUIRY_TABLE_JASPER_PATH = "src/main/resources/templates/indirect/jasper/enquiry_table.jasper";
    public static final String GLOSSARY_1_JASPER_PATH = "src/main/resources/templates/nh/jasper/glossary1.jasper";
    public static final String GLOSSARY_2_JASPER_PATH = "src/main/resources/templates/nh/jasper/glossary2.jasper";


    //NH JRXML PATH
    public static final String NH_MAIN_JRXML_PATH = "src/main/resources/templates/nh/jrxml/main.jrxml";
    public static final String NH_INDEX_JRXML_PATH = "src/main/resources/templates/nh/jrxml/toc.jrxml";
    public static final String NH_SUMMARY_JRXML_PATH = "src/main/resources/templates/nh/jrxml/cs.jrxml";
    public static final String NH_PERSONALINFO_JRXML_PATH = "src/main/resources/templates/nh/jrxml/pi.jrxml";
    public static final String NH_ACCOUNTINFO_JRXML_PATH = "src/main/resources/templates/nh/ai.jrxml";
    public static final String NH_ENQUIRYINFO_JRXML_PATH = "src/main/resources/templates/nh/ei.jrxml";

    //NH JASPER PATH
    public static final String NH_MAIN_JASPER_PATH = "src/main/resources/templates/nh/jasper/main.jasper";
    public static final String NH_INDEX_JASPER_PATH = "src/main/resources/templates/nh/jasper/toc.jasper";
    public static final String NH_SUMMARY_JASPER_PATH = "src/main/resources/templates/nh/jasper/summary.jasper";
    public static final String NH_PERSONALINFO_JASPER_PATH = "src/main/resources/templates/nh/jasper/personal_information.jasper";
    public static final String NH_ACCOUNTINFO_JASPER_PATH = "src/main/resources/templates/nh/jasper/account_information.jasper";
    public static final String NH_ENQUIRYINFO_JASPER_PATH = "src/main/resources/templates/nh/jasper/enquiry_information.jasper";


    public static final String CIBIL_GRAPH_0 = "";
    public static final String CIBIL_GRAPH_300 = "";
    public static final String CIBIL_GRAPH_301_320 = "";
    public static final String CIBIL_GRAPH_321_340 = "";
    public static final String CIBIL_GRAPH_341_360 = "";
    public static final String CIBIL_GRAPH_361_380 = "";
    public static final String CIBIL_GRAPH_381_400 = "";
    public static final String CIBIL_GRAPH_401_420 = "";
    public static final String CIBIL_GRAPH_421_440 = "";
    public static final String CIBIL_GRAPH_441_460 = "";
    public static final String CIBIL_GRAPH_461_480 = "";
    public static final String CIBIL_GRAPH_481_500 = "";
    public static final String CIBIL_GRAPH_501_520 = "";
    public static final String CIBIL_GRAPH_521_540 = "";
    public static final String CIBIL_GRAPH_541_560 = "";
    public static final String CIBIL_GRAPH_561_580 = "";
    public static final String CIBIL_GRAPH_581_600 = "";
    public static final String CIBIL_GRAPH_601_620 = "";
    public static final String CIBIL_GRAPH_621_640 = "";
    public static final String CIBIL_GRAPH_641_660 = "";
    public static final String CIBIL_GRAPH_661_680 = "";
    public static final String CIBIL_GRAPH_681_700 = "";
    public static final String CIBIL_GRAPH_701_720 = "";
    public static final String CIBIL_GRAPH_721_722 = "";

    public static final String CIBIL_GRAPH_723_727 = "";
    public static final String CIBIL_GRAPH_728_732 = "";
    public static final String CIBIL_GRAPH_733_737 = "";
    public static final String CIBIL_GRAPH_738_742 = "";
    public static final String CIBIL_GRAPH_743_747 = "";

    public static final String CIBIL_GRAPH_748_752 = "";
    public static final String CIBIL_GRAPH_753_757 = "";
    public static final String CIBIL_GRAPH_758_762 = "";
    public static final String CIBIL_GRAPH_763_764 = "";

    public static final String CIBIL_GRAPH_765_769 = "";
    public static final String CIBIL_GRAPH_770_774 = "";
    public static final String CIBIL_GRAPH_775_777 = "";

    public static final String CIBIL_GRAPH_778_787 = "";
    public static final String CIBIL_GRAPH_788_797 = "";
    public static final String CIBIL_GRAPH_798_807 = "";
    public static final String CIBIL_GRAPH_808_817 = "";
    public static final String CIBIL_GRAPH_818_827 = "";
    public static final String CIBIL_GRAPH_828_837 = "";
    public static final String CIBIL_GRAPH_838_847 = "";
    public static final String CIBIL_GRAPH_848_857 = "";
    public static final String CIBIL_GRAPH_858_867 = "";
    public static final String CIBIL_GRAPH_868_877 = "";
    public static final String CIBIL_GRAPH_878_887 = "";
    public static final String CIBIL_GRAPH_888_897 = "";
    public static final String CIBIL_GRAPH_898_900 = "";


}
