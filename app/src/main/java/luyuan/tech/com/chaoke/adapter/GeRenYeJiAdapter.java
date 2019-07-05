package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.GeRenYeJiBean;
import luyuan.tech.com.chaoke.bean.WeiTuoHeTongBean;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class GeRenYeJiAdapter extends BaseQuickAdapter<GeRenYeJiBean,BaseViewHolder> {
    public GeRenYeJiAdapter(@Nullable List<GeRenYeJiBean> data) {
        super(R.layout.layout_gerenyeji_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GeRenYeJiBean item) {
        TextView tvName = helper.getView(R.id.tv_title);
        TextView tvMoney = helper.getView(R.id.tv_money);
        TextView tvZu = helper.getView(R.id.tv_zu);
        ImageView ivAvatar = helper.getView(R.id.iv_avatar);
        tvName.setText(item.getNickname());
        tvMoney.setText(item.getRes_count()+"");
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.mipmap.default_image);
        Glide.with(helper.itemView.getContext()).load(item.getHeadimgurl()).apply(requestOptions).into(ivAvatar);
        tvZu.setText(item.getStore_name());

    }
}
