package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import luyuan.tech.com.chaoke.bean.ShouFangJiaoGeBean;
import luyuan.tech.com.chaoke.bean.ShouFangJiaoGeListBean;
import luyuan.tech.com.chaoke.fragment.ChuFangJiaoGeFragment;
import luyuan.tech.com.chaoke.fragment.ShouFangJiaoGeFragment;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class ChuFangJiaoGePagerAdapter extends FragmentStatePagerAdapter {
    private List<String> titles;
    private List<Fragment> fragments;

    public static final int TYPE_YIJIAOGE = 1;
    public static final int TYPE_WEIJIAOGE = 2;
    public ChuFangJiaoGePagerAdapter(FragmentManager fm, ShouFangJiaoGeBean data) {
        super(fm);
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        fragments.add(ChuFangJiaoGeFragment.newInstance((ArrayList<ShouFangJiaoGeListBean>) data.getList(),TYPE_WEIJIAOGE));
        fragments.add(ChuFangJiaoGeFragment.newInstance((ArrayList<ShouFangJiaoGeListBean>) data.getList1(),TYPE_YIJIAOGE));
        titles.add("未交割");
        titles.add("已交割");
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
