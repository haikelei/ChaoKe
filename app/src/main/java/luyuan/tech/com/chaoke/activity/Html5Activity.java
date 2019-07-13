package luyuan.tech.com.chaoke.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import luyuan.tech.com.chaoke.R;
import luyuan.tech.com.chaoke.base.BaseActivity;
import luyuan.tech.com.chaoke.utils.StatusBarUtil;
import luyuan.tech.com.chaoke.widget.Html5Webview;


public class Html5Activity extends BaseActivity {

    @BindView(R.id.web_view)
    Html5Webview webView;

    public static final String URL = "url";
    private String mUrl;

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, Html5Activity.class);
        intent.putExtra(URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBarDarkTheme(this,true);
        mUrl = getIntent().getStringExtra(URL);
        Log.d("Url:", mUrl);
//        webView.loadUrl("http://www.17-53.com");
        webView.loadUrl(mUrl);
    }



}