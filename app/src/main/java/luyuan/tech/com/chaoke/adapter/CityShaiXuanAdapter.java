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


public class CityShaiXuanAdapter extends BaseQuickAdapter<ShaiXuanCityBean,BaseViewHolder> {
    public CityShaiXuanAdapter(@Nullable List<ShaiXuanCityBean> data) {
        super(R.layout.item_city,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShaiXuanCityBean item) {
        TextView textView = helper.getView(R.id.tv);
        textView.setText(item.getCity_name());
    }
}
