package com.rmuhamed.catalogogastronomia;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.rmuhamed.catalogogastronomia.UTILS.BaseUtils;

/**
 * Created by rmuhamed on 08/10/2015.
 */
public class CatalogoApplication extends Application {
    private static final int DISK_CACHE_SIZE = 8*1024*1024; //8MB en bytes
    private static final String LOG_TAG = CatalogoApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        this.configurarUniversalImageLoader();
    }

    private void configurarUniversalImageLoader() {
        int tamCacheDeMemoriaUIL = (BaseUtils.getMemoriaAsignadaApp(this.getApplicationContext())/4) * 1024 * 1024;

        DisplayImageOptions options = this.construirImageOption();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .memoryCacheSize(tamCacheDeMemoriaUIL)
                .diskCacheSize(DISK_CACHE_SIZE)
                .writeDebugLogs()
                .imageDownloader(new BaseImageDownloader(this, 7000, 30000))
                .build();

        ImageLoader.getInstance().init(config);
    }

    private DisplayImageOptions construirImageOption() {
        DisplayImageOptions options = null;

        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true);

        options = builder.build();

        return options;
    }

    public boolean hayConexionInternet() {
        return ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    public void showImage(String url, ImageView imageView) {
        try {
            ImageLoader.getInstance().displayImage(url, imageView);
        } catch (OutOfMemoryError e) {
            Log.e(LOG_TAG, "El celu se quedó sin memoria");
        }
    }

}
