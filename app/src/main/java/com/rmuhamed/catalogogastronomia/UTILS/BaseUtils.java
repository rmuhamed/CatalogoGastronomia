package com.rmuhamed.catalogogastronomia.UTILS;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;

/**
 * Created by rmuhamed on 08/10/2015.
 */
public class BaseUtils {
    private static final String LOG_TAG = BaseUtils.class.getSimpleName();

    public static int getMemoriaAsignadaApp(Context contexto){
        int memClass = ((ActivityManager) contexto.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        Log.i(LOG_TAG, "Memoria de la App" + ' ' + Integer.toString(memClass) + "MB");
        return memClass;
    }

    public static String getApplicationVersionName(PackageInfo aPackageInfo) {
        String versionName = aPackageInfo!=null ? aPackageInfo.versionName : "";

        return versionName;
    }

    public static int getApplicationVersionCode(PackageInfo aPackageInfo) {
        int versionCode = aPackageInfo!=null ? aPackageInfo.versionCode : -1;

        return versionCode;
    }
}
