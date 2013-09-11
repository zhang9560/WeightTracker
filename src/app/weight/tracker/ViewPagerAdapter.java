package app.weight.tracker;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        mFragments = new Fragment[2];
        mFragments[0] = Fragment.instantiate(context, WeightListFragment.class.getName(), null);
        mFragments[1] = Fragment.instantiate(context, LineChartFragment.class.getName(), null);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments[i];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }

    private Fragment[] mFragments;
}
