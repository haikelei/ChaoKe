package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.ShouFangJiaoGeListBean;
import luyuan.tech.com.chaoke.bean.ShouFangShenPiBean;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class ShouFangJiaoGeAdapter extends BaseQuickAdapter<ShouFangJiaoGeListBean,BaseViewHolder> {
    public ShouFangJiaoGeAdapter(@Nullable List<ShouFangJiaoGeListBean> data) {
        super(R.layout.layout_shoufangjiaoge_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShouFangJiaoGeListBean item) {
        TextView tvTitle = helper.getView(R.id.tv_title);
        TextView tvOther = helper.getView(R.id.tv_other);
        tvTitle.setText(item.getWuye_address());
        tvOther.setText(item.getCreatetime());
    }
}
