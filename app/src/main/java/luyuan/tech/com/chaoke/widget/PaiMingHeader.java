package luyuan.tech.com.chaoke.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.GeRenYeJiBean;

/**
 * @author: lujialei
 * @date: 2019/7/13
 * @describe:
 */


public class PaiMingHeader extends RelativeLayout {
    @BindView(R.id.iv_avatar1)
    ImageView ivAvatar1;
    @BindView(R.id.tv_name1)
    TextView tvName1;
    @BindView(R.id.tv_zu1)
    TextView tvZu1;
    @BindView(R.id.tv_other1)
    TextView tvOther1;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_zu)
    TextView tvZu;
    @BindView(R.id.tv_other)
    TextView tvOther;
    @BindView(R.id.iv_avatar2)
    ImageView ivAvatar2;
    @BindView(R.id.tv_name2)
    TextView tvName2;
    @BindView(R.id.tv_zu2)
    TextView tvZu2;
    @BindView(R.id.tv_other2)
    TextView tvOther2;

    public PaiMingHeader(Context context) {
        super(context);
        initView(context);
    }

    public PaiMingHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PaiMingHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_header_paiming, this, true);
        ButterKnife.bind(this, view);
    }

    public void setData(GeRenYeJiBean item, int i) {
        if (i == 0) {
            tvName.setText(item.getNickname());
            tvOther.setText(item.getRes_count()+"");
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.error(R.mipmap.default_image);
            Glide.with(getContext()).load(item.getHeadimgurl()).apply(requestOptions).into(ivAvatar);
            tvZu.setText(item.getStore_name());
        } else if (i == 1) {
            tvName1.setText(item.getNickname());
            tvOther1.setText(item.getRes_count()+"");
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.error(R.mipmap.default_image);
            Glide.with(getContext()).load(item.getHeadimgurl()).apply(requestOptions).into(ivAvatar1);
            tvZu1.setText(item.getStore_name());
        } else if (i == 2) {
            tvName2.setText(item.getNickname());
            tvOther2.setText(item.getRes_count()+"");
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.error(R.mipmap.default_image);
            Glide.with(getContext()).load(item.getHeadimgurl()).apply(requestOptions).into(ivAvatar2);
            tvZu2.setText(item.getStore_name());
        }
    }
}
