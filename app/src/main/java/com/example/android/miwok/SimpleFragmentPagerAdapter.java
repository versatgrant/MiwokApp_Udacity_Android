package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Nicola on 5/12/2017.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    public SimpleFragmentPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1){
            return new FamilyMembersFragment();
        } else if (position == 2){
            return new ColorsFragment();
        }else{
            return new PhrasesFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        if (position == 0) {
            return new NumbersFragment().tabTitle;
        } else if (position == 1){
            return new FamilyMembersFragment().tabTitle;
        } else if (position == 2){
            return new ColorsFragment().tabTitle;
        }else{
            return new PhrasesFragment().tabTitle;
        }
    }

}
