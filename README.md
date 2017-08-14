JsAndroidDemo

=============

### 摘要:
  Android 的 webview 是基于 webkit 内核的，webview 中集成了js与java互调的接口函数，通过addJavascriptInterface方法，
可以将Java的类注册进webkit，给网页上的js进行调用，而且还可以通过loadUrl方法是给webkit传递一个uri，供浏览器来进行解析，
实现Java和js交互。

###Java核心代码

```java
        WebSettings webSettings = webview.getSettings();
        //设置支持javascript
        webSettings.setJavaScriptEnabled(true);
        // 编码方式
        webSettings.setDefaultTextEncodingName("utf-8");
        //jsObj只是一个桥接对象，可以任意定义
        webview.addJavascriptInterface(mJsInterface, "jsObj");
        //加载assets下面的html页面
        webview.loadUrl("file:///android_asset/index.html");
```

### Java调用js的方法，只需要知道js的方法名称、参数即可
```java
webview.loadUrl("javascript: jsMethod("参数")");
```

### JavaScript调用Java的方法更简单<br/>
```window.jsObj.androidMethod("参数");```


### 注意:
```java
Java中提供给javascript调用的方法必须加@JavascriptInterface注解否则会报错
          (Uncaught TypeError: window.jsObj.androidMethod is not a function)
```

### 关于作者
- Email： 172308834@qq.com
- Blog :  [Eicky](http://eicky.com)
- 有任何建议或者使用中遇到问题都可以给我发邮件或博客留言, 技术交流，idea分享

License
=======

    Copyright 2016 Eicky

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing blacklist and
    limitations under the License.
