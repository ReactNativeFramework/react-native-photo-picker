package com.atide.photopicker;

import android.app.*;
import android.app.Application;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.Arguments;

import java.util.ArrayList;
/**
 * Created by atide on 2016/11/28.
 */
public class PhotoPickerModel extends ReactContextBaseJavaModule{
    private static final int REQUEST_CODE_CAMERA = 200;
    private Callback photoCallBack;
    private final ActivityEventListener listener = new BaseActivityEventListener() {
            @Override
                   public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {

                       super.onActivityResult(activity, requestCode, resultCode, data);

                       if (resultCode != Activity.RESULT_OK) {
                           return;
                       }
                       if (requestCode == REQUEST_CODE_CAMERA) {
                           ArrayList<String> result = data.getStringArrayListExtra(PhotoPickerActivity.KEY_RESULT);

                           if (photoCallBack!=null){
                                WritableArray array = Arguments.createArray();
                                                   for (String s:result) {
                                                       array.pushString(s);
                                                   }
                                                   photoCallBack.invoke(array);
                           }
                       }
                   }
        };
    public PhotoPickerModel(ReactApplicationContext reactContext) {
        super(reactContext);
         reactContext.addActivityEventListener(listener);
    }

    @Override
    public String getName() {
        return "PhotoPickerModel";
    }

    @ReactMethod
       public void photoPicker(ReadableMap options,Callback callback){
           this.photoCallBack = callback;
           Activity activity = getCurrentActivity();
           int mode = PhotoPickerActivity.MODE_MULTI;
           if (options!=null&&options.hasKey("mode") && !options.isNull("mode")) {
               mode = options.getInt("mode") == 0 ? PhotoPickerActivity.MODE_SINGLE : PhotoPickerActivity.MODE_MULTI;
           }
           int number = 9;
           if (options!=null&&options.hasKey("maxNum") && !options.isNull("maxNum")) {
               number = options.getInt("maxNum");
           }
           Intent intent = new Intent(activity, PhotoPickerActivity.class);
           intent.putExtra(PhotoPickerActivity.EXTRA_SHOW_CAMERA,true);
           intent.putExtra(PhotoPickerActivity.EXTRA_SELECT_MODE, mode);
           if (mode==PhotoPickerActivity.MODE_MULTI){
               intent.putExtra(PhotoPickerActivity.EXTRA_MAX_MUN,number);
           }
           activity.startActivityForResult(intent, REQUEST_CODE_CAMERA);
       }

}
