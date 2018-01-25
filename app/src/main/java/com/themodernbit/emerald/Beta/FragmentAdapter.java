package com.themodernbit.emerald.Beta;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.themodernbit.emerald.LevelFragment;
import com.themodernbit.emerald.UserFragment;

/**
 * Created by antho on 1/24/2018.
 */

public class FragmentAdapter  extends FragmentPagerAdapter{
private static String KEY_TABNUMBER = "KeyNumber";

    public FragmentAdapter(FragmentManager fm){
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new UserFragment();
            case 1:
                Bundle args = new Bundle();
                args.putInt(KEY_TABNUMBER, 1);
                LevelFragment fragment = new LevelFragment();
                fragment.setArguments(args);
                return fragment;
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
                return "Scenarios";
            case 2:
                return "Tests";

                default:
                return null;
        }
    }


}
