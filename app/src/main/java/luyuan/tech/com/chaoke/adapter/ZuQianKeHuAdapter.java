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
import luyuan.tech.com.chaoke.bean.HouseBean;
import luyuan.tech.com.chaoke.bean.ZuKeDetailBean;
import luyuan.tech.com.chaoke.bean.ZuKeListBean;

/**
 * @author: lujialei
 * @date: 2019/6/17
 * @describe:
 */


public class ZuQianKeHuAdapter extends BaseQuickAdapter<ZuKeListBean,BaseViewHolder> {
    public ZuQianKeHuAdapter(@Nullable List<ZuKeListBean> data) {
        super(R.layout.item_zuqiankehu,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZuKeListBean item) {
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvOther = helper.getView(R.id.tv_other);
        TextView tvMoney = helper.getView(R.id.tv_money);
        tvName.setText(item.getUsername());
        tvMoney.setText(item.getRent_min());
        tvOther.setText(item.getCity_name()+"  "+item.getRoom()+"-"+item.getOffice()+"-"+item.getToilet());
    }
}
