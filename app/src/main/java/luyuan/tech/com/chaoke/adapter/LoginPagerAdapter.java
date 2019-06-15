package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import luyuan.tech.com.chaoke.fragment.LoginMIMaFragment;
import luyuan.tech.com.chaoke.fragment.LoginYanzhengmaFragment;

/**
 * @author: lujialei
 * @date: 2019/6/15
 * @describe:
 */


public class LoginPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> titleList;
    public LoginPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        fragmentList.add(new LoginMIMaFragment());
        fragmentList.add(new LoginYanzhengmaFragment());
        titleList = new ArrayList<>();
        titleList.add("密码登录");
        titleList.add("验证码登录");
    }


    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
