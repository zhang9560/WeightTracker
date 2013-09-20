package app.weight.tracker;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.view.LineChart;

import java.util.ArrayList;

public class LineChartFragment extends Fragment implements ChartUpdater {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLineChart = (LineChart)inflater.inflate(R.layout.line_chart, container, false);
        mLineChart.setBackgroundColor(Color.TRANSPARENT);
        mLineChart.setDisplayLatitude(true);
        mLineChart.setDisplayLongitude(false);

        return mLineChart;
    }

    @Override
    public void updateChart() {
        // Display line chart if number of points more than 1.
        if (WeightListFragment.sWeights.size() > 1) {
            int maxWeight = (int)WeightListFragment.sWeights.get(0).weight + 1;
            int minWeight = (int)WeightListFragment.sWeights.get(0).weight;
            ArrayList<Float> lineData = new ArrayList<Float>();

            for (int i = WeightListFragment.sWeights.size() - 1; i >= 0; i--) {
                Weight aWeight = WeightListFragment.sWeights.get(i);

                if (maxWeight < aWeight.weight) {
                    maxWeight = (int)aWeight.weight + 1;
                }

                if (minWeight > aWeight.weight) {
                    minWeight = (int)aWeight.weight;
                }

                lineData.add(aWeight.weight);
            }

            ArrayList<String> axisYTitles = new ArrayList<String>();
            for (int i = minWeight; i <= maxWeight; i++) {
                axisYTitles.add(String.valueOf(i));
            }
            mLineChart.setAxisYTitles(axisYTitles);

            LineEntity weightLineEntity = new LineEntity();
            weightLineEntity.setLineColor(Color.BLACK);
            weightLineEntity.setLineData(lineData);

            ArrayList<LineEntity> lines = new ArrayList<LineEntity>();
            lines.add(weightLineEntity);

            mLineChart.setMaxValue(maxWeight);
            mLineChart.setMinValue(minWeight);
            mLineChart.setMaxPointNum(WeightListFragment.sWeights.size());
            mLineChart.setLineData(lines);
            mLineChart.invalidate();
        }
    }

    private LineChart mLineChart;
}
