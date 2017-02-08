package edu.kiet.www.epoque2017;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs,s;


    public PagerAdapter(FragmentManager fm, int NumOfTabs) {

        super(fm);
        this.mNumOfTabs=NumOfTabs;

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragmentDepartment tab1 = new TabFragmentDepartment();
                return tab1;
            case 1:
                TabFragmentInterdepartment tab2 = new TabFragmentInterdepartment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
