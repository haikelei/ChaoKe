package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.StringDataResponse;
import luyuan.tech.com.chaoke.bean.WeiTuoHeTongDetailBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.net.NetParser;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/26
 * @describe:
 */


public class BoHuiWeiTuoHeTongXiangQingActivity extends BaseActivity {
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
    @BindView(R.id.iv_qita)
    ImageView ivQita;
    @BindView(R.id.rl_chongxinbianji)
    RelativeLayout rlChongxinbianji;
    private String id;
    private String totalId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bohuiweituotongxiangqing);
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
        rlChongxinbianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),FangYuanQianYueOneActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("total_id",totalId);
                startActivity(intent);
            }
        });
        loadHeTongUrl();
        loadData();
    }

    private String url;

    public void loadHeTongUrl() {
        HttpManager.post(HttpManager.WEITUOHETONGZHENGBEN)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("id", id)
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(String data) {
                        if (NetParser.isOk(data)) {
                            StringDataResponse response = NetParser.parse(data, StringDataResponse.class);
                            url = response.getData();
                        }
                    }
                });

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
                        totalId = data.getContract_data().getId()+"";
                        fillData(data);
                    }
                });
    }

    private void fillData(WeiTuoHeTongDetailBean data) {
        //合同信息
        tvWuyedizhi.setText(data.getContract_data().getWuye_address());
        tvFangyuanbianhao.setText(data.getContract_data().getRent_num());
        int laiyuan = data.getContract_data().getFrom_by();
        String laiyuanString = getLaiyuan(laiyuan);
        tvFangyuanlaiyuan.setText(laiyuanString);
//        为首签 2为续签
        tvQianyueleixing.setText(getType(data.getContract_data().getSigning_type()));
        tvHetongbianhao.setText(data.getContract_data().getIdentifier());
        tvQianyueriqi.setText(data.getContract_data().getSigning_time());
        tvJiaofangriqi.setText(data.getContract_data().getOver_time());
        //委托信息
        tvFukuanxinxi.setText(data.getEntrust().getPayment_msg() + "");
        tvFukuantianshu.setText(data.getEntrust().getPayment_day() + "");
        tvMianzutianshu.setText(data.getEntrust().getRent_day() + "");
        tvWeituoqisuanri.setText(data.getEntrust().getEntrust_begin());
        tvWeituodaoqiri.setText(data.getEntrust().getEntrust_end());
        //租金信息
        tvShoucifukuanri.setText(data.getRent().getFirst_pay());
        tvShifoudaichewei.setText(data.getRent().getIs_parking() == 1 ? "带车位" : "不带");
        tvMeiyuefangzu.setText(data.getRent().getRent_price());
        //租金策略
        data.getStrategy();

        //房产信息
        tvChanquandizhi.setText(data.getProperty().getProperty_address());
        tvJianzhumianji.setText(data.getProperty().getArea() + "");
        tvHuxing.setText(data.getProperty().getApartment());
        tvGongyouqingkuang.setText(data.getProperty().getShare_desc());
        tvYongtu.setText(data.getProperty().getUsed_type() + "");
        tvShifouyoufangdai.setText(data.getProperty().getIs_loan() == 1 ? "有" : "无");

        //产权人信息
        tvChanquanrenleixing.setText(data.getProperty1().getPeople_type() == 1 ? "个人" : "企业");
        tvZhengjianleixing.setText(data.getProperty1().getCard_type() + "");
        tvXingming.setText(data.getProperty1().getUsername());
        tvZhengjianhaoma.setText(data.getProperty1().getCard_num() + "");
        tvZhengjianyouxiaori.setText(data.getProperty1().getCard_begin());
        tvZhengjianjiezhiri.setText(data.getProperty1().getCard_end());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.mipmap.default_image);
        Glide.with(getActivity()).load(data.getProperty1().getCard_zpic()).apply(requestOptions).into(ivZhengjian);
        Glide.with(getActivity()).load(data.getProperty1().getCard_fpic()).apply(requestOptions).into(ivZhengjian1);
        tvChanquanzhengleixing.setText(data.getProperty1().getProperty_type() + "");
        tvChanquanzhengbianhao.setText(data.getProperty1().getProperty_num() + "");
        Glide.with(getActivity()).load(data.getProperty1().getNum_pic()).apply(requestOptions).into(ivDaizhenghaoye);
        Glide.with(getActivity()).load(data.getProperty1().getHome_pic()).apply(requestOptions).into(ivZhuye);
        Glide.with(getActivity()).load(data.getProperty1().getAttach_pic()).apply(requestOptions).into(ivFujiye);
        Glide.with(getActivity()).load(data.getProperty1().getOld_pic()).apply(requestOptions).into(ivYuanhuxingtu);
        Glide.with(getActivity()).load(data.getProperty1().getHousehold_pic()).apply(requestOptions).into(ivFenhutu);
        if (data.getProperty1().getOther_pic() != null && data.getProperty1().getOther_pic().size() > 0) {
            Glide.with(getActivity()).load(data.getProperty1().getOther_pic().get(0)).apply(requestOptions).into(ivQita);
        }

        //共有产权人信息
        tvChanquanrenleixing1.setText(data.getProperty2().getPeople_type() == 1 ? "个人" : "企业");
        tvZhengjianleixing1.setText(data.getProperty2().getCard_type() + "");
        tvXingming1.setText(data.getProperty2().getUsername());
        tvZhengjianhaoma1.setText(data.getProperty2().getCard_num() + "");
        tvZhengjianyouxiaori1.setText(data.getProperty2().getCard_begin());
        tvZhengjianjiezhiri1.setText(data.getProperty2().getCard_end());
        Glide.with(getActivity()).load(data.getProperty2().getCard_zpic()).apply(requestOptions).into(ivZhengjian2);
        Glide.with(getActivity()).load(data.getProperty2().getCard_fpic()).apply(requestOptions).into(ivZhengjian3);
        tvGongyouchanquanbianhao.setText(data.getProperty2().getProperty_num() + "");
        //出租人信息
        tvZhengjianleixing2.setText(data.getOut_people().getCard_type() + "");
        tvXingming2.setText(data.getOut_people().getUsername());
        tvZhengjianhaoma2.setText(data.getOut_people().getCard_num() + "");
        tvDianhua.setText(data.getOut_people().getPhone());
        tvYouxiangdizhi.setText(data.getOut_people().getEmail_address());
        tvTongxundizhi.setText(data.getOut_people().getComm_address());
        //共租人信息
        tvZhengjianleixing3.setText(data.getCommon_people().getCard_type() + "");
        tvXingming3.setText(data.getCommon_people().getUsername());
        tvZhengjianhaoma3.setText(data.getCommon_people().getCard_num() + "");
        tvYouxiangdizhi1.setText(data.getCommon_people().getEmail_address());
        tvTongxundizhi1.setText(data.getCommon_people().getComm_address());
        //收款人信息
        tvShoukuanrenleixing.setText(data.getPayee().getPeople_type() + "");
        tvZhengjianleixing4.setText(data.getPayee().getCard_type() + "");
        tvXingming4.setText(data.getPayee().getUsername() + "");
        tvZhengjianhaoma3.setText(data.getPayee().getCard_num() + "");
        tvZhanghaoleixing.setText(data.getPayee().getNumber_type() + "");
        tvShoukuanzhanghao.setText(data.getPayee().getNumber_num() + "");
        tvShoukuanjigou.setText(data.getPayee().getMechanism() + "");
        tvShoukuanzhihang.setText(data.getPayee().getBranch() + "");
        //紧急联系人
        tvXingming5.setText(data.getUrgent_people().getUsername());
        tvShoujihaoma2.setText(data.getUrgent_people().getPhone());


    }

    private String getType(String signing_type) {
        if (signing_type.equals("1")) {
            return "首签";
        } else if (signing_type.equals("2")) {
            return "续签";
        }
        return "续签";
    }

    public String getLaiyuan(int i) {
        if (i == 1) {
            return "中介合作";
        } else if (i == 2) {
            return "转介绍";
        } else if (i == 3) {
            return "老客户";
        } else if (i == 4) {
            return "网络端口";
        } else if (i == 5) {
            return "地推";
        } else if (i == 6) {
            return "房东上门";
        } else if (i == 7) {
            return "名单获取";
        } else if (i == 8) {
            return "销冠";
        }
        return "";
    }
}
