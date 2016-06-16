package org.apache.cordova.engine;

import android.content.Context;

import com.growingio.android.sdk.collection.GrowingIO;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.NativeToJsMessageQueue;
import org.apache.cordova.PluginManager;


public class GrowingIOWebViewEngine extends SystemWebViewEngine {
    public GrowingIOWebViewEngine(Context context, CordovaPreferences preferences) {
        super(new GrowingIOSystemWebView(context), preferences);
    }

    @Override
    public void init(CordovaWebView parentWebView, CordovaInterface cordova, Client client, CordovaResourceApi resourceApi, PluginManager pluginManager, NativeToJsMessageQueue nativeToJsMessageQueue) {
        super.init(parentWebView, cordova, client, resourceApi, pluginManager, nativeToJsMessageQueue);
        SystemWebChromeClient chromeClient = new SystemWebChromeClient(this);
        String projectId = preferences.getString("GROWINGIO_PROJECT_ID",  null);
        double sampling = preferences.getDouble("GROWINGIO_SAMPLING", 1.0);
        String urlScheme = preferences.getString("GROWINGIO_SCHEME", null);
        GrowingIO.startTracing(webView.getContext(), projectId, sampling);
        GrowingIO.setScheme(urlScheme);
        GrowingIO.trackWebView(webView, chromeClient);
    }
}
