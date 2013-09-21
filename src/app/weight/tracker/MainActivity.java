package app.weight.tracker;

import android.app.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.agimind.widget.SlideHolder;

public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mSlideHolder = (SlideHolder)findViewById(R.id.slide_holder);
        mSlideHolder.setAllowInterceptTouch(false);
        mBtnWeightList = (Button)findViewById(R.id.btn_weight_list);
        mBtnLineChart = (Button)findViewById(R.id.btn_line_chart);
        mBtnColumnChart = (Button)findViewById(R.id.btn_column_chart);
        mBtnSettings = (Button)findViewById(R.id.btn_settings);

        mBtnWeightList.setOnClickListener(this);
        mBtnLineChart.setOnClickListener(this);
        mBtnColumnChart.setOnClickListener(this);
        mBtnSettings.setOnClickListener(this);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);

        mWeightListFragment = new WeightListFragment();
        mLineChartFragment = new LineChartFragment();
        mColumnChartFragment = new ColumnChartFragment();
        mSettingsFragment = new SettingsFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, mWeightListFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigateUp() {
        mSlideHolder.toggleImmediately();
        return true;
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (view == mBtnWeightList) {
            fragmentTransaction.replace(R.id.fragment_container, mWeightListFragment);
        } else if (view == mBtnLineChart) {
            fragmentTransaction.replace(R.id.fragment_container, mLineChartFragment);
        } else if (view == mBtnColumnChart) {
            fragmentTransaction.replace(R.id.fragment_container, mColumnChartFragment);
        } else if (view == mBtnSettings) {
            fragmentTransaction.replace(R.id.fragment_container, mSettingsFragment);
        }

        fragmentTransaction.commit();
        mSlideHolder.toggleImmediately();
    }

    private SlideHolder mSlideHolder;
    private Button mBtnWeightList;
    private Button mBtnLineChart;
    private Button mBtnColumnChart;
    private Button mBtnSettings;

    private Fragment mWeightListFragment;
    private Fragment mLineChartFragment;
    private Fragment mColumnChartFragment;
    private Fragment mSettingsFragment;
}
