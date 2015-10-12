package com.rmuhamed.catalogogastronomia.UI;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.rmuhamed.catalogogastronomia.API.CatalogoAPI;
import com.rmuhamed.catalogogastronomia.API.CatalogoAPIListener;
import com.rmuhamed.catalogogastronomia.API.SingletonRequestQueue;
import com.rmuhamed.catalogogastronomia.MODEL.Branch;
import com.rmuhamed.catalogogastronomia.MODEL.SearchResult;
import com.rmuhamed.catalogogastronomia.R;
import com.rmuhamed.catalogogastronomia.UI.adapter.CatalogoAdapter;
import com.rmuhamed.catalogogastronomia.UI.asynctask.SearchTask;
import com.rmuhamed.catalogogastronomia.UI.asynctask.SortTask;
import com.rmuhamed.catalogogastronomia.UI.listener.CustomOnScrollListener;
import com.rmuhamed.catalogogastronomia.UI.listener.OnNewPageToBeDownloadedListener;
import com.rmuhamed.catalogogastronomia.UI.listener.SearchTaskListener;
import com.rmuhamed.catalogogastronomia.UI.listener.SortTaskListener;
import com.rmuhamed.catalogogastronomia.UTILS.AsyncTaskUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmuhamed on 09/10/2015.
 */
public class CatalogoActivity extends BaseActivity implements CatalogoAPIListener, OnNewPageToBeDownloadedListener, View.OnClickListener, SearchTaskListener, SortTaskListener {
    private static final String LOG_TAG = CatalogoActivity.class.getSimpleName();
    private RecyclerView recycler;

    private List<Branch> branches;
    private View orderButton;
    private View searchButton;
    private SearchTask searchTask;
    private SortTask sortTask;
    private int actualPageToBeRendered;
    private EditText searchInputTextField;
    private boolean filterApplied;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_catalogo);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        this.branches = new ArrayList<>();
        this.filterApplied = false;

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        this.searchInputTextField = (EditText) this.findViewById(R.id.search_field);

        this.orderButton = this.findViewById(R.id.order_button);
        this.orderButton.setOnClickListener(this);

        this.searchButton = this.findViewById(R.id.search_button);
        this.searchButton.setOnClickListener(this);

        this.recycler = (RecyclerView) this.findViewById(R.id.catalogo_list);
        this.recycler.setLayoutManager(layoutManager);
        this.recycler.setAdapter(new CatalogoAdapter(this, this.branches));
        this.recycler.addOnScrollListener(new CustomOnScrollListener(layoutManager, this));

        this.obtenerCatalogoPaginado(0);
    }

    @Override
    protected void onStop() {
        AsyncTaskUtils.cancelTask(this.sortTask);
        AsyncTaskUtils.cancelTask(this.searchTask);

        SingletonRequestQueue.getInstance(this).cancelAllRequests();

        super.onStop();
    }

    @Override
    public void onSuccess(SearchResult response) {
        if(this.branches==null){
            this.branches = new ArrayList<>();
        }

        this.branches.addAll(response.getBranches());
        this.renderizarListado(false);
    }

    @Override
    public void onError(VolleyError error) {
        Log.e(LOG_TAG, error.toString());
        Snackbar.make(this.orderButton, this.getString(R.string.request_to_branches_with_error), Snackbar.LENGTH_INDEFINITE)
                .setAction(getResources().getString(R.string.no_result_for_search_another_try), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CatalogoActivity.this.obtenerCatalogoPaginado(CatalogoActivity.this.actualPageToBeRendered);
                    }
                })
                .show();
    }

    @Override
    public void onNewPageToLoad(int page) {
        this.actualPageToBeRendered = page;
        this.obtenerCatalogoPaginado(page);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.search_button:
                if(!this.filterApplied) {
                    this.doSearch();
                }else{
                    this.removeFilters();
                }
                break;

            case R.id.order_button:
                this.doSort();
                break;
        }
    }

    @Override
    public void searchDone(List<Branch> filteredBranches) {
        this.branches = filteredBranches;
        if(this.branches.isEmpty()){
            //TODO: RM - Display none results container
        }else {
            this.renderizarListado(true);
        }
    }

    @Override
    public void sortDone(List<Branch> sorteredBranches) {
        this.branches = sorteredBranches;
        this.renderizarListado(true);
    }

    private void doSort() {
        if(AsyncTaskUtils.isPossibleToLaunchTask(this.sortTask)){
            this.sortTask = new SortTask(this, this.branches, this);
            this.sortTask.execute();
        }
    }

    private void doSearch() {
        String query = this.searchInputTextField.getText().toString();
        if(!query.isEmpty()){
            if(AsyncTaskUtils.isPossibleToLaunchTask(this.searchTask)) {
                //Update flag
                this.filterApplied = true;
                //Update drawable for search button container
                this.searchButton.setBackgroundResource(R.drawable.ic_trash);

                Branch dummyBranch = new Branch();
                dummyBranch.setName(query);

                this.searchTask = new SearchTask(this, this.branches, this);
                this.searchTask.execute(dummyBranch);
            }
        }else{

        }
    }

    private void obtenerCatalogoPaginado(int pagina){
        CatalogoAPI.obtenerCatalogo(CatalogoAPI.BASE_URL, this, pagina, this);
    }

    private void renderizarListado(boolean redraw) {
        if(redraw){
            this.recycler.setAdapter(new CatalogoAdapter(this, this.branches));
        }else {
            if (this.recycler != null && this.recycler.getAdapter() != null) {
                this.recycler.getAdapter().notifyDataSetChanged();
            }
        }
    }

    private void removeFilters() {
        //Update flag
        this.filterApplied = false;
        //Update search button
        this.searchButton.setBackgroundResource(R.drawable.search);

        this.obtenerCatalogoPaginado(0);
    }
}