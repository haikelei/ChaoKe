package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import luyuan.tech.com.chaoke.fragment.LineFragment;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class LinePagerAdapter extends FragmentStatePagerAdapter {

    private List<String> titles;
    private List<Fragment> fragments;
    public LinePagerAdapter(FragmentManager fm) {
        super(fm);
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
        titles.add("进客");
        titles.add("出租");
        titles.add("委托");
        fragments.add(LineFragment.newInstance(1));
        fragments.add(LineFragment.newInstance(2));
        fragments.add(LineFragment.newInstance(3));
//        fragments.add(new LineFragment());
//        fragments.add(new LineFragment());
//        fragments.add(new LineFragment());
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
