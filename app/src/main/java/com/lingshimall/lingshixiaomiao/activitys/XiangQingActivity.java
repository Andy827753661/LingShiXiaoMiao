package com.lingshimall.lingshixiaomiao.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

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
        ShareSDK.initSDK(this);
        goodID = getDataFromIntent();
        setDataForWebView(goodID);
        addListenorForView();
        setListenerForBackBtn();
        setShareSDKForShareBtn();
        setListenerForCart();
    }

    private void setListenerForCart() {
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XiangQingActivity.this, ShoppingCarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setListenerForBackBtn() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setShareSDKForShareBtn() {
        shareBtn.setOnClickListener(new TheShareListener());
    }

    public class TheShareListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            OnekeyShare oks = new OnekeyShare();
            //关闭sso授权
            oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
            //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
            // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
            oks.setTitle("快乐在于分享");
            // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
            oks.setTitleUrl("http://sharesdk.cn");
            // text是分享文本，所有平台都需要这个字段
            oks.setText("我是分享文本");
            // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
            //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
            // url仅在微信（包括好友和朋友圈）中使用
            oks.setUrl("http://sharesdk.cn");
            // comment是我对这条分享的评论，仅在人人网和QQ空间使用
            oks.setComment("我是测试评论文本");
            // site是分享此内容的网站名称，仅在QQ空间使用
            oks.setSite(getString(R.string.app_name));
            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
            oks.setSiteUrl("http://sharesdk.cn");
// 启动分享GUI
            oks.show(getApplicationContext());
        }
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
