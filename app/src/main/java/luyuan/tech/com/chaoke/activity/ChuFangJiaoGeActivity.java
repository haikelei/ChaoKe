package luyuan.tech.com.chaoke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.zhouyou.http.request.PostRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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


public class ChuFangJiaoGeActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.rv_shuidianranqi)
    RecyclerView rvShuidianranqi;
    @BindView(R.id.input_xiaoqudizhi)
    InputLayout inputXiaoqudizhi;
    @BindView(R.id.input_dianlihuhao)
    InputLayout inputDianlihuhao;
    @BindView(R.id.input_chufangjiage)
    InputLayout inputChufangjiage;
    @BindView(R.id.input_zongdian)
    InputLayout inputZongdian;
    @BindView(R.id.input_fengdian)
    InputLayout inputFengdian;
    @BindView(R.id.input_gudian)
    InputLayout inputGudian;
    @BindView(R.id.input_shuibiao)
    InputLayout inputShuibiao;
    @BindView(R.id.input_ranqihuhao)
    InputLayout inputRanqihuhao;
    @BindView(R.id.input_ranqidushu)
    InputLayout inputRanqidushu;

    @BindView(R.id.sl_jiafangchegndan)
    SelectLayout slJiafangchegndan;
    @BindView(R.id.num_dianshi)
    InputLayout numDianshi;
    @BindView(R.id.num_kongtiao)
    InputLayout numKongtiao;
    @BindView(R.id.num_bingxiang)
    InputLayout numBingxiang;
    @BindView(R.id.num_xiyiji)
    InputLayout numXiyiji;
    @BindView(R.id.num_hongganji)
    InputLayout numHongganji;
    @BindView(R.id.num_weibolu)
    InputLayout numWeibolu;
    @BindView(R.id.num_meiqizao)
    InputLayout numMeiqizao;
    @BindView(R.id.num_diancilu)
    InputLayout numDiancilu;
    @BindView(R.id.num_chuang)
    InputLayout numChuang;
    @BindView(R.id.num_chuangdian)
    InputLayout numChuangdian;
    @BindView(R.id.num_shafa)
    InputLayout numShafa;
    @BindView(R.id.num_yigui)
    InputLayout numYigui;
    @BindView(R.id.num_shuzhuo)
    InputLayout numShuzhuo;
    @BindView(R.id.num_yizi)
    InputLayout numYizi;
    @BindView(R.id.num_chuanglian)
    InputLayout numChuanglian;
    @BindView(R.id.num_chuangtougui)
    InputLayout numChuangtougui;
    @BindView(R.id.num_ditan)
    InputLayout numDitan;
    @BindView(R.id.num_xiegui)
    InputLayout numXiegui;
    @BindView(R.id.num_canzhuo)
    InputLayout numCanzhuo;
    @BindView(R.id.num_canyi)
    InputLayout numCanyi;
    @BindView(R.id.num_chaji)
    InputLayout numChaji;
    @BindView(R.id.num_aigui)
    InputLayout numAigui;
    @BindView(R.id.num_yinshuiji)
    InputLayout numYinshuiji;
    @BindView(R.id.num_reshuiqi)
    InputLayout numReshuiqi;
    @BindView(R.id.num_menka)
    InputLayout numMenka;
    @BindView(R.id.sl_qingdanjiaofuriqi)
    SelectLayout slQingdanjiaofuriqi;
    @BindView(R.id.num_youyanji)
    InputLayout numYouyanji;
    private String id;


    private ImageSelectAdapter adapterShuidianranqi;
    private ArrayList<ImageBean> listShuidianranqi;
    public static final int CODE_Shuidianranqi = 195;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chufang_jiaoge);
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


        setDatePickerListener(slQingdanjiaofuriqi);
        String[] arr1 = {"电费", "水费", "燃气费", "物业及能耗费"};
        setMultiSelectListener(slJiafangchegndan, arr1,"甲方承担");

        initView();
        initListener();
    }

    private void initListener() {

        adapterShuidianranqi.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageBean imageBean = listShuidianranqi.get(position);
                onMultyClick(imageBean, CODE_Shuidianranqi);
            }
        });
    }


    private void onMultyClick(ImageBean imageBean, int code) {
        if (imageBean.isAddItem()) {
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
        //水电燃气
        listShuidianranqi = new ArrayList<>();
        ImageBean bean2 = new ImageBean();
        bean2.setAddItem(true);
        listShuidianranqi.add(bean2);
        adapterShuidianranqi = new ImageSelectAdapter(listShuidianranqi);
        rvShuidianranqi.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        rvShuidianranqi.setAdapter(adapterShuidianranqi);
    }


    private void loadData() {
        if (!checkEmptyInfo()) {
            return;
        }
        StringBuilder handoverList = new StringBuilder();
        handoverList
                .append(numDianshi.getText() + ",")
                .append(numKongtiao.getText() + ",")
                .append(numBingxiang.getText() + ",")
                .append(numXiyiji.getText() + ",")
                .append(numHongganji.getText() + ",")
                .append(numWeibolu.getText() + ",")
                .append(numYouyanji.getText()+",")
                .append(numMeiqizao.getText() + ",")
                .append(numDiancilu.getText() + ",")
                .append(numChuang.getText() + ",")
                .append(numChuangdian.getText() + ",")
                .append(numShafa.getText() + ",")
                .append(numYigui.getText() + ",")
                .append(numShuzhuo.getText() + ",")
                .append(numYizi.getText() + ",")
                .append(numChuanglian.getText() + ",")
                .append(numChuangtougui.getText() + ",")
                .append(numDitan.getText() + ",")
                .append(numXiegui.getText() + ",")
                .append(numCanzhuo.getText() + ",")
                .append(numCanyi.getText() + ",")
                .append(numChaji.getText() + ",")
                .append(numAigui.getText() + ",")
                .append(numYinshuiji.getText() + ",")
                .append(numReshuiqi.getText() + ",")
                .append(numMenka.getText());
        PostRequest request = HttpManager.post(HttpManager.CHUFANG_JIAOGE)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("room_number", getValue(inputXiaoqudizhi))
                .params("power_number", getValue(inputDianlihuhao))
                .params("total_power", getValue(inputZongdian))
                .params("peak_power", getValue(inputFengdian))
                .params("valley_power", getValue(inputGudian))
                .params("water_meter", getValue(inputShuibiao))
                .params("gas_number", getValue(inputRanqihuhao))
                .params("gas_degree", getValue(inputRanqidushu))
                .params("first_cost", slJiafangchegndan.getValue())
                .params("handover_time", slQingdanjiaofuriqi.getText().toString())
                .params("contract_id", id)
                .params("side_letter",getValue(inputChufangjiage))
                .params("config", handoverList.toString())

                .params("pics", getListJson(listShuidianranqi));

        request.execute(new SimpleCallBack<String>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getBaseContext(), e.getMessage());
            }

            @Override
            public void onSuccess(String data) {
                if (NetParser.isOk(data)){
                    showSuccess();
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                }else {
                    StringDataResponse stringDataResponse = NetParser.parse(data,StringDataResponse.class);
                    T.showShort(getActivity(),stringDataResponse.getMsg());
                }
            }
        });
    }

    private String getListJson(List<ImageBean> data) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ImageBean imageBean = data.get(i);
            if (!imageBean.isAddItem()) {
                list.add(imageBean.getPath());
            }
        }
        String s = new Gson().toJson(list);
        return s;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_Shuidianranqi:
                    // 图片选择结果回调
                    onMultyResult(data, listShuidianranqi, adapterShuidianranqi);
                    break;
            }
        }
    }

    private void onMultyResult(Intent data, final ArrayList<ImageBean> list, final ImageSelectAdapter adapter) {
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
