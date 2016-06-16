package org.apache.cordova.engine;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebViewEngine;

public class GrowingIOSystemWebView extends SystemWebView implements CordovaWebViewEngine.EngineView {
    private SystemWebViewEngine parentEngine;
    private CordovaInterface cordova;

    public GrowingIOSystemWebView(Context context) {
        this(context, null);
    }

    public GrowingIOSystemWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    void init(SystemWebViewEngine parentEngine, CordovaInterface cordova) {
        this.cordova = cordova;
        this.parentEngine = parentEngine;
        this.chromeClient = new SystemWebChromeClient(parentEngine);
        super.init(parentEngine, cordova);
    }

    @Override
    public void setWebChromeClient(WebChromeClient client) {
        if (!(client instanceof SystemWebChromeClient)) {
            client = new GrowingIOWebChromeClient(parentEngine, client);
        }
        super.setWebChromeClient(client);
    }

    private static class GrowingIOWebChromeClient extends SystemWebChromeClient {

        private WebChromeClient mOriginalClient;

        public GrowingIOWebChromeClient(SystemWebViewEngine engine, WebChromeClient client) {
            super(engine);
            mOriginalClient = client;
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mOriginalClient.onProgressChanged(view, newProgress);
        }
    }

}
