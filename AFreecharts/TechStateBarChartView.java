package info.androidhive.slidingmenu.ChartViews;

import info.androidhive.slidingmenu.model.TechnicianData;

import java.util.List;

import org.afree.chart.ChartFactory;
import org.afree.chart.AFreeChart;
import org.afree.chart.axis.CategoryAxis;
import org.afree.chart.axis.CategoryLabelPositions;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.plot.CategoryPlot;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.renderer.category.BarRenderer;
import org.afree.chart.title.LegendTitle;
import org.afree.data.category.CategoryDataset;
import org.afree.data.category.DefaultCategoryDataset;
import org.afree.graphics.GradientColor;
import org.afree.graphics.SolidColor;
import org.afree.graphics.geom.Font;
import org.afree.ui.RectangleEdge;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

public class TechStateBarChartView extends DemoView {

	/**
	 * constructor
	 * 
	 * @param context
	 */
	public TechStateBarChartView(Context context, TechnicianData techdata) {
		super(context);

		CategoryDataset dataset = createDataset(techdata);
		AFreeChart chart = createChart(dataset);

		setChart(chart);
	}

	/**
	 * Returns a sample dataset.
	 * 
	 * @return The dataset.
	 */
	private static CategoryDataset createDataset(TechnicianData techD) {

		// row keys...
		String series1 = "Job Count";
		// String series2 = "Expenses";
		// create the dataset...
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(techD.quebecJobCount, series1, "Quebec");
		dataset.addValue(techD.ontarioJobCount, series1, "Ontario");
		return dataset;

	}

	/**
	 * Creates a sample chart.
	 * 
	 * @param dataset
	 *            the dataset.
	 * 
	 * @return The chart.
	 */
	private static AFreeChart createChart(CategoryDataset dataset) {

		// create the chart...
		AFreeChart chart = ChartFactory.createBarChart("CLICK managed Job Incompletion Rate", // chart
																			// title
				"Incompletion Rate", // domain axis label
				"Job Count", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				false, // include legend
				true, // tooltips?
				false // URLs?
				);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

		// set the background color for the chart...
		chart.setBackgroundPaintType(new SolidColor(Color.WHITE));

		// get a reference to the plot for further customisation...
		CategoryPlot plot = (CategoryPlot) chart.getPlot();

		// set the range axis to display integers only...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		// disable bar outlines...
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);

		// set up gradient paints for series...
		GradientColor gp0 = new GradientColor(Color.CYAN, Color.rgb(51, 102, 204));
		GradientColor gp1 = new GradientColor(Color.RED, Color.rgb(255, 0, 0));
		renderer.setSeriesPaintType(0, gp0);
		renderer.setSeriesPaintType(1, gp1);

		CategoryAxis domainAxis = plot.getDomainAxis();

		domainAxis.setCategoryLabelPositions(CategoryLabelPositions
				.createUpRotationLabelPositions(Math.PI / 6.0));
		// OPTIONAL CUSTOMISATION COMPLETED.

		Font font = new Font("Dialog", Typeface.NORMAL, 30);
		rangeAxis.setTickLabelFont(font);
		Font font2 = new Font("Dialog", Typeface.NORMAL, 35);
		domainAxis.setTickLabelFont(font2);

		Font font3 = new Font("SansSerif", Typeface.BOLD, 30);
		plot.getDomainAxis().setLabelFont(font3);
		plot.getRangeAxis().setLabelFont(font3);
		
		LegendTitle legend = new LegendTitle(plot.getRenderer());
		
		Font font4 = new Font("Dialog", Typeface.NORMAL, 30);
		legend.setItemFont(font3);
		chart.addLegend(legend);
		legend.setPosition(RectangleEdge.BOTTOM); 

		return chart;

	}
}