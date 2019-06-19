package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.ClientTaskBean;
import luyuan.tech.com.chaoke.bean.HouseBean;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class QianYueFangYuanAdapter extends BaseQuickAdapter<HouseBean,BaseViewHolder> {
    public QianYueFangYuanAdapter(@Nullable List<HouseBean> data) {
        super(R.layout.item_kaifa_fangyuan,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseBean item) {
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvOther = helper.getView(R.id.tv_other);
        TextView tvMoney = helper.getView(R.id.tv_money);
        tvName.setText(item.getRoom_name());
        tvOther.setText(item.getArea());
        tvMoney.setText(item.getLong_price());
    }
}
