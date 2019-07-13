package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.MingXiBean;
import luyuan.tech.com.chaoke.bean.ZuKeDetailBean;

/**
 * @author: lujialei
 * @date: 2019/6/25
 * @describe:
 */


public class MingXiAdapter extends BaseQuickAdapter<MingXiBean.MonthDataBean,BaseViewHolder> {
    public MingXiAdapter(@Nullable List<MingXiBean.MonthDataBean> data) {
        super(R.layout.item_mingxi,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MingXiBean.MonthDataBean item) {
        TextView tvTime = helper.getView(R.id.tv_time);
        TextView tvOther = helper.getView(R.id.tv_other);
        TextView tvAddress = helper.getView(R.id.tv_address);
        tvTime.setText(item.getCreatetime());
        tvAddress.setText(item.getWuye_address());
    }
}
