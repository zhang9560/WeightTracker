package app.weight.tracker;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static final String[] TITLES = new String[3];

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        TITLES[0] = context.getString(R.string.weight_list);
        TITLES[1] = context.getString(R.string.line_chart);
        TITLES[2] = context.getString(R.string.settings);

        mFragments = new Fragment[3];
        mFragments[0] = Fragment.instantiate(context, WeightListFragment.class.getName(), null);
        mFragments[1] = Fragment.instantiate(context, LineChartFragment.class.getName(), null);
        mFragments[2] = Fragment.instantiate(context, SettingsFragment.class.getName(), null);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments[i];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    private Fragment[] mFragments;
}
