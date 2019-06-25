package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.ShaiXuanCityBean;

/**
 * @author: lujialei
 * @date: 2019/6/25
 * @describe:
 */


public class AreaShaiXuanAdapter extends BaseQuickAdapter<ShaiXuanCityBean.AreaListBean,BaseViewHolder> {
    public AreaShaiXuanAdapter(@Nullable List<ShaiXuanCityBean.AreaListBean> data) {
        super(R.layout.item_city,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShaiXuanCityBean.AreaListBean item) {
        TextView textView = helper.getView(R.id.tv);
        textView.setText(item.getArea_name());
    }
}
