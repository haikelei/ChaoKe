package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.CityBean;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class QuYuLeftAdapter extends BaseQuickAdapter<CityBean,BaseViewHolder> {
    public QuYuLeftAdapter(@Nullable List<CityBean> data) {
        super(R.layout.layout_item_quyu_left,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CityBean item) {
        TextView textView = helper.getView(R.id.tv);
        textView.setText(item.getCity_name());
    }
}
