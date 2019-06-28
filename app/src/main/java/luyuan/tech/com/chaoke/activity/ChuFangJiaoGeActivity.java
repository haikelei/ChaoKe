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
import com.zhouyou.http.body.ProgressResponseCallBack;
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
import luyuan.tech.com.chaoke.bean.NameBean;
import luyuan.tech.com.chaoke.bean.TotalIdBean;
import luyuan.tech.com.chaoke.net.HttpManager;
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
    @BindView(R.id.input_xiaoqudizhi)
    InputLayout inputXiaoqudizhi;
    @BindView(R.id.input_hetongbianhao)
    InputLayout inputHetongbianhao;
    @BindView(R.id.input_chufangjiage)
    InputLayout inputChufangjiage;
    @BindView(R.id.input_zongjine)
    InputLayout inputZongjine;
    @BindView(R.id.input_zukexingming)
    InputLayout inputZukexingming;
    @BindView(R.id.input_zukeshoujihao)
    InputLayout inputZukeshoujihao;
    @BindView(R.id.sl_hetongqisuanri)
    SelectLayout slHetongqisuanri;
    @BindView(R.id.sl_hetongjieshuri)
    SelectLayout slHetongjieshuri;
    @BindView(R.id.rv_hetongziliao)
    RecyclerView rvHetongziliao;
    @BindView(R.id.rv_shenfenzheng)
    RecyclerView rvShenfenzheng;
    @BindView(R.id.rv_wuyejiaoge)
    RecyclerView rvWuyejiaoge;


    private ImageSelectAdapter adapterHetognziliao;
    private ImageSelectAdapter adapterShenfenzheng;
    private ImageSelectAdapter adapterWuyejiaoge;
    private ArrayList<ImageBean> listHetognziliao;
    private ArrayList<ImageBean> listShenfenzheng;
    private ArrayList<ImageBean> listWuyejiaoge;
    public static final int CODE_Hetognziliao = 190;
    public static final int CODE_Shenfenzheng = 191;
    public static final int CODE_Wuyejiaoge = 192;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chufang_jiaoge);
        ButterKnife.bind(this);
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


        initView();
        initListener();
    }

    private void initListener() {
        adapterHetognziliao.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageBean imageBean = listHetognziliao.get(position);
                onMultyClick(imageBean, CODE_Hetognziliao);
            }
        });

        adapterShenfenzheng.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageBean imageBean = listShenfenzheng.get(position);
                onMultyClick(imageBean, CODE_Shenfenzheng);
            }
        });

        adapterWuyejiaoge.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageBean imageBean = listWuyejiaoge.get(position);
                onMultyClick(imageBean, CODE_Wuyejiaoge);
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
        //合同资料
        listHetognziliao = new ArrayList<>();
        ImageBean bean = new ImageBean();
        bean.setAddItem(true);
        listHetognziliao.add(bean);
        adapterHetognziliao = new ImageSelectAdapter(listHetognziliao);
        rvHetongziliao.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        rvHetongziliao.setAdapter(adapterHetognziliao);
        //身份证明
        listShenfenzheng = new ArrayList<>();
        ImageBean bean1 = new ImageBean();
        bean1.setAddItem(true);
        listShenfenzheng.add(bean1);
        adapterShenfenzheng = new ImageSelectAdapter(listShenfenzheng);
        rvShenfenzheng.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        rvShenfenzheng.setAdapter(adapterShenfenzheng);
        //物业交割
        listWuyejiaoge = new ArrayList<>();
        ImageBean bean2 = new ImageBean();
        bean2.setAddItem(true);
        listWuyejiaoge.add(bean2);
        adapterWuyejiaoge = new ImageSelectAdapter(listWuyejiaoge);
        rvWuyejiaoge.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        rvWuyejiaoge.setAdapter(adapterWuyejiaoge);
    }


    private void loadData() {
        if (!checkEmptyInfo()){
            return;
        }
        PostRequest request = HttpManager.post(HttpManager.CHUFANG_JIAOGE)
                .params("token", UserInfoUtils.getInstance().getToken())
                .params("room_address",getValue(inputXiaoqudizhi))
                .params("contract_num",getValue(inputHetongbianhao))
                .params("out_price",getValue(inputChufangjiage))
                .params("total_price",getValue(inputZongjine))
                .params("tenant_name",getValue(inputZukexingming))
                .params("contract_begin",getValue(slHetongqisuanri))
                .params("contract_end",getValue(slHetongjieshuri))
                .params("contract_pics",getListJson(listHetognziliao))
                .params("tenant_pics",getListJson(listShenfenzheng))
                .params("delivery_pics",getListJson(listWuyejiaoge));

        request.execute(new SimpleCallBack<String>() {

            @Override
            public void onError(ApiException e) {
                T.showShort(getBaseContext(), e.getMessage());
            }

            @Override
            public void onSuccess(String data) {
                showSuccess();
                startActivity(new Intent(getBaseContext(), MainActivity.class));
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
                case CODE_Hetognziliao:
                    // 图片选择结果回调
                    onMultyResult(data, listHetognziliao, adapterHetognziliao);
                    break;
                case CODE_Shenfenzheng:
                    // 图片选择结果回调
                    onMultyResult(data, listShenfenzheng, adapterShenfenzheng);
                    break;
                case CODE_Wuyejiaoge:
                    // 图片选择结果回调
                    onMultyResult(data, listWuyejiaoge, adapterWuyejiaoge);
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
