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


public class SkillPerformanceGraph extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFreeChart lineChart;
    private DefaultCategoryDataset dataset;
    private ChartPanel chartPanel;
    
    
    public JPanel createChartPanel(CategoryDataset dataset) {
        String chartTitle = "Skill Progress Over Time";
        String categoryAxisLabel = "Training Date";
        String valueAxisLabel = "Skill Level";
 

        JFreeChart chart = ChartFactory.createLineChart(
            chartTitle,
            categoryAxisLabel,
            valueAxisLabel,
            dataset
        );
        
        // background colour
        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 18));
        chart.getCategoryPlot().getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        chart.getCategoryPlot().getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));

        chart.getCategoryPlot().getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        chart.getCategoryPlot().getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        chart.setAntiAlias(true);


        
        org.jfree.chart.plot.CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE); // background
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY); 
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY); 
        
        plot.getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        plot.getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        chart.getLegend().setItemFont(new Font("SansSerif", Font.PLAIN, 12));

        org.jfree.chart.renderer.category.LineAndShapeRenderer renderer = new org.jfree.chart.renderer.category.LineAndShapeRenderer();
         
        renderer.setBaseShapesVisible(true);
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        
        // colours for each skill 
        renderer.setSeriesPaint(0, java.awt.Color.RED); // standard
        renderer.setSeriesStroke(0, new BasicStroke(2.0f)); 
        renderer.setSeriesPaint(1, java.awt.Color.BLUE); // spin
        renderer.setSeriesStroke(1, new BasicStroke(2.0f)); 
        renderer.setSeriesPaint(2, java.awt.Color.GREEN); // pop
        renderer.setSeriesStroke(2, new BasicStroke(2.0f)); 
        renderer.setSeriesPaint(3, java.awt.Color.ORANGE); //front
        renderer.setSeriesStroke(3, new BasicStroke(2.0f)); 
        renderer.setSeriesPaint(4, java.awt.Color.MAGENTA); //rear
        renderer.setSeriesStroke(4, new BasicStroke(2.0f)); 
        renderer.setSeriesPaint(5, java.awt.Color.CYAN); // side
        renderer.setSeriesStroke(5, new BasicStroke(2.0f)); 
        renderer.setSeriesPaint(6, java.awt.Color.YELLOW); // scrabble
        renderer.setSeriesStroke(6, new BasicStroke(2.0f)); 
        renderer.setSeriesPaint(7, java.awt.Color.DARK_GRAY); //drop
        renderer.setSeriesStroke(7, new BasicStroke(2.0f)); 
        renderer.setSeriesPaint(8, java.awt.Color.BLACK); // punt
        renderer.setSeriesStroke(8, new BasicStroke(2.0f)); 
        renderer.setSeriesPaint(9, java.awt.Color.RED); //grubber
        renderer.setSeriesStroke(9, new BasicStroke(2.0f)); 
        renderer.setSeriesPaint(10, java.awt.Color.PINK); //goal
        renderer.setSeriesStroke(10, new BasicStroke(2.0f)); 
        plot.setRenderer(renderer);
        
        org.jfree.chart.axis.NumberAxis yAxis = (org.jfree.chart.axis.NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(org.jfree.chart.axis.NumberAxis.createIntegerTickUnits());

       yAxis.setRange(1, 5); 

        return new ChartPanel(chart);
    }


	}


