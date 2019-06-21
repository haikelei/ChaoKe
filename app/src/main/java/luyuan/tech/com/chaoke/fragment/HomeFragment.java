package luyuan.tech.com.chaoke.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.tablayout.SlidingTabLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.activity.AddHouseActivity;
import luyuan.tech.com.chaoke.activity.ClientTaskActivity;
import luyuan.tech.com.chaoke.activity.HeTongTiaoKuanActivity;
import luyuan.tech.com.chaoke.activity.HouseTaskActivity;
import luyuan.tech.com.chaoke.activity.KaiFaFangYuanActivity;
import luyuan.tech.com.chaoke.activity.ShouFangShenPiListActivity;
import luyuan.tech.com.chaoke.activity.WeiTuoHeTongActivity;
import luyuan.tech.com.chaoke.activity.ZiYingFangYuanActivity;
import luyuan.tech.com.chaoke.activity.ZuQianKeHuActivity;
import luyuan.tech.com.chaoke.adapter.LinePagerAdapter;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class HomeFragment extends Fragment {
    @BindView(R.id.iv_plus)
    ImageView ivPlus;
    @BindView(R.id.iv_sao)
    ImageView ivSao;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.banner_area)
    RelativeLayout bannerArea;
    Unbinder unbinder;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.mipmap.banner);
        banner.setImages(list).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                RequestOptions requestOptions = RequestOptions.centerCropTransform();
                Glide.with(context).load(path).apply(requestOptions).into(imageView);
            }
        }).start();

        viewPager.setAdapter(new LinePagerAdapter(getChildFragmentManager()));
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setViewPager(viewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_plus, R.id.iv_sao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_plus:
                startActivity(new Intent(getContext(), AddHouseActivity.class));
                break;
            case R.id.iv_sao:
                break;
        }
    }

    @OnClick({R.id.ll_kerenwu, R.id.ll_fangrenwu, R.id.ll_shoufangshenpi, R.id.ll_tongxunlu, R.id.ll_ziyingfangyuan, R.id.ll_zuqiankehu, R.id.ll_kaifafangyuan, R.id.ll_chuzuhetong, R.id.ll_weituohetong, R.id.ll_hetongtiaokua, R.id.ll_chufangjiaoge, R.id.ll_shoufangjiaoge})
    public void onViewClicke(View view) {
        switch (view.getId()) {
            case R.id.ll_kerenwu:
                startActivity(new Intent(getContext(), ClientTaskActivity.class));
                break;
            case R.id.ll_fangrenwu:
                startActivity(new Intent(getContext(), HouseTaskActivity.class));
                break;
            case R.id.ll_shoufangshenpi:
                startActivity(new Intent(getContext(), ShouFangShenPiListActivity.class));
                break;
            case R.id.ll_tongxunlu:
                break;
            case R.id.ll_ziyingfangyuan:
                startActivity(new Intent(getContext(), ZiYingFangYuanActivity.class));
                break;
            case R.id.ll_zuqiankehu:
                startActivity(new Intent(getContext(), ZuQianKeHuActivity.class));
                break;
            case R.id.ll_kaifafangyuan:
                startActivity(new Intent(getContext(), KaiFaFangYuanActivity.class));
                break;
            case R.id.ll_chuzuhetong:
                break;
            case R.id.ll_weituohetong:
                startActivity(new Intent(getContext(), WeiTuoHeTongActivity.class));
                break;
            case R.id.ll_hetongtiaokua:
                startActivity(new Intent(getContext(), HeTongTiaoKuanActivity.class));
                break;
            case R.id.ll_chufangjiaoge:
                break;
            case R.id.ll_shoufangjiaoge:
                break;
        }
    }
}
