package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.MainActivity;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.adapter.ImageSelectAdapter;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.bean.ImageBean;
import luyuan.tech.com.chaoke.bean.StringDataResponse;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.net.NetParser;
import luyuan.tech.com.chaoke.utils.ImageUploadUtils;
import luyuan.tech.com.chaoke.utils.T;
import luyuan.tech.com.chaoke.utils.UserInfoUtils;
import luyuan.tech.com.chaoke.widget.InputLayout;
import luyuan.tech.com.chaoke.widget.SelectLayout;

/**
 * @author: lujialei
 * @date: 2019/6/10
 * @describe:
 */


public class ShouFangShenPiActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.input_address)
    InputLayout inputAddress;
    @BindView(R.id.input_huxing)
    InputLayout inputHuxing;
    @BindView(R.id.input_chaoxiang)
    InputLayout inputChaoxiang;
    @BindView(R.id.input_mianji)
    InputLayout inputMianji;
    @BindView(R.id.input_shoufangjiage)
    InputLayout inputShoufangjiage;
    @BindView(R.id.sl_fukuanfangshi)
    SelectLayout slFukuanfangshi;
    @BindView(R.id.rv_woshi)
    RecyclerView rvWoshi;
    @BindView(R.id.rv_keting)
    RecyclerView rvKeting;
    @BindView(R.id.rv_chufang)
    RecyclerView rvChufang;
    @BindView(R.id.rv_weishengjian)
    RecyclerView rvWeishengjian;
    @BindView(R.id.rv_yangtai)
    RecyclerView rvYangtai;
    private String id;
    private ImageSelectAdapter adapterWoshi;
    private ImageSelectAdapter adapterKeting;
    private ImageSelectAdapter adapterChufang;
    private ImageSelectAdapter adapterWeishengjian;
    private ImageSelectAdapter adapterYangtai;
    private ArrayList<ImageBean> listWoshi;
    private ArrayList<ImageBean> listKeting;
    private ArrayList<ImageBean> listChufang;
    private ArrayList<ImageBean> listWeishengjian;
    private ArrayList<ImageBean> listYangtai;
    public static final int CODE_WOSHI = 150;
    public static final int CODE_CHUFANG = 151;
    public static final int CODE_KETING = 152;
    public static final int CODE_WEISHENGJIAN = 153;
    public static final int CODE_YANGTAI = 154;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoufang_shenpi);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
//        付款方式 1 为 2为 3为 4为
        String[] arr = {"月付","季付","半年付","年付"};
        setSelectLListener(slFukuanfangshi,arr,"付款方式");
        initView();
        initListener();
    }

    private void initListener() {
        adapterWoshi.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageBean imageBean = listWoshi.get(position);
                onClick(imageBean,CODE_WOSHI);
            }
        });
        adapterYangtai.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageBean imageBean = listYangtai.get(position);
                onClick(imageBean,CODE_YANGTAI);
            }
        });
        adapterWeishengjian.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageBean imageBean = listWeishengjian.get(position);
                onClick(imageBean,CODE_WEISHENGJIAN);
            }
        });
        adapterChufang.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageBean imageBean = listChufang.get(position);
                onClick(imageBean,CODE_CHUFANG);
            }
        });
        adapterKeting.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageBean imageBean = listKeting.get(position);
                onClick(imageBean,CODE_KETING);
            }
        });
    }

    private void onClick(ImageBean imageBean, int code) {
        if (imageBean.isAddItem()){
            PictureSelector.create(getActivity())
                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .maxSelectNum(5)// 最大图片选择数量
                    .minSelectNum(0)// 最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                    .previewImage(false)// 是否可预览图片
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    .enableCrop(false)// 是否裁剪
                    .compress(true)// 是否压缩
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
//                        .selectionMedia(selectList)// 是否传入已选图片
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    .forResult(code);//结果回调onActivityResult code
        }
    }

    private void initView() {
        //卧室
        listWoshi = new ArrayList<>();
        ImageBean  bean = new ImageBean();
        bean.setAddItem(true);
        listWoshi.add(bean);
        adapterWoshi = new ImageSelectAdapter(listWoshi);
        rvWoshi.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.HORIZONTAL,false));
        rvWoshi.setAdapter(adapterWoshi);
        //客厅
        listKeting = new ArrayList<>();
        ImageBean  bean1 = new ImageBean();
        bean1.setAddItem(true);
        listKeting.add(bean1);
        adapterKeting = new ImageSelectAdapter(listKeting);
        rvKeting.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.HORIZONTAL,false));
        rvKeting.setAdapter(adapterKeting);
        //厨房
        listChufang = new ArrayList<>();
        ImageBean  bean2 = new ImageBean();
        bean2.setAddItem(true);
        listChufang.add(bean2);
        adapterChufang = new ImageSelectAdapter(listChufang);
        rvChufang.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.HORIZONTAL,false));
        rvChufang.setAdapter(adapterChufang);
        //卫生间
        listWeishengjian = new ArrayList<>();
        ImageBean  bean3 = new ImageBean();
        bean3.setAddItem(true);
        listWeishengjian.add(bean3);
        adapterWeishengjian = new ImageSelectAdapter(listWeishengjian);
        rvWeishengjian.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.HORIZONTAL,false));
        rvWeishengjian.setAdapter(adapterWeishengjian);
        //阳台
        listYangtai = new ArrayList<>();
        ImageBean  bean4 = new ImageBean();
        bean4.setAddItem(true);
        listYangtai.add(bean4);
        adapterYangtai = new ImageSelectAdapter(listYangtai);
        rvYangtai.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.HORIZONTAL,false));
        rvYangtai.setAdapter(adapterYangtai);
    }

    private void loadData() {
        if (!checkEmptyInfo()){
            return;
        }
        HttpManager.post(HttpManager.SHOUFANG_SHENPI)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("rent_id",id)
                .params("room_address",getValue(inputAddress))
                .params("apartment_type",getValue(inputHuxing))
                .params("orientation",getValue(inputChaoxiang))
                .params("area",getValue(inputMianji))
                .params("collect_price",getValue(inputShoufangjiage))
                .params("pay_type",getValue(slFukuanfangshi))
                .params("bedroom", getListJson(listWoshi))
                .params("living_room",getListJson(listKeting))
                .params("kitchen",getListJson(listChufang))
                .params("toilet",getListJson(listWeishengjian))
                .params("balcony",getListJson(listYangtai))
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(getBaseContext(), e.getMessage());
                    }

                    @Override
                    public void onSuccess(String data) {
                        StringDataResponse stringDataResponse = NetParser.parse(data,StringDataResponse.class);
                        if (!TextUtils.isEmpty(stringDataResponse.getMsg())){
                            T.showShort(getActivity(),stringDataResponse.getMsg());
                        }else {
                            showSuccess();
                            startActivity(new Intent(getBaseContext(), MainActivity.class));
                        }

                    }
                });
    }


    private String getListJson(List<ImageBean> data){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ImageBean imageBean = data.get(i);
            if (!imageBean.isAddItem()){
                list.add(imageBean.getPath());
            }
        }
        String s = new Gson().toJson(list);
        return  s;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_WOSHI:
                    // 图片选择结果回调
                    onResult(data,listWoshi,adapterWoshi);
                    break;
                case CODE_KETING:
                    // 图片选择结果回调
                    onResult(data,listKeting,adapterKeting);
                    break;
                case CODE_CHUFANG:
                    // 图片选择结果回调
                    onResult(data,listChufang,adapterChufang);
                    break;
                case CODE_WEISHENGJIAN:
                    // 图片选择结果回调
                    onResult(data,listWeishengjian,adapterWeishengjian);
                    break;
                case CODE_YANGTAI:
                    // 图片选择结果回调
                    onResult(data,listYangtai,adapterYangtai);
                    break;
            }
        }
    }

    private void onResult(Intent data, final ArrayList<ImageBean> list, final ImageSelectAdapter adapter) {
        List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
        for (LocalMedia media : selectList) {
            final String path = media.getCompressPath();
            File file = new File(path);
            ImageUploadUtils.getInstance().uploadImage(file, new UpCompletionHandler() {
                @Override
                public void complete(String key, ResponseInfo info, JSONObject response) {
                    //res包含hash、key等信息，具体字段取决于上传策略的设置
                    if (info.isOK()) {
                        try {
                            String name = response.getString("key");
                            ImageBean bean = new ImageBean();
                            bean.setPath(name);
                            list.add(0, bean);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        }
    }


}
