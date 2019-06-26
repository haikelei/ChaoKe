package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.ChuZuHeTongBean;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class ChuZuHeTongAdapter extends BaseQuickAdapter<ChuZuHeTongBean.ListBean,BaseViewHolder> {
    public ChuZuHeTongAdapter(@Nullable List<ChuZuHeTongBean.ListBean> data) {
        super(R.layout.layout_weituohetong_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChuZuHeTongBean.ListBean item) {
        TextView tvTitle = helper.getView(R.id.tv_title);
        TextView tvTime = helper.getView(R.id.tv_time);
        TextView tvState = helper.getView(R.id.tv_state);
        tvTitle.setText(item.getAddress());
        tvTime.setText(item.getCreatetime());
        String s = "";
        if (item.getState()==1){
            s = "待签约";
        }else if (item.getState()==2){
            s = "有效";
        }else if (item.getState()==3){
            s = "超时失效";
        }else if (item.getState()==4){
            s = "到期失效";
        }else if (item.getState()==5){
            s = "手动作废";
        }
        tvState.setText(s);
        //	1为待签约 2为有效 3为超时失效 4为到期失效 5为后台手动作废

    }
}
