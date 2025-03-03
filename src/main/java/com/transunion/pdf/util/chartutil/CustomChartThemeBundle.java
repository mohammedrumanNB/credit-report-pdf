package com.transunion.pdf.util.chartutil;

import net.sf.jasperreports.charts.ChartTheme;
import net.sf.jasperreports.charts.ChartThemeBundle;

public class CustomChartThemeBundle implements ChartThemeBundle {

    public static final String CUSTOM_THEME_NAME = "DialTheme";

    @Override
    public String[] getChartThemeNames() {
        return new String[]{CUSTOM_THEME_NAME};
    }

    @Override
    public ChartTheme getChartTheme(String themeName) {
        if (CUSTOM_THEME_NAME.equals(themeName)) {
            return new CustomChartTheme();
        }
        return null;
    }
}
