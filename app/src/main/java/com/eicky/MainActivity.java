package com.eicky;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webview;
    private android.widget.Button get;
    private JsInterface mJsInterface;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.get = (Button) findViewById(R.id.get);
        this.webview = (WebView) findViewById(R.id.web_view);
        mJsInterface = new JsInterface();
        WebSettings webSettings = webview.getSettings();
        //设置支持javascript
        webSettings.setJavaScriptEnabled(true);
        // 编码方式
        webSettings.setDefaultTextEncodingName("utf-8");
        //jsObj只是一个桥接对象，可以任意定义
        webview.addJavascriptInterface(mJsInterface, "jsObj");
        //加载assets下面的html页面
        webview.loadUrl("file:///android_asset/index.html");

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webview.loadUrl("javascript: jsMethod('" + "Hello Eicky" + "')");
            }
        });
    }

    private class JsInterface {
        /**
         * js中通过window.jsObj.javaMethod("参数") 可以调用此方法并且把js中input中的值作为参数传入，
         * 必须加标注@JavascriptInterface
         * @param param
         */
        @JavascriptInterface
        public void androidMethod(String param) {
            Log.e("eicky", "我是js里面传过来的参数:" + param);
            Toast.makeText(MainActivity.this, "我是js里面传过来的参数:" + param, Toast.LENGTH_SHORT).show();
        }
    }
}
