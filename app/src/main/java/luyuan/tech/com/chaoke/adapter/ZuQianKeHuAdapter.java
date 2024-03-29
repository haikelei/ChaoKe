package luyuan.tech.com.chaoke.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.HouseBean;
import luyuan.tech.com.chaoke.bean.ZuKeDetailBean;
import luyuan.tech.com.chaoke.bean.ZuKeListBean;

/**
 * @author: lujialei
 * @date: 2019/6/17
 * @describe:
 */


public class ZuQianKeHuAdapter extends BaseQuickAdapter<ZuKeListBean,BaseViewHolder> {
    public ZuQianKeHuAdapter(@Nullable List<ZuKeListBean> data) {
        super(R.layout.item_zuqiankehu,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ZuKeListBean item) {
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvOther = helper.getView(R.id.tv_other);
        TextView tvMoney = helper.getView(R.id.tv_money);
        TextView tvLeixing = helper.getView(R.id.tv_leixing);
        ImageView tvTel = helper.getView(R.id.iv_tel);
        tvLeixing.setText(getLeixing(item.getGrade()));
        tvName.setText(item.getUsername());
        tvMoney.setText(item.getRent_min()+"-"+item.getRent_max());
        tvOther.setText(item.getCity_name()+"  "+item.getRoom()+"-"+item.getOffice()+"-"+item.getToilet());
        tvTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone(v.getContext(),item.getPhone());
            }
        });
    }

    public void callPhone(Context context,String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    private String getLeixing(int grade) {
        if (grade == 1) {
            return "A类";
        } else if (grade == 2) {
            return "B类";
        } else if (grade == 3) {
            return "C类";
        }
        return "其他";
    }
}
