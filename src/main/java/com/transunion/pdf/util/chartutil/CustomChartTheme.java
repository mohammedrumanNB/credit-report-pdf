package com.transunion.pdf.util.chartutil;

import net.sf.jasperreports.charts.*;
import net.sf.jasperreports.charts.type.EdgeEnum;
import net.sf.jasperreports.charts.type.MeterShapeEnum;
import net.sf.jasperreports.charts.util.JRMeterInterval;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.fonts.FontUtil;
import net.sf.jasperreports.engine.type.ModeEnum;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.*;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.Range;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.ValueDataset;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;

public class CustomChartTheme implements ChartTheme {

    protected static final Color TRANSPARENT_PAINT=new Color(0,0,0,0);

    private ChartContext chartContext;

    private FontUtil fontUtil;

    private String customChartType;

    private String additionalChartData;

    @Override
    public JFreeChart createChart(ChartContext chartContext) throws JRException {
        this.chartContext=chartContext;
        JRChart jrChart=getChart();

        if(jrChart.getPropertiesMap()!=null){
            this.customChartType=jrChart.getPropertiesMap().getProperty("customChartType");
            this.additionalChartData=jrChart.getPropertiesMap().getProperty("additionalChartData");
        }

        this.fontUtil=FontUtil.getInstance(chartContext.getJasperReportsContext());
        JFreeChart jFreeChart=createMeterChart();


        return jFreeChart;
    }

    protected JRChart getChart(){
        return chartContext.getChart();
    }

    protected JRChartPlot getPlot(){
        return getChart().getPlot();
    }

    protected JFreeChart createMeterChart() throws JRException{
        JRMeterPlot jrPlot= (JRMeterPlot) getPlot();

        //start by creating the plot that will hold the meter
        MeterPlot chartPlot;
        if(customChartType!=null && customChartType.equalsIgnoreCase("DialTheme")){
            chartPlot = new CustomMeterPlot((ValueDataset)getDataset());
        }else {
            chartPlot=new CustomMeterPlot((ValueDataset) getDataset());
        }

        //Set the shape
        MeterShapeEnum shape = jrPlot.getShape() == null ? MeterShapeEnum.PIE : jrPlot.getShape();
        switch (shape){
            case CHORD:
                chartPlot.setDialShape(DialShape.CHORD);
                break;
            case CIRCLE:
                chartPlot.setDialShape(DialShape.CIRCLE);
                break;
            case PIE:
            default:
                chartPlot.setDialShape(DialShape.PIE);
                break;
        }

        //Set Meter's range
        chartPlot.setRange(convertRange(jrPlot.getDataRange()));

        //set the size of the meter
        int meterAngle = jrPlot.getMeterAngle() == null ? 180 : jrPlot.getMeterAngle();
        chartPlot.setMeterAngle(meterAngle);

        //Set the units - this is just a string that will be shown next to the value
        String units = jrPlot.getUnits();
        if(units!=null && !units.isEmpty()){
           chartPlot.setUnits(units);
        }

        //set the font used for the tick labels
        if(jrPlot.getTickLabelFont() !=null){
            chartPlot.setTickLabelFont(fontUtil.getAwtFont(jrPlot.getTickLabelFont(), getLocale()));
        }

        //Set the spacing between ticks
        double tickInterval = jrPlot.getTickInterval() == null ? 10.0 : jrPlot.getTickInterval();
        chartPlot.setTickSize(tickInterval);

        //set all the colors we support
        Color color = jrPlot.getMeterBackgroundColor();
        if(color!=null){
            chartPlot.setDialBackgroundPaint(color);
        }

        color = jrPlot.getNeedleColor();
        if(color!=null){
            chartPlot.setNeedlePaint(color);
        }

        //Set how the value is displayed
        JRValueDisplay display = jrPlot.getValueDisplay();
        if(display !=null){
            if(display.getColor() != null){
                chartPlot.setValuePaint(display.getColor());
            }

            if(display.getMask() != null){
                chartPlot.setTickLabelFormat(new DecimalFormat(display.getMask()));
            }
            if(display.getFont() !=null){
                chartPlot.setValueFont(fontUtil.getAwtFont(display.getFont(),getLocale()));
            }

            color = jrPlot.getTickColor();
            if(color!=null){
                chartPlot.setTickPaint(color);
            }

        }
        //Now define all intervals, setting their range and color
        List<JRMeterInterval> intervals = jrPlot.getIntervals();
        if(intervals !=null){
            Iterator<JRMeterInterval> iter = intervals.iterator();
            while (iter.hasNext()){
                JRMeterInterval interval = iter.next();
                chartPlot.addInterval(convertInterval(interval));
            }
        }

        //Actually create the chart around the plot
        JFreeChart jFreeChart = new JFreeChart(evaluateTextExpression(getChart().getTitleExpression()), null, chartPlot, isShowLegend());

        //set all the generic options
        configureChart(jFreeChart);

        return jFreeChart;


    }

    protected Range convertRange(JRDataRange dataRange) throws JRException{
        if(dataRange== null){
            return null;
        }
        Number low = (Number) evaluateExpression(dataRange.getLowExpression());
        Number high = (Number) evaluateExpression(dataRange.getHighExpression());
        return new Range(low != null ? low.doubleValue() : 0.0 , high!=null ? high.doubleValue() : 100.0 );
    }

    protected final Object evaluateExpression(JRExpression expression) throws JRException{
        return chartContext.evaluateExpression(expression);
    }

    protected Dataset getDataset(){
        return chartContext.getDataset();
    }

    protected Locale getLocale(){
        return chartContext.getLocale();
    }
    protected final String evaluateTextExpression(JRExpression expression) throws  JRException{
        return chartContext.evaluateTextExpression(expression);
    }

    protected JRFont getFont(JRFont font){
        if(font == null){
            return new JRBaseFont(getChart());
        }
        return font;
    }
    protected MeterInterval convertInterval(JRMeterInterval interval) throws JRException{
        String label=interval.getLabel();
        if(label==null){
            label="";
        }
        Range range = convertRange(interval.getDataRange());
        Color color= interval.getBackgroundColor()==null ? getChart().getBackcolor() :interval.getBackgroundColor();
        float[] components = color.getRGBColorComponents(null);

        float alpha = interval.getAlpha() == null ? (float) JRMeterInterval.DEFAULT_TRANSPARENCY : interval.getAlpha().floatValue();
        Color alphaColor = new Color(components[0], components[1], components[2], alpha);

        return new MeterInterval(label,range,alphaColor,null,alphaColor);
    }

    protected boolean isShowLegend(){
        return getChart().getShowLegend() == null || getChart().getShowLegend();
    }

    protected void configureChart(JFreeChart jFreeChart) throws JRException{
        if(getChart().getMode() == ModeEnum.OPAQUE){
            jFreeChart.setBackgroundPaint(getChart().getBackcolor());
        }else {
            jFreeChart.setBackgroundPaint(TRANSPARENT_PAINT);
        }

        RectangleEdge titleEdge= getEdge(getChart().getTitlePosition(), RectangleEdge.TOP);
        if(jFreeChart.getTitle()!=null){
            TextTitle title = jFreeChart.getTitle();
            title.setPaint(getChart().getTitleColor());
            title.setFont(fontUtil.getAwtFont(getChart().getTitleFont(),getLocale()));
            title.setPosition(titleEdge);
        }

        String subtitleText = evaluateTextExpression(getChart().getSubtitleExpression());
        if(subtitleText !=null){
            TextTitle subtitle=new TextTitle(subtitleText);
            subtitle.setPaint(getChart().getSubtitleColor());

            subtitle.setFont(fontUtil.getAwtFont(getFont(getChart().getSubtitleFont()),getLocale()));
            subtitle.setPosition(titleEdge);
            jFreeChart.addSubtitle(subtitle);
        }

        //Apply all the legend formatting options
        LegendTitle legend = jFreeChart.getLegend();
        if(legend!=null){
            legend.setItemPaint(getChart().getLegendColor());
            if(getChart().getOwnLegendBackgroundColor()==null){
                legend.setBackgroundPaint(TRANSPARENT_PAINT);
            }else{
                legend.setBackgroundPaint(getChart().getLegendBackgroundColor());
            }
            legend.setItemFont(fontUtil.getAwtFont(getFont(getChart().getLegendFont()),getLocale()));
            legend.setPosition(getEdge(getChart().getLegendPosition(),RectangleEdge.BOTTOM));
        }
        configurePlot(jFreeChart.getPlot());
    }

    protected Object getLabelGenerator(){
        return chartContext.getLabelGenerator();
    }

    private static RectangleEdge getEdge(EdgeEnum position, RectangleEdge defaultPosition){
        RectangleEdge edge=defaultPosition;
        if(position!=null){
            switch (position){
                case TOP:
                    edge=RectangleEdge.TOP;
                    break;
                case BOTTOM:
                    edge=RectangleEdge.BOTTOM;
                    break;
                case LEFT:
                    edge=RectangleEdge.LEFT;
                    break;
                case RIGHT:
                    edge=RectangleEdge.RIGHT;
                    break;
            }
        }
        return edge;
    }

    protected void configurePlot(Plot plot){
        plot.setOutlinePaint(TRANSPARENT_PAINT);

        if(getPlot().getOwnBackcolor() ==null){
            plot.setBackgroundPaint(TRANSPARENT_PAINT);
        }else{
            plot.setBackgroundPaint(getPlot().getBackcolor());
        }

        float backgroundAlpha = getPlot().getBackgroundAlpha() == null ? 1f : getPlot().getBackgroundAlpha();
        float foregroundAlpha = getPlot().getForegroundAlpha() == null ? 1f : getPlot().getForegroundAlpha();

        plot.setBackgroundAlpha(backgroundAlpha);
        plot.setForegroundAlpha(foregroundAlpha);

        if (plot instanceof CategoryPlot) {
            // Handle rotation of category labels
            CategoryAxis axis = ((CategoryPlot) plot).getDomainAxis();
            double labelRotation = getLabelRotation();
            if (labelRotation == 90) {
                axis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);
            } else if (labelRotation == -90) {
                axis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
            } else if (labelRotation < 0) {
                // Potential typo: "angle()" is not a standard Java method
                axis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions((-labelRotation / 180.0) * Math.PI));
            } else if (labelRotation > 0) {
                axis.setCategoryLabelPositions(CategoryLabelPositions.createDownRotationLabelPositions( (labelRotation / 180.0) * Math.PI));
            }
        }

        SortedSet<JRChartPlot.JRSeriesColor> seriesColors = getPlot().getSeriesColors();

        if (seriesColors != null && !seriesColors.isEmpty()) {
            if (seriesColors.size() == 1) {
                Paint[] colors = new Paint[DefaultDrawingSupplier.DEFAULT_PAINT_SEQUENCE.length + 1];
                colors[0] = seriesColors.first().getColor();
                System.arraycopy(
                        DefaultDrawingSupplier.DEFAULT_PAINT_SEQUENCE,
                        0,
                        colors,
                        1,
                        DefaultDrawingSupplier.DEFAULT_PAINT_SEQUENCE.length
                );
                plot.setDrawingSupplier(new DefaultDrawingSupplier(colors,
                        DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
                        DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
                        DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
                        DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE
                ));
            }
            else if (seriesColors.size() > 1) {
                Color[] colors = new Color[seriesColors.size()];
                JRChartPlot.JRSeriesColor[] colorSequence = new JRChartPlot.JRSeriesColor[seriesColors.size()];

                seriesColors.toArray(colorSequence);
                for (int i = 0; i < colorSequence.length; i++) {
                    colors[i] = colorSequence[i].getColor();
                }
                plot.setDrawingSupplier(new DefaultDrawingSupplier(
                        colors,
                        DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
                        DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
                        DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
                        DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE
                ));
            }
        }

        final CustomChartThemeBundle BUNDLE=new CustomChartThemeBundle();



    }

    @SuppressWarnings("deprecation")
    private double getLabelRotation(){
        return getPlot().getLabelRotation() == null ? 0d : getPlot().getLabelRotation();
    }
}
