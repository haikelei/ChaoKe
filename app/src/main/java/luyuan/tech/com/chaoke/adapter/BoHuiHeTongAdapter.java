package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.WeiTuoHeTongBean;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class BoHuiHeTongAdapter extends BaseQuickAdapter<WeiTuoHeTongBean,BaseViewHolder> {
    public BoHuiHeTongAdapter(@Nullable List<WeiTuoHeTongBean> data) {
        super(R.layout.layout_weituohetong_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeiTuoHeTongBean item) {
        TextView tvTitle = helper.getView(R.id.tv_title);
        TextView tvTime = helper.getView(R.id.tv_time);
        TextView tvState = helper.getView(R.id.tv_state);
        tvTitle.setText(item.getWuye_address());
        tvTime.setText(item.getCreatetime());
        String s = "已驳回";
//        if (item.getState()==1){
//            s = "";
//        }else if (item.getState()==2){
//            s = "有效";
//        }else if (item.getState()==3){
//            s = "超时失效";
//        }else if (item.getState()==4){
//            s = "到期失效";
//        }else if (item.getState()==5){
//            s = "手动作废";
//        }
        tvState.setText(s);
        //	1为待签约 2为有效 3为超时失效 4为到期失效 5为后台手动作废

    }
}
