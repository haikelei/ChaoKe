package luyuan.tech.com.chaoke.activity;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.HousePeiZhiAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.HouseDetailBean;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;

/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class XianChangDaiKanActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_jushi)
    TextView tvJushi;
    @BindView(R.id.tv_size)
    TextView tvSize;
    @BindView(R.id.tv_fangyuanzhuangtai)
    TextView tvFangyuanzhuangtai;
    @BindView(R.id.tv_chaoxiang)
    TextView tvChaoxiang;
    @BindView(R.id.tv_zhuangxiuqingkuang)
    TextView tvZhuangxiuqingkuang;
    @BindView(R.id.tv_louceng)
    TextView tvLouceng;
    @BindView(R.id.tv_wuyeyongtu)
    TextView tvWuyeyongtu;
    @BindView(R.id.tv_fankangzhuangtai)
    TextView tvFankangzhuangtai;
    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.recycler_peizhi)
    RecyclerView recyclerPeizhi;
    private String id;
    private AMap aMap;
    private HousePeiZhiAdapter housePeiZhiAdapter;
    private ArrayList<HouseDetailBean.ConfigureBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qianyue_daikan);
        ButterKnife.bind(this);
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        mMapView.onCreate(savedInstanceState);
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
        }
        initView();
        loadData();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), XianChangDaiKanTwoActivity.class);
                intent.putExtra("id", id);
                if (data!=null){
                    intent.putExtra("address",data.getAdd_details());
                }
                startActivity(intent);
            }
        });

        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.centerCrop();
                Glide.with(context).load(path).apply(requestOptions).into(imageView);
            }
        });
        initMap();
    }


    private void initMap() {
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {

            }
        });
    }

    private void loadData() {
        if (!checkEmptyInfo()) {
            return;
        }
        HttpManager.post(HttpManager.HOUSE_DETAIL)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("id", id)
                .execute(new SimpleCallBack<HouseDetailBean>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(HouseDetailBean data) {
                        fillData(data);
                    }
                });
    }

    private HouseDetailBean data;
    private void fillData(HouseDetailBean data) {
        this.data = data;
        if (data.getPics() != null) {
            banner.setImages(data.getPics()).start();
        }
        if (!TextUtils.isEmpty(data.getRoom_name())) {
            tvName.setText(data.getRoom_name());
        }
        if (!TextUtils.isEmpty(data.getLong_price())) {
            tvMoney.setText(data.getLong_price());
        }
        if (!TextUtils.isEmpty(data.getApartment())) {
            tvJushi.setText(data.getApartment());
        }
        if (!TextUtils.isEmpty(data.getArea())) {
            tvSize.setText(data.getArea());
        }
//        tvFangyuanzhuangtai.setText();
        //朝向 1朝南 2为朝北 3为朝东 4为朝西
        int ori = data.getOrientation();
        String s = "朝南";
        if (ori == 1) {
            s = "朝南";
        } else if (ori == 2) {
            s = "朝北";
        } else if (ori == 3) {
            s = "朝东";
        } else if (ori == 4) {
            s = "朝西";
        }
        tvChaoxiang.setText("朝向:" + s);
//        tvZhuangxiuqingkuang.setText();
        if (!TextUtils.isEmpty(data.getFloor())) {
            tvLouceng.setText("楼层:" + data.getFloor());
        }
//        tvWuyeyongtu.setText();
//        tvFankangzhuangtai.setText();
        bindRecycler(data.getConfigure());
    }

    private void bindRecycler(List<HouseDetailBean.ConfigureBean> configure) {
        if (configure==null){
            return;
        }
        list.clear();
        list.addAll(configure);
        housePeiZhiAdapter.notifyDataSetChanged();
    }

    private void initView() {
        recyclerPeizhi.setLayoutManager(new GridLayoutManager(getActivity(),5));
        list = new ArrayList<>();
        housePeiZhiAdapter = new HousePeiZhiAdapter(list);
        recyclerPeizhi.setAdapter(housePeiZhiAdapter);
    }
}
