package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.ShouFangShenPiBean;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class DaiChuShenAdapter extends BaseQuickAdapter<ShouFangShenPiBean.DataBean,BaseViewHolder> {
    public DaiChuShenAdapter(@Nullable List<ShouFangShenPiBean.DataBean> data) {
        super(R.layout.layout_daichushen_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShouFangShenPiBean.DataBean item) {
        TextView tvTitle = helper.getView(R.id.tv_title);
        TextView tvOther = helper.getView(R.id.tv_other);
        tvTitle.setText(item.getRoom_name());
        tvOther.setText(item.getCreatetime());
    }
}
