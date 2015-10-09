package com.rmuhamed.catalogogastronomia.UI;

import android.support.v7.app.AppCompatActivity;

import com.rmuhamed.catalogogastronomia.CatalogoApplication;

/**
 * Created by rmuhamed on 09/10/2015.
 */
public class BaseActivity extends AppCompatActivity{

    public CatalogoApplication getMyApplication() {
        return (CatalogoApplication) getApplicationContext();
    }
}
