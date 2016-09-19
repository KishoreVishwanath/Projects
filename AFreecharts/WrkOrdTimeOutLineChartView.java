package info.androidhive.slidingmenu.ChartViews;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.afree.chart.ChartFactory;
import org.afree.chart.AFreeChart;
import org.afree.chart.axis.CategoryAxis;
import org.afree.chart.axis.CategoryLabelPositions;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.plot.CategoryPlot;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.renderer.category.BarRenderer;
import org.afree.chart.renderer.category.LineAndShapeRenderer;
import org.afree.chart.title.LegendTitle;
import org.afree.data.RangeType;
import org.afree.data.category.CategoryDataset;
import org.afree.data.category.DefaultCategoryDataset;
import org.afree.graphics.GradientColor;
import org.afree.graphics.SolidColor;
import org.afree.graphics.geom.Font;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;
import org.afree.ui.RectangleEdge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.DropBoxManager.Entry;

public class WrkOrdTimeOutLineChartView extends DemoView {

	/**
	 * constructor
	 * 
	 * @param context
	 */
	public WrkOrdTimeOutLineChartView(Context context, Map<String, Integer> s) {
		super(context);

		CategoryDataset dataset = createDataset(s);
		AFreeChart chart = createChart(dataset);

		setChart(chart);
	}

	/**
	 * Returns a sample dataset.
	 * 
	 * @return The dataset.
	 */
	private static CategoryDataset createDataset(Map<String, Integer> to) {

		// row keys...
		String series1 = "Timeout";
		
		// create the dataset...
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// Map<String, Integer> s = techD.get(0);
		// Map<String, Integer> f = techD.get(1);

		for (Map.Entry<String, Integer> entry : to.entrySet()) {
			String key = entry.getKey();
			Integer thing = entry.getValue();
			dataset.addValue(thing, series1, key);
				}

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
		AFreeChart chart = ChartFactory.createLineChart("TMI Transactions", // chart
				// title
				"Timeout recieved from CLICK", // domain axis label
				"Count", // range axis label
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
		rangeAxis.setRangeType(RangeType.POSITIVE);

		// disable bar outlines...

		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();
		// renderer.setDrawOutlines(false);

		// set up gradient paints for series...
		GradientColor gp0 = new GradientColor(Color.CYAN, Color.rgb(51, 102,
				204));
		GradientColor gp1 = new GradientColor(Color.RED, Color.rgb(255, 0, 0));

		renderer.setSeriesPaintType(0, gp0);
		renderer.setSeriesPaintType(1, gp1);
		renderer.setSeriesStroke(0, 6.0f);
		renderer.setSeriesStroke(1, 6.0f);
		
		 plot.setDomainCrosshairVisible(true);
	     plot.setRangeCrosshairVisible(true);
	     
	     renderer.setBaseShapesVisible(true);
         renderer.setBaseShapesFilled(true);
     	
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