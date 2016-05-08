package com.rmuhamed.catalogogastronomia.API;

import android.content.Context;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rmuhamed.catalogogastronomia.API.utils.StreamToStringConverter;
import com.rmuhamed.catalogogastronomia.MODEL.SearchResult;

import java.io.UnsupportedEncodingException;

/**
 * Created by rmuhamed on 13/05/2015.
 */
public class CatalogoRequest extends JsonRequest<SearchResult> {

    public static final String LOG_TAG = CatalogoRequest.class.getSimpleName();
    private Response.Listener<SearchResult> listener;

    public CatalogoRequest(String url, String requestBody, Response.Listener<SearchResult> listener, Response.ErrorListener errorListener) {
        super(url, requestBody, listener, errorListener);

        this.listener = listener;

        /** Timeout **/
        this.setRetryPolicy(new DefaultRetryPolicy(
                6000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    protected Response<SearchResult> parseNetworkResponse(NetworkResponse networkResponse) {
        Log.i(LOG_TAG, "Request de catalogo completo, parseando...");

        Response<SearchResult> result = null;

        try {
            String UTF8String = StreamToStringConverter.convert(networkResponse.data);
            try {

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                SearchResult searchResult = gson.fromJson(UTF8String, SearchResult.class);

                Cache.Entry cacheHeaders = HttpHeaderParser.parseCacheHeaders(networkResponse);

                result = Response.success(searchResult, cacheHeaders);
            } catch (Exception e) {
                result = Response.error(new VolleyError());
            }

        } catch (UnsupportedEncodingException e) {
            result = Response.error(new VolleyError());
        } catch (OutOfMemoryError e){
            result = Response.error(new VolleyError());
        } catch (Exception e){
            result = Response.error(new VolleyError());
        }

        return result;
    }

    @Override
    protected void deliverResponse(SearchResult response) {
        if(this.listener!=null) {
            this.listener.onResponse(response);
        }
    }
}

