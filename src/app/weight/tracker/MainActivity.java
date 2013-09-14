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

        mViewPager = (ViewPager)findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), this);
        mViewPager.setAdapter(viewPagerAdapter);

        TitlePageIndicator pageIndicator = (TitlePageIndicator)findViewById(R.id.page_indicator);
        pageIndicator.setViewPager(mViewPager);
    }

    private ViewPager mViewPager;
}
