package com.rmuhamed.catalogogastronomia.API;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.rmuhamed.catalogogastronomia.MODEL.SearchResult;

/**
 * Created by rmuhamed on 08/10/2015.
 */
public class CatalogoAPI {
    public static final String BASE_URL = "http://api.club.lanacion.com.ar/search?facet=categoria|gastronomia&Page=%d&PageSize=8";


    public static void obtenerCatalogo(String URL, Context contexto, int paginaACargar, final CatalogoAPIListener listener){
        String url = String.format(URL, paginaACargar);
        JsonRequest jsonRequest = new CatalogoRequest(url, null,
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
