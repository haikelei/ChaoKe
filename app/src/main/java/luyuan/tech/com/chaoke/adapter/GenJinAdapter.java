package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.ZuKeDetailBean;

/**
 * @author: lujialei
 * @date: 2019/6/25
 * @describe:
 */


public class GenJinAdapter extends BaseQuickAdapter<ZuKeDetailBean.FollowDataBean,BaseViewHolder> {
    public GenJinAdapter(@Nullable List<ZuKeDetailBean.FollowDataBean> data) {
        super(R.layout.item_zukexiangqing_genjin,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZuKeDetailBean.FollowDataBean item) {
        TextView tvTime = helper.getView(R.id.tv_time);
        TextView tvDesc = helper.getView(R.id.tv_desc);
        TextView tvTitle = helper.getView(R.id.tv_genjinleixing);
        tvTime.setText(item.getCreatetime());
        tvDesc.setText(item.getDesc());
    }
}
