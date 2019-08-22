package luyuan.tech.com.chaoke.activity;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
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
import luyuan.tech.com.chaoke.utils.AppStorageUtils;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;


/**
 * @author: lujialei
 * @date: 2019/6/12
 * @describe:
 */


public class FangYuanXiangQingActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
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
    @BindView(R.id.ll_shoufang_bottom)
    LinearLayout llShoufangBottom;
    @BindView(R.id.ll_qianyue_bottom)
    LinearLayout llQianyueBottom;
    @BindView(R.id.ll_daikan_bottom)
    LinearLayout llShikanBottom;
    @BindView(R.id.ll_genjin_bottom)
    LinearLayout llGenjinBottom;
    @BindView(R.id.ll_call)
    LinearLayout llCall;
    @BindView(R.id.tv_youxiao)
    TextView tvYouxiao;
    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.recycler_peizhi)
    RecyclerView recyclerPeizhi;
    @BindView(R.id.tv_fangyuanleixing)
    TextView tvFangyuanleixing;
    @BindView(R.id.tv_wuyeleixing)
    TextView tvWuyeleixing;
    private String id;
    private AMap aMap;
    private HousePeiZhiAdapter housePeiZhiAdapter;
    private ArrayList<HouseDetailBean.ConfigureBean> list;
    private HouseDetailBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangyuan_xiangqing);
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
        llShoufangBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ShouFangShenPiActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        llQianyueBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), FangYuanQianYueOneActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        llShikanBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bean==null){
                    T.showShort(getActivity(),"房源信息获取为空");
                    return;
                }
                Intent intent = new Intent(getBaseContext(), ShiKanActivity.class);
                intent.putExtra("data",bean );
                startActivityForResult(intent, 201);
            }
        });
        llGenjinBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GenJinActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        llCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone("18888888888");
            }
        });
//        initMap();
    }

    private void initMap(HouseDetailBean data) {
//        MyLocationStyle myLocationStyle = new MyLocationStyle();
//        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
//        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//        aMap.getUiSettings().setMyLocationButtonEnabled(true);
//        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
//        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
//            @Override
//            public void onMyLocationChange(Location location) {
//
//            }
//        });

        //移动位置
        LatLng localLatLng=new LatLng(data.getLng_pos(), data.getLat_pos());
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localLatLng,18));
        //标记点
        LatLng latLng = new LatLng(data.getLng_pos(),data.getLat_pos());
        final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title(data.getArea_name()));
    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
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
                        bean = data;
                        AppStorageUtils.getInstance().setHouseDetail(data);
                        initMap(data);
                        fillData(data);
                    }
                });
    }

    private void fillData(HouseDetailBean data) {
        if (data.getPics() != null) {
            banner.setImages(data.getPics()).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontTransform();
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    Glide.with(context).load(path).apply(requestOptions).into(imageView);
                }
            }).start();
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
            tvSize.setText(data.getArea()+"㎡");
        }
//        tvFangyuanzhuangtai.setText();
        tvChaoxiang.setText(getOrientation(data.getOrientation()));
        tvZhuangxiuqingkuang.setText(getFitUp(data.getFit_up()));
//        tvZhuangxiuqingkuang.setText();
        tvFangyuanleixing.setText("房源类型:"+getFangyuanleixing(data.getSource()));
        tvWuyeyongtu.setText("物业用途:暂无");
        if (!TextUtils.isEmpty(data.getFloor())) {
            tvLouceng.setText("房源楼层:" + data.getFloor());
        }
//        tvWuyeyongtu.setText();
        tvFankangzhuangtai.setText("看房方式:" + getSeeType(data.getSee_type()));
        bindRecycler(data.getConfigure());
    }

    private void bindRecycler(List<HouseDetailBean.ConfigureBean> configure) {
        if (configure == null) {
            return;
        }
        list.clear();
        list.addAll(configure);
        housePeiZhiAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 201) {
            loadData();
        }
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

    public String getSeeType(int i) {
//        朝向 1朝南 2为朝北 3为朝东 4为朝西
        if (i == 1) {
            return "房屋出租";
        } else if (i == 2) {
            return "预约";
        } else if (i == 3) {
            return "直接";
        } else if (i == 4) {
            return "借钥";
        } else if (i == 5) {
            return "有钥";
        } else if (i == 6) {
            return "智能锁";
        }
        return "智能锁";
    }

    public String getFangyuanleixing(int i){
//        {"中介合作", "转介绍", "老客户", "网络端口", "地推", "房东上门", "名单获取", "销冠", "其他"}
        if (i == 1) {
            return "中介合作";
        } else if (i == 2) {
            return "转介绍";
        } else if (i == 3) {
            return "老客户";
        } else if (i == 4) {
            return "网络端口";
        } else if (i == 5) {
            return "地推";
        } else if (i == 6) {
            return "房东上门";
        }else if (i == 6) {
            return "名单获取";
        }else if (i == 6) {
            return "销冠";
        }else if (i == 6) {
            return "其他";
        }
        return "中介合作";
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


    private void initView() {
        recyclerPeizhi.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        list = new ArrayList<>();
        housePeiZhiAdapter = new HousePeiZhiAdapter(list);
        recyclerPeizhi.setAdapter(housePeiZhiAdapter);
    }
}
