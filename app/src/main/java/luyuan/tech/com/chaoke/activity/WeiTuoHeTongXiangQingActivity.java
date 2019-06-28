package luyuan.tech.com.chaoke.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.WeiTuoHeTongDetailBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/26
 * @describe:
 */


public class WeiTuoHeTongXiangQingActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_wuyedizhi)
    TextView tvWuyedizhi;
    @BindView(R.id.tv_fangyuanbianhao)
    TextView tvFangyuanbianhao;
    @BindView(R.id.tv_fangyuanlaiyuan)
    TextView tvFangyuanlaiyuan;
    @BindView(R.id.tv_qianyueleixing)
    TextView tvQianyueleixing;
    @BindView(R.id.tv_hetongbianhao)
    TextView tvHetongbianhao;
    @BindView(R.id.tv_qianyueriqi)
    TextView tvQianyueriqi;
    @BindView(R.id.tv_jiaofangriqi)
    TextView tvJiaofangriqi;
    @BindView(R.id.tv_gongyuxinxi)
    TextView tvGongyuxinxi;
    @BindView(R.id.tv_gaizaoxinxi)
    TextView tvGaizaoxinxi;
    @BindView(R.id.tv_hetongleixing)
    TextView tvHetongleixing;
    @BindView(R.id.tv_zhuangxiuqisuanri)
    TextView tvZhuangxiuqisuanri;
    @BindView(R.id.tv_zhuangxiujiezhiri)
    TextView tvZhuangxiujiezhiri;
    @BindView(R.id.tv_fukuanxinxi)
    TextView tvFukuanxinxi;
    @BindView(R.id.tv_fukuantianshu)
    TextView tvFukuantianshu;
    @BindView(R.id.tv_mianzutianshu)
    TextView tvMianzutianshu;
    @BindView(R.id.tv_weituoqisuanri)
    TextView tvWeituoqisuanri;
    @BindView(R.id.tv_weituodaoqiri)
    TextView tvWeituodaoqiri;
    @BindView(R.id.tv_shoucifukuanri)
    TextView tvShoucifukuanri;
    @BindView(R.id.tv_meiyuefangzu)
    TextView tvMeiyuefangzu;
    @BindView(R.id.tv_shifoudaichewei)
    TextView tvShifoudaichewei;
    @BindView(R.id.tv_nianfuwufei)
    TextView tvNianfuwufei;
    @BindView(R.id.tv_chanquandizhi)
    TextView tvChanquandizhi;
    @BindView(R.id.tv_jianzhumianji)
    TextView tvJianzhumianji;
    @BindView(R.id.tv_huxing)
    TextView tvHuxing;
    @BindView(R.id.tv_gongyouqingkuang)
    TextView tvGongyouqingkuang;
    @BindView(R.id.tv_yongtu)
    TextView tvYongtu;
    @BindView(R.id.tv_shifouyoufangdai)
    TextView tvShifouyoufangdai;
    @BindView(R.id.tv_chanquanrenleixing)
    TextView tvChanquanrenleixing;
    @BindView(R.id.tv_zhengjianleixing)
    TextView tvZhengjianleixing;
    @BindView(R.id.tv_xingming)
    TextView tvXingming;
    @BindView(R.id.tv_zhengjianhaoma)
    TextView tvZhengjianhaoma;
    @BindView(R.id.tv_zhengjianyouxiaori)
    TextView tvZhengjianyouxiaori;
    @BindView(R.id.tv_zhengjianjiezhiri)
    TextView tvZhengjianjiezhiri;
    @BindView(R.id.iv_zhengjian)
    ImageView ivZhengjian;
    @BindView(R.id.iv_zhengjian1)
    ImageView ivZhengjian1;
    @BindView(R.id.tv_chanquanzhengleixing)
    TextView tvChanquanzhengleixing;
    @BindView(R.id.tv_chanquanzhengbianhao)
    TextView tvChanquanzhengbianhao;
    @BindView(R.id.iv_daizhenghaoye)
    ImageView ivDaizhenghaoye;
    @BindView(R.id.iv_zhuye)
    ImageView ivZhuye;
    @BindView(R.id.iv_fujiye)
    ImageView ivFujiye;
    @BindView(R.id.iv_yuanhuxingtu)
    ImageView ivYuanhuxingtu;
    @BindView(R.id.iv_fenhutu)
    ImageView ivFenhutu;
    @BindView(R.id.tv_chanquanrenleixing1)
    TextView tvChanquanrenleixing1;
    @BindView(R.id.tv_zhengjianleixing1)
    TextView tvZhengjianleixing1;
    @BindView(R.id.tv_xingming1)
    TextView tvXingming1;
    @BindView(R.id.tv_zhengjianhaoma1)
    TextView tvZhengjianhaoma1;
    @BindView(R.id.tv_zhengjianyouxiaori1)
    TextView tvZhengjianyouxiaori1;
    @BindView(R.id.tv_zhengjianjiezhiri1)
    TextView tvZhengjianjiezhiri1;
    @BindView(R.id.iv_zhengjian2)
    ImageView ivZhengjian2;
    @BindView(R.id.iv_zhengjian3)
    ImageView ivZhengjian3;
    @BindView(R.id.tv_gongyouchanquanbianhao)
    TextView tvGongyouchanquanbianhao;
    @BindView(R.id.tv_zhengjianleixing2)
    TextView tvZhengjianleixing2;
    @BindView(R.id.tv_xingming3)
    TextView tvXingming3;
    @BindView(R.id.tv_zhengjianhaoma2)
    TextView tvZhengjianhaoma2;
    @BindView(R.id.tv_shoujihaoma1)
    TextView tvShoujihaoma1;
    @BindView(R.id.tv_youxiangdizhi)
    TextView tvYouxiangdizhi;
    @BindView(R.id.tv_tongxundizhi)
    TextView tvTongxundizhi;
    @BindView(R.id.tv_zhengjianleixing3)
    TextView tvZhengjianleixing3;
    @BindView(R.id.tv_xingming2)
    TextView tvXingming2;
    @BindView(R.id.tv_zhengjianhaom3)
    TextView tvZhengjianhaom3;
    @BindView(R.id.tv_shoujihaoma2)
    TextView tvShoujihaoma2;
    @BindView(R.id.tv_youxiangdizhi1)
    TextView tvYouxiangdizhi1;
    @BindView(R.id.tv_tongxundizhi1)
    TextView tvTongxundizhi1;
    @BindView(R.id.tv_shoukuanrenleixing)
    TextView tvShoukuanrenleixing;
    @BindView(R.id.tv_zhengjianleixing4)
    TextView tvZhengjianleixing4;
    @BindView(R.id.tv_xingming4)
    TextView tvXingming4;
    @BindView(R.id.tv_zhengjianhaoma3)
    TextView tvZhengjianhaoma3;
    @BindView(R.id.tv_zhanghaoleixing)
    TextView tvZhanghaoleixing;
    @BindView(R.id.tv_shoukuanzhanghao)
    TextView tvShoukuanzhanghao;
    @BindView(R.id.tv_shoukuanjigou)
    TextView tvShoukuanjigou;
    @BindView(R.id.tv_shoukuanzhihang)
    TextView tvShoukuanzhihang;
    @BindView(R.id.tv_xingming5)
    TextView tvXingming5;
    @BindView(R.id.tv_dianhua)
    TextView tvDianhua;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weituotongxiangqing);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        loadData();
    }

    private void loadData() {
        HttpManager.post(HttpManager.WEITUOHETONG_XIANGQING)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("id", id)
                .execute(new SimpleCallBack<WeiTuoHeTongDetailBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(WeiTuoHeTongDetailBean data) {
                        fillData(data);
                    }
                });
    }

    private void fillData(WeiTuoHeTongDetailBean data) {
        tvWuyedizhi.setText(data.getContract_data().getWuye_address());
        tvFangyuanbianhao.setText(data.getContract_data().getRent_num());
    }
}
