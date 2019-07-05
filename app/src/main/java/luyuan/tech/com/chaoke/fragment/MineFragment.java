package luyuan.tech.com.chaoke.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.activity.GeRenTuiGuangActivity;
import luyuan.tech.com.chaoke.activity.GeRenXiaoLiangPaiMingActivity;
import luyuan.tech.com.chaoke.activity.GeRenYeJiPaiMingActivity;
import luyuan.tech.com.chaoke.activity.SettingsActivity;
import luyuan.tech.com.chaoke.activity.TuiGuangActivity;
import luyuan.tech.com.chaoke.activity.YiJianFanKuiActivity;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class MineFragment extends Fragment {
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_other)
    TextView tvOther;
    @BindView(R.id.iv_settings)
    ImageView ivSettings;
    @BindView(R.id.rl_app_tuiguang)
    RelativeLayout rlAppTuiguang;
    @BindView(R.id.rl_yijianfankui)
    RelativeLayout rlYijianfankui;
    @BindView(R.id.rl_gerentuiguang)
    RelativeLayout rlGerentuiguang;
    Unbinder unbinder;
    @BindView(R.id.rl_gerenyeji)
    RelativeLayout rlGerenyeji;
    @BindView(R.id.rl_gerenxiaoliang)
    RelativeLayout rlGerenxiaoliang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.mipmap.default_image);
        Glide.with(getActivity()).load(UserInfoUtils.getInstance().getAvatar()).apply(requestOptions).into(ivAvatar);
        tvName.setText(UserInfoUtils.getInstance().getUserName());
        ivSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
            }
        });
        rlAppTuiguang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TuiGuangActivity.class));
            }
        });
        rlGerentuiguang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), GeRenTuiGuangActivity.class));
            }
        });
        rlYijianfankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), YiJianFanKuiActivity.class));
            }
        });
        rlGerenxiaoliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), GeRenXiaoLiangPaiMingActivity.class));
            }
        });
        rlGerenyeji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), GeRenYeJiPaiMingActivity.class));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
