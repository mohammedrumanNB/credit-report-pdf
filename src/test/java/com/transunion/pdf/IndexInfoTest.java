package com.transunion.pdf;

import com.transunion.pdf.constant.ApplicationConstant;
import com.transunion.pdf.dto.PDFData;
import com.transunion.pdf.exception.InvalidDataException;
import com.transunion.pdf.model.IdentificationInfo;
import com.transunion.pdf.model.IndexInfo;
import com.transunion.pdf.service.CibilSummaryReportService;
import com.transunion.pdf.service.IndexReportService;
import com.transunion.pdf.util.CibilGraphUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class IndexInfoTest {

    @Autowired
    private IndexReportService indexReportService;

    @Autowired
    private CibilSummaryReportService cibilSummaryReportService;



//    @Test
//    public void testIndexInfo() {
//        IndexInfo indexInfo = new IndexInfo(816,"11-12-2024","12Th Nov 2024");
//        Assertions.assertEquals("3,865,813,852", indexInfo.getControlNumber());
//    }

    @Test
    public void testIndexInfo1() {
        Assertions.assertThrows(InvalidDataException.class, () -> indexReportService.getIndexParam(null));
        Assertions.assertThrows(InvalidDataException.class, () -> indexReportService.getIndexParam(new PDFData()));
    }

    @Test
    public void testCibilGraphUtil()
    {
        String cibilGraph = CibilGraphUtil.getCibilGraph(310);
    }
    @Test
    public void testIdentificationInfo() {
        IdentificationInfo identificationInfo = new IdentificationInfo();
        identificationInfo.setIdentificationType(ApplicationConstant.ID_TYPE_PAN);
        identificationInfo.setIdNumber("XYZ121");

        List<IdentificationInfo> identificationInfoList = List.of(identificationInfo);

        IdentificationInfo identificationInfo1 = cibilSummaryReportService.getIdentificationInfo(identificationInfoList);
        Assertions.assertEquals("PAN Card", identificationInfo1.getIdentificationType());
    }


}
