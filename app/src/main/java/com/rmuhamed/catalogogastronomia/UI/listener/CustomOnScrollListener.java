package com.rmuhamed.catalogogastronomia.UI.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by rmuhamed on 11/10/15.
 */
public class CustomOnScrollListener extends RecyclerView.OnScrollListener{
    private final OnNewPageToBeDownloadedListener listener;
    private LinearLayoutManager layoutManager;
    private int pagina;

    public CustomOnScrollListener(LinearLayoutManager layoutManager, OnNewPageToBeDownloadedListener listener) {
        this.layoutManager = layoutManager;
        this.listener = listener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerview, int dx, int dy) {
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();


        if ((visibleItemCount + pastVisiblesItems)
                >= (totalItemCount - 2)) {
            //if (loading) {
            //    loading = false;
                this.pagina++;

                this.listener.onNewPageToLoad(this.pagina);

            //}
        }

    }
}
