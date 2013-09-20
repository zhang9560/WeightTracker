package app.weight.tracker;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.viewpagerindicator.TitlePageIndicator;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        getActionBar().setDisplayShowHomeEnabled(false);

        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), this);
        viewPager.setAdapter(viewPagerAdapter);

        TitlePageIndicator pageIndicator = (TitlePageIndicator)findViewById(R.id.page_indicator);
        pageIndicator.setViewPager(viewPager);
        pageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) { // LineChartFragment
                    ((LineChartFragment)viewPagerAdapter.getItem(position)).updateChart();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
