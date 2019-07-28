package luyuan.tech.com.chaoke.adapter;

import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.ClientTaskBean;
import luyuan.tech.com.chaoke.bean.HouseBean;
import luyuan.tech.com.chaoke.bean.HouseStringBean;
import luyuan.tech.com.chaoke.utils.Constant;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class QianYueFangYuanAdapter extends BaseQuickAdapter<HouseStringBean,BaseViewHolder> {
    public QianYueFangYuanAdapter(@Nullable List<HouseStringBean> data) {
        super(R.layout.item_fangyuan_qianyue,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseStringBean item) {
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvOther = helper.getView(R.id.tv_other);
        TextView tvMoney = helper.getView(R.id.tv_money);
        ImageView iv = helper.getView(R.id.iv);
        tvName.setText(item.getRoom_name());
        tvOther.setText(item.getArea());
        if (!TextUtils.isEmpty(item.getLong_price())){
            tvMoney.setText(item.getLong_price()+"元/月");
        }
        RequestOptions options = new RequestOptions().centerCrop().error(R.mipmap.default_image);
        Glide.with(helper.itemView.getContext()).load(item.getCover()).apply(options).into(iv);
    }
}
