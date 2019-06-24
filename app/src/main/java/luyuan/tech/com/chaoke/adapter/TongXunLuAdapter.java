package luyuan.tech.com.chaoke.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.TongXunLuBean;

/**
 * @author: lujialei
 * @date: 2019/6/24
 * @describe:
 */


public class TongXunLuAdapter extends BaseQuickAdapter<TongXunLuBean,BaseViewHolder> {
    public TongXunLuAdapter(@Nullable List<TongXunLuBean> data) {
        super(R.layout.item_tongxunlu,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final TongXunLuBean item) {
        TextView tvName = helper.getView(R.id.tv_name);
        TextView ivCall = helper.getView(R.id.iv_call);
        tvName.setText(item.getNickname());
        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + item.getPhone());
                intent.setData(data);
                view.getContext().startActivity(intent);
            }
        });
    }

}
