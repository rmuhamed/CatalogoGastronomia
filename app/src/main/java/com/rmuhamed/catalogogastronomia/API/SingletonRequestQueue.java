package com.rmuhamed.catalogogastronomia.API;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingletonRequestQueue {
    private static final String LOG_TAG = SingletonRequestQueue.class.getSimpleName();
    private static SingletonRequestQueue instance;
    private RequestQueue requestQueue;
    private static Context context;

    private SingletonRequestQueue(Context context) {
        SingletonRequestQueue.context = context;
        this.requestQueue = this.getRequestQueue();
    }

    public static synchronized SingletonRequestQueue getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonRequestQueue(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        this.getRequestQueue().add(req);
    }

    public void cancelAllRequests() {
        this.getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
    }

    public void cancelNotaRequests() {
        this.getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                String tag = (String) request.getTag();
                Log.i(LOG_TAG, String.format("%s %s", "Se cancela la nota con tag: ", tag != null ? tag.toString() : ""));
                return tag!=null && tag.equalsIgnoreCase("nota");
            }
        });
    }

}
