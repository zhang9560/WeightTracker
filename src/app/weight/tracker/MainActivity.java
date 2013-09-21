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
        mBtnWeightList = (Button)findViewById(R.id.btn_weight_list);
        mBtnLineChart = (Button)findViewById(R.id.btn_line_chart);
        mBtnSettings = (Button)findViewById(R.id.btn_settings);

        mBtnWeightList.setOnClickListener(this);
        mBtnLineChart.setOnClickListener(this);
        mBtnSettings.setOnClickListener(this);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);

        mWeightListFragment = new WeightListFragment();
        mLineChartFragment = new LineChartFragment();
        mSettingsFragment = new SettingsFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, mWeightListFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigateUp() {
        mSlideHolder.toggle();
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
        } else if (view == mBtnSettings) {
            fragmentTransaction.replace(R.id.fragment_container, mSettingsFragment);
        }

        fragmentTransaction.commit();
        mSlideHolder.toggle();
    }

    private SlideHolder mSlideHolder;
    private Button mBtnWeightList;
    private Button mBtnLineChart;
    private Button mBtnSettings;

    private Fragment mWeightListFragment;
    private Fragment mLineChartFragment;
    private Fragment mSettingsFragment;
}
