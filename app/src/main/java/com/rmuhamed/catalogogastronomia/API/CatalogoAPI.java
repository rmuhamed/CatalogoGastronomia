package com.rmuhamed.catalogogastronomia.API;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.rmuhamed.catalogogastronomia.model.SearchResult;

/**
 * Created by rmuhamed on 08/10/2015.
 */
public class CatalogoAPI {
    public static final String BASE_URL = "http://api.club.lanacion.com.ar/search?facet=categoria|gastronomia&Page=1&PageSize=15";


    public static void obtenerCatalogo(String URL, Context contexto, final CatalogoAPIListener listener){
        JsonRequest jsonRequest = new CatalogoRequest(contexto, URL, null,
                new Response.Listener<SearchResult>() {

                    @Override
                    public void onResponse(SearchResult response) {
                        listener.onSuccess(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        listener.onError(volleyError);

                    }
                });

        jsonRequest.setShouldCache(true);

        SingletonRequestQueue.getInstance(contexto).addToRequestQueue(jsonRequest);
    }
}
