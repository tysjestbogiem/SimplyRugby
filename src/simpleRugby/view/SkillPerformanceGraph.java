package simpleRugby.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.Map;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * This class builds and returns line chart panel showing Player skill level progression over time.
 * It's used to visually track how a player's skills have changed across different training dates.
 */
public class SkillPerformanceGraph extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFreeChart lineChart;
    private DefaultCategoryDataset dataset;
    private ChartPanel chartPanel;
    
    /**
	 * Creates and returns a chart panel with a line graph based on the skill dataset provided.
	 */
    public JPanel createChartPanel(CategoryDataset dataset) {
    	
        String chartTitle = "Skill Progress Over Time";
        String categoryAxisLabel = "Training Date";
        String valueAxisLabel = "Skill Level";
 
        // Create the line chart
        JFreeChart chart = ChartFactory.createLineChart(
            chartTitle,
            categoryAxisLabel,
            valueAxisLabel,
            dataset
        );
        
        // Set how chart looks
        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 18));
        chart.getCategoryPlot().getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        chart.getCategoryPlot().getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        chart.getCategoryPlot().getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        chart.getCategoryPlot().getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        chart.setAntiAlias(true);
        
        // Sets plot area (where lines are drawn)
        org.jfree.chart.plot.CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE); // background
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY); 
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY); 
        
        // Set axis fonts
        plot.getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        plot.getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        chart.getLegend().setItemFont(new Font("SansSerif", Font.PLAIN, 12));

        // Sets line renderer (controls how lines and points are drawn)
        org.jfree.chart.renderer.category.LineAndShapeRenderer renderer = new org.jfree.chart.renderer.category.LineAndShapeRenderer();
        renderer.setBaseShapesVisible(true);
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        
        // Force Y-axis to show whole numbers from 0 to 6
        org.jfree.chart.axis.NumberAxis yAxis = (org.jfree.chart.axis.NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(org.jfree.chart.axis.NumberAxis.createIntegerTickUnits());

        yAxis.setRange(0, 6); 

        // Return the panel containing the chart
        return new ChartPanel(chart);
    }
}


