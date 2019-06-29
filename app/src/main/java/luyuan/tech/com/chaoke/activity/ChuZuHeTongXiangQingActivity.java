package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.net.Uri;
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
import luyuan.tech.com.chaoke.bean.ChuZuDetailBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/26
 * @describe:
 */


public class ChuZuHeTongXiangQingActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_wuyedizhi)
    TextView tvWuyedizhi;
    @BindView(R.id.tv_chuzuleixing)
    TextView tvChuzuleixing;
    @BindView(R.id.tv_chengzuleibie)
    TextView tvChengzuleibie;
    @BindView(R.id.tv_tijiaoriqi)
    TextView tvTijiaoriqi;
    @BindView(R.id.tv_hetongzhuangtai)
    TextView tvHetongzhuangtai;
    @BindView(R.id.tv_chengzuqisuan)
    TextView tvChengzuqisuan;
    @BindView(R.id.tv_chengzudaoqi)
    TextView tvChengzudaoqi;
    @BindView(R.id.tv_qianyueriqi)
    TextView tvQianyueriqi;
    @BindView(R.id.tv_shoucifukuan)
    TextView tvShoucifukuan;
    @BindView(R.id.tv_fukuanfangshi)
    TextView tvFukuanfangshi;
    @BindView(R.id.tv_fukuanzhouqi)
    TextView tvFukuanzhouqi;
    @BindView(R.id.tv_shuifeichengdan)
    TextView tvShuifeichengdan;
    @BindView(R.id.tv_qianyuegongsi)
    TextView tvQianyuegongsi;
    @BindView(R.id.tv_hetongbianhao)
    TextView tvHetongbianhao;
    @BindView(R.id.tv_hetongbeizhu)
    TextView tvHetongbeizhu;
    @BindView(R.id.tv_xingming)
    TextView tvXingming;
    @BindView(R.id.tv_xingbie)
    TextView tvXingbie;
    @BindView(R.id.tv_nianling)
    TextView tvNianling;
    @BindView(R.id.tv_xingzuo)
    TextView tvXingzuo;
    @BindView(R.id.tv_chengzuriqi)
    TextView tvChengzuriqi;
    @BindView(R.id.tv_weituoqisuanri)
    TextView tvWeituoqisuanri;
    @BindView(R.id.tv_weituodaoqiri)
    TextView tvWeituodaoqiri;
    @BindView(R.id.tv_hetongbianhao1)
    TextView tvHetongbianhao1;
    @BindView(R.id.tv_hetongbeizhu1)
    TextView tvHetongbeizhu1;
    @BindView(R.id.tv_name0)
    TextView tvName0;
    @BindView(R.id.iv_tel0)
    ImageView ivTel0;
    @BindView(R.id.tv_name1)
    TextView tvName1;
    @BindView(R.id.iv_tel1)
    ImageView ivTel1;
    @BindView(R.id.tv_name2)
    TextView tvName2;
    @BindView(R.id.iv_tel2)
    ImageView ivTel2;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuzuhetongxiangqing);
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
        HttpManager.post(HttpManager.CHUZUHETONG_XIANGQING)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("id", id)
                .execute(new SimpleCallBack<ChuZuDetailBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(ChuZuDetailBean data) {
                        fillData(data);
                    }
                });
    }

    private void fillData(final ChuZuDetailBean data) {
        //基础信息
        tvWuyedizhi.setText("物业地址:" + data.getBase_msg().getAddress());
        tvChuzuleixing.setText("出租类型:" + data.getBase_msg().getMode());
        tvChengzuleibie.setText("承租类别:" + data.getBase_msg().getCate());
        tvTijiaoriqi.setText("提交日期:" + data.getBase_msg().getCreatetime());
        tvHetongzhuangtai.setText("合同状态:" + data.getBase_msg().getState());
        //合同信息
        tvChengzuqisuan.setText("承租起算:" + data.getContract_msg().getLessee_starttime());
        tvChengzudaoqi.setText("承租到期:" + data.getContract_msg().getLessee_endtime());
        tvQianyueriqi.setText("签约日期:" + data.getContract_msg().getSubtime());
        tvShoucifukuan.setText("首次付款:" + data.getContract_msg().getFirst_paytime());
        tvFukuanfangshi.setText("付款方式:" + data.getContract_msg().getPay_type());
        tvFukuanzhouqi.setText("付款周期:" + data.getContract_msg().getPayway());
        tvShuifeichengdan.setText("税费承担:" + data.getContract_msg().getTaxation());
        tvQianyuegongsi.setText("签约公司:" + data.getContract_msg().getCompany());
        tvHetongbianhao.setText("合同编号:" + data.getContract_msg().getIdentifier());
        tvHetongbeizhu.setText("合同备注:" + data.getContract_msg().getSide_letter());
        //承租信息
        tvXingming.setText("姓名:" + data.getLessee().getUsername());
        tvXingbie.setText("性别:" + data.getLessee().getSex());
        tvNianling.setText("年龄:" + data.getLessee().getAge());
        tvChengzuriqi.setText("承租日期:" + data.getLessee().getLessee_starttime());
        //入住人信息(接口没有)

        //委托信息
        tvWeituoqisuanri.setText("委托起算:" + data.getEntrust().getLessee_starttime());
        tvWeituodaoqiri.setText("委托到期:" + data.getEntrust().getLessee_endtime());
        tvHetongbianhao1.setText("合同编号:" + data.getEntrust().getIdentifier());
        tvHetongbeizhu1.setText("合同备注:" + data.getEntrust().getSide_letter());
        //角色人信息
        tvName0.setText("姓名:"+data.getRole().get(0).getUsername());
        ivTel0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone(data.getRole().get(0).getPhone());
            }
        });
        tvName1.setText("姓名:"+data.getRole().get(1).getUsername());
        ivTel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone(data.getRole().get(1).getPhone());
            }
        });
        tvName2.setText("姓名:"+data.getRole().get(2).getUsername());
        ivTel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone(data.getRole().get(2).getPhone());
            }
        });
    }


    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
}
