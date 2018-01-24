package com.themodernbit.projecthumane.Beta;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.themodernbit.projecthumane.LevelFragment;

/**
 * Created by antho on 1/24/2018.
 */

public class FragmentAdapter  extends FragmentPagerAdapter{

    public FragmentAdapter(FragmentManager fm){
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LevelFragment();
            case 1:
                Fragment theFragment = new LevelFragment();

                return new LevelFragment();
            case 2:
                return new LevelFragment();


        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return "My Courses";
            case 1:
                return "Featured";
            case 2:
                return "Tests";

                default:
                return null;
        }
    }


}
