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
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

public class LineChartFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDBHelper = new WeightDBHelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LineChart lineChart = (LineChart)inflater.inflate(R.layout.line_chart, container, false);

        ArrayList<String> axisXTitles = new ArrayList<String>();
        ArrayList<String> axisYTitles = new ArrayList<String>();
        for (int i = 0; i <= 100; i += 10) {
            axisYTitles.add(String.valueOf(i));
        }
        axisXTitles.add("9/1");
        axisXTitles.add("9/2");
        axisXTitles.add("9/3");
        axisXTitles.add("9/4");
        axisXTitles.add("9/5");
        lineChart.setAxisXTitles(axisXTitles);
        lineChart.setAxisYTitles(axisYTitles);

        ArrayList<Float> lineData = new ArrayList<Float>();
        lineData.add(10f);
        lineData.add(20f);
        lineData.add(50f);
        lineData.add(40f);
        lineData.add(80f);
        LineEntity line1 = new LineEntity();
        line1.setTitle("test");
        line1.setLineColor(Color.WHITE);
        line1.setLineData(lineData);

        ArrayList<LineEntity> lines = new ArrayList<LineEntity>();
        lines.add(line1);

        lineChart.setMaxValue(100);
        lineChart.setMinValue(0);
        lineChart.setMaxPointNum(5);
        lineChart.setDisplayLatitude(false);
        lineChart.setDisplayLongitude(false);
        lineChart.setLineData(lines);

        return lineChart;
    }

    private WeightDBHelper mDBHelper;
}
