package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.HouseBean;

/**
 * @author: lujialei
 * @date: 2019/6/17
 * @describe:
 */


public class DuoXuanFangYuanAdapter extends BaseQuickAdapter<HouseBean,BaseViewHolder> {
    public DuoXuanFangYuanAdapter(@Nullable List<HouseBean> data) {
        super(R.layout.item_duoxuan_fangyuan,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseBean item) {
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvOther = helper.getView(R.id.tv_other);
        TextView tvMoney = helper.getView(R.id.tv_money);
        RadioButton rb = helper.getView(R.id.rb);
        ImageView iv = helper.getView(R.id.iv);
        tvName.setText(item.getRoom_name());
        tvOther.setText(item.getArea()+"m²");
        tvMoney.setText(item.getLong_price());
        rb.setChecked(item.checked);
        RequestOptions options = new RequestOptions().centerCrop().error(R.mipmap.default_image);
        Glide.with(helper.itemView.getContext()).load(item.getCover()).apply(options).into(iv);
    }
}
