package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import luyuan.tech.com.chaoke.fragment.ChuZuHeTongFragment;
import luyuan.tech.com.chaoke.fragment.ShouFangDaiChuShenFragment;
import luyuan.tech.com.chaoke.fragment.WeiTuoHeTongFragment;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class HeTongTiaoKuanPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> titles;
    private List<Fragment> fragments;

    public HeTongTiaoKuanPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        fragments.add(new WeiTuoHeTongFragment());
        fragments.add(new ChuZuHeTongFragment());
        titles.add("委托合同");
        titles.add("出租合同");
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
