package com.transunion.pdf.util.chartutil;

import net.sf.jasperreports.charts.ChartThemeBundle;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.extensions.ExtensionsRegistry;
import net.sf.jasperreports.extensions.ExtensionsRegistryFactory;

import java.util.Collections;
import java.util.List;

public class CustomExtensionRegistryFactory implements ExtensionsRegistryFactory {
    @Override
    public ExtensionsRegistry createRegistry(String s, JRPropertiesMap jrPropertiesMap) {
        return customExtensionsRegistry;
    }


    private static final ExtensionsRegistry customExtensionsRegistry = new ExtensionsRegistry() {
        @Override
        public <T> List<T> getExtensions(Class<T> extensionType) {
            if (ChartThemeBundle.class.equals(extensionType)) {
                return Collections.singletonList((T) new CustomChartThemeBundle());
            }
            return null;
        }
    };

}