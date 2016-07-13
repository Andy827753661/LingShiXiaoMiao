package com.lingshimall.lingshixiaomiao.activitys;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingshimall.lingshixiaomiao.BaseActivity;
import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.beans.URLs;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 张 波 on 2016/7/12.
 */
public class XiangQingActivity extends BaseActivity {

    @ViewInject(R.id.xiangqing_back_id)
    private TextView backBtn;
    @ViewInject(R.id.xiangqing_share_id)
    private ImageView shareBtn;
    @ViewInject(R.id.xiangqing_jiaru_id)
    private Button add;
    @ViewInject(R.id.xiangqing_gouwuche_id)
    private Button cart;
    @ViewInject(R.id.xiangqing_webview_id)
    private WebView webView;

    private int goodID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_xiangqing_layout);
        x.view().inject(this);

        goodID = getDataFromIntent();
        setDataForWebView(goodID);
        addListenorForView();
    }

    private void addListenorForView() {
//        backBtn.setOnClickListener();

    }


    private void setDataForWebView(int ID) {
        String urlPath = URLs.GOODSXIANGQING1 + ID + URLs.GOODSXIANGQING2;
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(urlPath);


    }


    public int getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("GoodId", 9283);
        LogUtil.e(id + "========");
        return id;
    }
}
