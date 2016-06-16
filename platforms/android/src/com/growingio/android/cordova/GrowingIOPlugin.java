package com.growingio.android.cordova;

import android.content.Context;

import com.growingio.android.sdk.collection.GrowingIO;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GrowingIOPlugin extends CordovaPlugin {

    private Context mApplicationContext;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        mApplicationContext = webView.getContext().getApplicationContext();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("setCS")) {
            GrowingIO growingIO = GrowingIO.getInstance();
            if (growingIO == null) return false;
            for (int i = 0; i < args.length(); i++) {
                JSONArray opArgs = args.getJSONArray(i);
                if (opArgs.length() == 3) {
                    String op = opArgs.getString(0);
                    String key = opArgs.getString(1);
                    String value = opArgs.getString(2);
                    try {
                        Method method = GrowingIO.class.getDeclaredMethod(op, String.class, String.class);
                        method.invoke(growingIO, key, value);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            return true;
        }
        return false;
    }
}