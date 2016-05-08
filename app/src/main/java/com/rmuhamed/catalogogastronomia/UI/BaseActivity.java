package com.rmuhamed.catalogogastronomia.UI;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.rmuhamed.catalogogastronomia.CatalogoApplication;
import com.rmuhamed.catalogogastronomia.DOMAIN.DatabaseConfigurator;
import com.rmuhamed.catalogogastronomia.UTILS.BaseUtils;

/**
 * Created by rmuhamed on 09/10/2015.
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        PackageInfo packageInfo = this.getPackageInformation();

        //Database configuration
        DatabaseConfigurator.getInstance().setContext(this);
        DatabaseConfigurator.getInstance().configure(BaseUtils.getApplicationVersionCode(packageInfo), null);

        this.setupLayout();
    }

    protected abstract void setupLayout();

    @Nullable
    private PackageInfo getPackageInformation() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = this.getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(this.getClass().getSimpleName(), "Meta Data of App", e);
        }
        return packageInfo;
    }

    public CatalogoApplication getApplicationInstance() {
        return (CatalogoApplication) this.getApplicationContext();
    }
}
