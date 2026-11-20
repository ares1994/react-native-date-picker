package com.henninghall.date_picker;

import android.content.Context;
import android.view.View;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.henninghall.date_picker.ui.SpinnerState;

import java.util.Calendar;

public class Emitter {

    private static ReactContext getReactContext(View view) {
        if (view != null) {
            Context context = view.getContext();
            if (context instanceof ReactContext) return (ReactContext) context;
        }
        return DatePickerPackage.context;
    }

    private static RCTEventEmitter eventEmitter(ReactContext reactContext){
        return reactContext.getJSModule(RCTEventEmitter.class);
    }

    private static DeviceEventManagerModule.RCTDeviceEventEmitter deviceEventEmitter(ReactContext reactContext){
        return reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
    }

    public static void onSpinnerStateChange(SpinnerState spinnerState, String id, View view) {
        ReactContext reactContext = getReactContext(view);
        if (reactContext == null) return;

        WritableMap event = Arguments.createMap();
        event.putString("spinnerState", spinnerState.toString());
        event.putString("id", id);
        if(BuildConfig.IS_NEW_ARCHITECTURE_ENABLED){
            deviceEventEmitter(reactContext).emit("spinnerStateChange", event);
        }
        else {
            eventEmitter(reactContext).receiveEvent(view.getId(),"spinnerStateChange",event);
        }
    }

    public static void onDateChange(Calendar date, String displayValueString, String id, View view) {
        ReactContext reactContext = getReactContext(view);
        if (reactContext == null) return;

        WritableMap event = Arguments.createMap();
        String dateString = Utils.dateToIso(date);
        event.putString("date", dateString);
        event.putString("dateString", displayValueString);
        event.putString("id", id);
        if(BuildConfig.IS_NEW_ARCHITECTURE_ENABLED){
            deviceEventEmitter(reactContext).emit("dateChange", event);
        }
        else {
            eventEmitter(reactContext).receiveEvent(view.getId(),"dateChange",event);
        }
    }
    public static void onConfirm(String date, String id) {
        ReactContext reactContext = DatePickerPackage.context;
        if (reactContext == null) return;
        WritableMap event = Arguments.createMap();
        event.putString("date", date);
        event.putString("id", id);
        deviceEventEmitter(reactContext).emit("onConfirm", event);
    }

    public static void onCancel(String id) {
        ReactContext reactContext = DatePickerPackage.context;
        if (reactContext == null) return;
        WritableMap event = Arguments.createMap();
        event.putString("id", id);
        deviceEventEmitter(reactContext).emit("onCancel", event);
    }



}
