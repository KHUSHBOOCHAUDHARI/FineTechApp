package com.fintech.potstest.Fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class JacpotPagerAdapter extends FragmentStatePagerAdapter {
    public JacpotPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new JackpotFirstFragment();
            case 1:
                return new JackpotFirstFragment();
            case 2:
                return new JackpotFirstFragment();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return 3;
    }}
