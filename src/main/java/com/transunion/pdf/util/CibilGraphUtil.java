package com.transunion.pdf.util;

import com.transunion.pdf.constant.ApplicationConstant;
import com.transunion.pdf.exception.InvalidDataException;

import java.util.NavigableMap;
import java.util.TreeMap;

public class CibilGraphUtil {
    private static final NavigableMap<Integer, String> CIBIL_GRAPH_MAP = new TreeMap<>();

    static {
        CIBIL_GRAPH_MAP.put(300, ApplicationConstant.CIBIL_GRAPH_300);
        CIBIL_GRAPH_MAP.put(301, ApplicationConstant.CIBIL_GRAPH_301_320);
        CIBIL_GRAPH_MAP.put(321, ApplicationConstant.CIBIL_GRAPH_321_340);
        CIBIL_GRAPH_MAP.put(341, ApplicationConstant.CIBIL_GRAPH_341_360);
        CIBIL_GRAPH_MAP.put(361, ApplicationConstant.CIBIL_GRAPH_361_380);
        CIBIL_GRAPH_MAP.put(381, ApplicationConstant.CIBIL_GRAPH_381_400);
        CIBIL_GRAPH_MAP.put(401, ApplicationConstant.CIBIL_GRAPH_401_420);
        CIBIL_GRAPH_MAP.put(421, ApplicationConstant.CIBIL_GRAPH_421_440);
        CIBIL_GRAPH_MAP.put(441, ApplicationConstant.CIBIL_GRAPH_441_460);
        CIBIL_GRAPH_MAP.put(461, ApplicationConstant.CIBIL_GRAPH_461_480);
        CIBIL_GRAPH_MAP.put(481, ApplicationConstant.CIBIL_GRAPH_481_500);
        CIBIL_GRAPH_MAP.put(501, ApplicationConstant.CIBIL_GRAPH_501_520);
        CIBIL_GRAPH_MAP.put(521, ApplicationConstant.CIBIL_GRAPH_521_540);
        CIBIL_GRAPH_MAP.put(541, ApplicationConstant.CIBIL_GRAPH_541_560);
        CIBIL_GRAPH_MAP.put(561, ApplicationConstant.CIBIL_GRAPH_561_580);
        CIBIL_GRAPH_MAP.put(581, ApplicationConstant.CIBIL_GRAPH_581_600);
        CIBIL_GRAPH_MAP.put(601, ApplicationConstant.CIBIL_GRAPH_601_620);
        CIBIL_GRAPH_MAP.put(621, ApplicationConstant.CIBIL_GRAPH_621_640);
        CIBIL_GRAPH_MAP.put(641, ApplicationConstant.CIBIL_GRAPH_641_660);
        CIBIL_GRAPH_MAP.put(661, ApplicationConstant.CIBIL_GRAPH_661_680);
        CIBIL_GRAPH_MAP.put(681, ApplicationConstant.CIBIL_GRAPH_681_700);
        CIBIL_GRAPH_MAP.put(701, ApplicationConstant.CIBIL_GRAPH_701_720);
        CIBIL_GRAPH_MAP.put(721, ApplicationConstant.CIBIL_GRAPH_721_722);
        CIBIL_GRAPH_MAP.put(723, ApplicationConstant.CIBIL_GRAPH_723_727);
        CIBIL_GRAPH_MAP.put(728, ApplicationConstant.CIBIL_GRAPH_728_732);
        CIBIL_GRAPH_MAP.put(733, ApplicationConstant.CIBIL_GRAPH_733_737);
        CIBIL_GRAPH_MAP.put(738, ApplicationConstant.CIBIL_GRAPH_738_742);
        CIBIL_GRAPH_MAP.put(743, ApplicationConstant.CIBIL_GRAPH_743_747);
        CIBIL_GRAPH_MAP.put(748, ApplicationConstant.CIBIL_GRAPH_748_752);
        CIBIL_GRAPH_MAP.put(753, ApplicationConstant.CIBIL_GRAPH_753_757);
        CIBIL_GRAPH_MAP.put(758, ApplicationConstant.CIBIL_GRAPH_758_762);
        CIBIL_GRAPH_MAP.put(763, ApplicationConstant.CIBIL_GRAPH_763_764);
        CIBIL_GRAPH_MAP.put(765, ApplicationConstant.CIBIL_GRAPH_765_769);
        CIBIL_GRAPH_MAP.put(770, ApplicationConstant.CIBIL_GRAPH_770_774);
        CIBIL_GRAPH_MAP.put(775, ApplicationConstant.CIBIL_GRAPH_775_777);
        CIBIL_GRAPH_MAP.put(778, ApplicationConstant.CIBIL_GRAPH_778_787);
        CIBIL_GRAPH_MAP.put(788, ApplicationConstant.CIBIL_GRAPH_788_797);
        CIBIL_GRAPH_MAP.put(798, ApplicationConstant.CIBIL_GRAPH_798_807);
        CIBIL_GRAPH_MAP.put(808, ApplicationConstant.CIBIL_GRAPH_808_817);
        CIBIL_GRAPH_MAP.put(818, ApplicationConstant.CIBIL_GRAPH_818_827);
        CIBIL_GRAPH_MAP.put(828, ApplicationConstant.CIBIL_GRAPH_828_837);
        CIBIL_GRAPH_MAP.put(838, ApplicationConstant.CIBIL_GRAPH_838_847);
        CIBIL_GRAPH_MAP.put(848, ApplicationConstant.CIBIL_GRAPH_848_857);
        CIBIL_GRAPH_MAP.put(858, ApplicationConstant.CIBIL_GRAPH_858_867);
        CIBIL_GRAPH_MAP.put(868, ApplicationConstant.CIBIL_GRAPH_868_877);
        CIBIL_GRAPH_MAP.put(878, ApplicationConstant.CIBIL_GRAPH_878_887);
        CIBIL_GRAPH_MAP.put(888, ApplicationConstant.CIBIL_GRAPH_888_897);
        CIBIL_GRAPH_MAP.put(898, ApplicationConstant.CIBIL_GRAPH_898_900);
    }

    public static String getCibilGraph(int value) {
        if (value > 900) {
            throw new InvalidDataException(1002, "Cibil Score cannot be more than 900.");
        }
        if (value < 300) {
            return ApplicationConstant.CIBIL_GRAPH_0;
        }
        return CIBIL_GRAPH_MAP.floorEntry(value).getValue();
    }
}

