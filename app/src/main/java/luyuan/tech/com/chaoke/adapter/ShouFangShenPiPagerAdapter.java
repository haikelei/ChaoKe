package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import luyuan.tech.com.chaoke.fragment.DaiGenJinFragment;
import luyuan.tech.com.chaoke.fragment.ShouFangDaiChuShenFragment;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class ShouFangShenPiPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> titles;
    private List<Fragment> fragments;

    public ShouFangShenPiPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        fragments.add(new ShouFangDaiChuShenFragment());
        fragments.add(new ShouFangDaiChuShenFragment());
        fragments.add(new ShouFangDaiChuShenFragment());
        titles.add("待初审");
        titles.add("待复审");
        titles.add("审核通过");
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
