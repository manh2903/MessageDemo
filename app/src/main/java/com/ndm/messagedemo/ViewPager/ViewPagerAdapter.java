package com.ndm.messagedemo.ViewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ndm.messagedemo.Fragment.CallFragment;
import com.ndm.messagedemo.Fragment.ChatFragment;
import com.ndm.messagedemo.Fragment.HomeFragment;
import com.ndm.messagedemo.Fragment.PhoneBookFragment;
import com.ndm.messagedemo.Fragment.StoryFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new CallFragment();
            case 2:
                return new PhoneBookFragment();
            case 3:
                return new StoryFragment();
            default:
                return new ChatFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
