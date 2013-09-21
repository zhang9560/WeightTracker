package app.weight.tracker;

import android.content.Context;
import android.graphics.Color;
import android.preference.PreferenceManager;
import org.afree.chart.AFreeChart;
import org.afree.chart.ChartFactory;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.plot.CategoryPlot;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.renderer.category.BarRenderer;
import org.afree.data.category.CategoryDataset;
import org.afree.data.category.DefaultCategoryDataset;
import org.afree.graphics.GradientColor;
import org.afree.graphics.SolidColor;

public class ColumnChartView extends BaseChartView {

    /**
     * constructor
     * @param context
     */
    public ColumnChartView(Context context) {
        super(context);

        CategoryDataset dataset = createDataset(context);
        AFreeChart chart = createChart(context, dataset);

        setChart(chart);
    }

    /**
     * Returns a sample dataset.
     *
     * @return The dataset.
     */
    private static CategoryDataset createDataset(Context context) {

        // row keys...
        String series1 = context.getString(R.string.min_weight);
        String series2 = context.getString(R.string.max_weight);
        String series3 = context.getString(R.string.current_weight);
        String series4 = context.getString(R.string.target_weight);

        // column keys...
        String category1 = "";

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (WeightListFragment.sWeights.size() > 0) {
            float minWeight = WeightListFragment.sWeights.get(0).weight;
            float maxWeight = WeightListFragment.sWeights.get(0).weight;
            float currentWeight = WeightListFragment.sWeights.get(0).weight;
            float targetWeight = 0f;
            try {
                targetWeight = Float.valueOf(PreferenceManager.getDefaultSharedPreferences(context)
                        .getString(SettingsFragment.KEY_PREF_TARGET_WEIGHT, "0"));
            } catch (NumberFormatException e) {

            }

            for (Weight weight : WeightListFragment.sWeights) {
                if (minWeight > weight.weight) {
                    minWeight = weight.weight;
                }

                if (maxWeight < weight.weight) {
                    maxWeight = weight.weight;
                }
            }

            dataset.addValue(minWeight, series1, category1);
            dataset.addValue(maxWeight, series2, category1);
            dataset.addValue(currentWeight, series3, category1);
            dataset.addValue(targetWeight, series4, category1);
        }

        return dataset;

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static AFreeChart createChart(Context context, CategoryDataset dataset) {

        // create the chart...
        AFreeChart chart = ChartFactory.createBarChart(
                context.getString(R.string.comparison_chart_of_weight),      // chart title
                "",                                                          // domain axis label
                context.getString(R.string.weight),                          // range axis label
                dataset,                                                     // data
                PlotOrientation.VERTICAL,                                    // orientation
                true,                                                        // include legend
                true,                                                        // tooltips?
                false                                                        // URLs?
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
        GradientColor gp0 = new GradientColor(Color.BLUE, Color.rgb(0, 0, 64));
        GradientColor gp1 = new GradientColor(Color.RED, Color.rgb(64, 0, 0));
        GradientColor gp2 = new GradientColor(Color.YELLOW, Color.rgb(64, 64, 0));
        GradientColor gp3 = new GradientColor(Color.GREEN, Color.rgb(0, 64, 0));
        renderer.setSeriesPaintType(0, gp0);
        renderer.setSeriesPaintType(1, gp1);
        renderer.setSeriesPaintType(2, gp2);
        renderer.setSeriesPaintType(3, gp3);

        return chart;

    }
}
