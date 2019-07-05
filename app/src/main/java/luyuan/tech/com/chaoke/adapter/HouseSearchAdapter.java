package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.XiaoQuBean;

/**
 * @author: lujialei
 * @date: 2019/6/17
 * @describe:
 */


public class HouseSearchAdapter extends BaseQuickAdapter<XiaoQuBean,BaseViewHolder> {
    public HouseSearchAdapter(@Nullable List<XiaoQuBean> data) {
        super(R.layout.item_kaifa_xiaoqu,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, XiaoQuBean item) {
        TextView tvName = helper.getView(R.id.tv_name);
        tvName.setText(item.room_name);
    }
}
