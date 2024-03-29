package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.ClientTaskBean;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class ClientTaskAdapter extends BaseQuickAdapter<ClientTaskBean,BaseViewHolder> {
    public ClientTaskAdapter(@Nullable List<ClientTaskBean> data) {
        super(R.layout.layout_client_task_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClientTaskBean item) {
        TextView tvName = helper.getView(R.id.tv_title);
        TextView tvOther = helper.getView(R.id.tv_other);
        tvName.setText(item.getUsername());
        tvOther.setText(item.getCreatetime());
    }
}
