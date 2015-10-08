package com.rmuhamed.catalogogastronomia.API;

import com.android.volley.VolleyError;
import com.rmuhamed.catalogogastronomia.MODEL.SearchResult;

/**
 * Created by rmuhamed on 08/10/2015.
 */
public interface CatalogoAPIListener {
    public void onSuccess(SearchResult response);
    public void onError(VolleyError error);
}
