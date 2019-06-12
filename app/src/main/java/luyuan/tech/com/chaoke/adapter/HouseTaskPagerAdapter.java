package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import luyuan.tech.com.chaoke.fragment.DaiGenJinFragment;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class HouseTaskPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> titles;
    private List<Fragment> fragments;

    public HouseTaskPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        fragments.add(new DaiGenJinFragment());
        fragments.add(new DaiGenJinFragment());
        fragments.add(new DaiGenJinFragment());
        titles.add("待跟进");
        titles.add("初勘探");
        titles.add("待委托");
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
