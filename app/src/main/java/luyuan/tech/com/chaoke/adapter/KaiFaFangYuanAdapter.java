package luyuan.tech.com.chaoke.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.bean.HouseBean;

/**
 * @author: lujialei
 * @date: 2019/6/17
 * @describe:
 */


public class KaiFaFangYuanAdapter extends BaseQuickAdapter<HouseBean,BaseViewHolder> {
    public KaiFaFangYuanAdapter(@Nullable List<HouseBean> data) {
        super(R.layout.item_kaifa_fangyuan,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseBean item) {
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvOther = helper.getView(R.id.tv_other);
        TextView tvMoney = helper.getView(R.id.tv_money);
        TextView tvQuyu = helper.getView(R.id.tv_quyu);
        TextView tvzhuangtai = helper.getView(R.id.tv_zhuangtai);
        ImageView iv = helper.getView(R.id.iv);
        tvName.setText(item.getRoom_name());
        StringBuilder stringBuilder  = new StringBuilder();
        stringBuilder
                .append(item.getApartment()+"|")
                .append(item.getArea()+"m²|")
                .append(getOrientation(item.getOrientation())+"|")
                .append(getFitUp(item.getFit_up()));

        tvOther.setText(stringBuilder.toString());
        tvMoney.setText(item.getLong_price()+"元/月");
        RequestOptions options = new RequestOptions().centerCrop().error(R.mipmap.default_image);
        Glide.with(helper.itemView.getContext()).load(item.getCover()).apply(options).into(iv);
        tvQuyu.setText(item.getRegion());
        tvzhuangtai.setText(getStatus(item.getType()));
    }

    public String getOrientation(int i) {
//        朝向 1朝南 2为朝北 3为朝东 4为朝西
        if (i == 1) {
            return "朝南";
        } else if (i == 2) {
            return "朝北";
        } else if (i == 3) {
            return "朝东";
        } else if (i == 4) {
            return "朝西";
        }
        return "朝南";
    }

    public String getFitUp(int i){
//        {"毛坯", "简装", "精装配置齐全", "精装配置不齐全", "豪华装修"};
        if (i == 1) {
            return "毛坯";
        } else if (i == 2) {
            return "简装";
        } else if (i == 3) {
            return "精装配置齐全";
        } else if (i == 4) {
            return "精装配置不齐全";
        }else if (i == 5) {
            return "豪华装修";
        }
        return "毛坯";
    }

    public String getStatus(int i){
//    仅长租", "仅短租", "都可租
        if (i == 1) {
            return "仅长租";
        } else if (i == 2) {
            return "仅短租";
        } else if (i == 3) {
            return "都可租";
        }
        return "仅长租";
    }


}
