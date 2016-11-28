package com.atide.photopicker;


import android.content.Context;

public class Application {

    private static Context mContext;


    public static void setContext(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }



}
