package app.weight.tracker;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mViewPager = (ViewPager)findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), this);
        mViewPager.setAdapter(viewPagerAdapter);

        TabPageIndicator pageIndicator = (TabPageIndicator)findViewById(R.id.page_indicator);
        pageIndicator.setViewPager(mViewPager);
    }

    private ViewPager mViewPager;
}
