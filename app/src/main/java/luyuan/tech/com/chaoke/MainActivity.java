package luyuan.tech.com.chaoke;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.activity.AddHouseOtherInfoActivity;
import luyuan.tech.com.chaoke.activity.FangYuanQianYueFourMianZuCeLueActivity;
import luyuan.tech.com.chaoke.activity.HeToneXinXiFourActivity;
import luyuan.tech.com.chaoke.activity.HeToneXinXiThreeActivity;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.fragment.HomeFragment;
import luyuan.tech.com.chaoke.fragment.MessageFragment;
import luyuan.tech.com.chaoke.fragment.MineFragment;
import luyuan.tech.com.chaoke.fragment.TelFragment;
import luyuan.tech.com.chaoke.net.HttpManager;
import luyuan.tech.com.chaoke.net.NetParser;
import luyuan.tech.com.chaoke.utils.PermissionHelper;
import luyuan.tech.com.chaoke.utils.PermissionInterface;
import luyuan.tech.com.chaoke.utils.T;

public class MainActivity extends BaseActivity {


    @BindView(R.id.ll0)
    LinearLayout ll0;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.ll2)
    LinearLayout ll2;
    @BindView(R.id.ll3)
    LinearLayout ll3;
    @BindView(R.id.iv0)
    ImageView iv0;
    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    ArrayList<Fragment> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initPermissions();
        HomeFragment f0 = new HomeFragment();
        TelFragment f1 = new TelFragment();
        MessageFragment f2 = new MessageFragment();
        MineFragment f3 = new MineFragment();
        list.add(f0);
        list.add(f1);
        list.add(f2);
        list.add(f3);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_container,f0)
                .add(R.id.fl_container,f1)
                .add(R.id.fl_container,f2)
                .add(R.id.fl_container,f3)
                .commit();
        ll0.performClick();
        loadData();

    }

    private void loadData() {
//        startActivity(new Intent(getBaseContext(),HeToneXinXiFourActivity.class));
//        startActivity(new Intent(getBaseContext(),FangYuanQianYueFourMianZuCeLueActivity.class));
    }

    @OnClick({R.id.ll0, R.id.ll1, R.id.ll2, R.id.ll3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll0:
                getSupportFragmentManager().beginTransaction().
                        show(list.get(0))
                        .hide(list.get(1))
                        .hide(list.get(2))
                        .hide(list.get(3))
                        .commit();
                iv0.setSelected(true);
                tv0.setSelected(true);
                iv1.setSelected(false);
                tv1.setSelected(false);
                iv2.setSelected(false);
                tv2.setSelected(false);
                iv3.setSelected(false);
                tv3.setSelected(false);
                break;
            case R.id.ll1:
                getSupportFragmentManager().beginTransaction().
                        hide(list.get(0))
                        .show(list.get(1))
                        .hide(list.get(2))
                        .hide(list.get(3))
                        .commit();
                iv0.setSelected(false);
                tv0.setSelected(false);
                iv1.setSelected(true);
                tv1.setSelected(true);
                iv2.setSelected(false);
                tv2.setSelected(false);
                iv3.setSelected(false);
                tv3.setSelected(false);
                break;
            case R.id.ll2:
                getSupportFragmentManager().beginTransaction().
                        hide(list.get(0))
                        .hide(list.get(1))
                        .show(list.get(2))
                        .hide(list.get(3))
                        .commit();
                iv0.setSelected(false);
                tv0.setSelected(false);
                iv1.setSelected(false);
                tv1.setSelected(false);
                iv2.setSelected(true);
                tv2.setSelected(true);
                iv3.setSelected(false);
                tv3.setSelected(false);
                break;
            case R.id.ll3:
                getSupportFragmentManager().beginTransaction().
                        hide(list.get(0))
                        .hide(list.get(1))
                        .hide(list.get(2))
                        .show(list.get(3))
                        .commit();
                iv0.setSelected(false);
                tv0.setSelected(false);
                iv1.setSelected(false);
                tv1.setSelected(false);
                iv2.setSelected(false);
                tv2.setSelected(false);
                iv3.setSelected(true);
                tv3.setSelected(true);
                break;
        }
    }

    private PermissionHelper mPermissionHelper;

    private void initPermissions() {
        mPermissionHelper = new PermissionHelper(this, new PermissionInterface() {
            @Override
            public int getPermissionsRequestCode() {
                return 10000;
            }

            @Override
            public String[] getPermissions() {
                return new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                };
            }

            @Override
            public void requestPermissionsSuccess() {

            }

            @Override
            public void requestPermissionsFail() {
                T.showShort(getBaseContext(),"权限申请失败");
                finish();
            }
        });
        mPermissionHelper.requestPermissions();

    }
}
