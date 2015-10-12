package com.rmuhamed.catalogogastronomia.UI;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
import java.util.Collections;
import java.util.List;

/**
 * Created by rmuhamed on 09/10/2015.
 */
public class CatalogoActivity extends BaseActivity implements CatalogoAPIListener, OnNewPageToBeDownloadedListener, View.OnClickListener, SearchTaskListener, SortTaskListener {
    private static final String LOG_TAG = CatalogoActivity.class.getSimpleName();
    private RecyclerView recycler;

    private List<Branch> branches;
    private FloatingActionButton orderButton;
    private FloatingActionButton searchButton;
    private SearchTask searchTask;
    private SortTask sortTask;
    private int actualPageToBeRendered;
    private EditText searchInputTextField;
    private boolean filterApplied;
    private boolean firstTime;

    private LinearLayoutManager recyclerLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_catalogo);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        this.branches = Collections.synchronizedList(new ArrayList<Branch>());
        this.filterApplied = false;
        this.firstTime = true;
        this.actualPageToBeRendered = 1;

        this.recyclerLayoutManager = new LinearLayoutManager(this);
        this.recyclerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        this.searchInputTextField = (EditText) this.findViewById(R.id.search_field);

        this.orderButton = (FloatingActionButton) this.findViewById(R.id.order_button);
        this.orderButton.setOnClickListener(this);

        this.searchButton = (FloatingActionButton) this.findViewById(R.id.search_button);
        this.searchButton.setOnClickListener(this);

        this.recycler = (RecyclerView) this.findViewById(R.id.catalogo_list);
        this.recycler.setLayoutManager(this.recyclerLayoutManager);
        this.recycler.setAdapter(new CatalogoAdapter(this, this.branches));

        this.obtenerCatalogoPaginado(this.actualPageToBeRendered);
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

        if(response!=null && response.getBranches()!=null) {
            //Attach OnScrollListener for recyclerView
            if (this.firstTime) {
                this.firstTime = false;
                this.recycler.addOnScrollListener(new CustomOnScrollListener(this.recyclerLayoutManager, this));
            }


            this.actualizarElementos(response.getBranches());
            this.renderizarListado(false);
        }
    }


    private void actualizarElementos(List<Branch> remoteBranches) {
        if (this.branches == null) {
            this.branches = new ArrayList<>();
        }

        this.branches.addAll(remoteBranches);
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
            Toast.makeText(this, this.getString(R.string.query_with_no_results), Toast.LENGTH_SHORT).show();
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
                this.searchButton.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_trash));

                Branch dummyBranch = new Branch();
                dummyBranch.setName(query);

                this.searchTask = new SearchTask(this, this.branches, this);
                this.searchTask.execute(dummyBranch);
            }
        }else{
            Toast.makeText(this, R.string.query_field_empty, Toast.LENGTH_SHORT).show();
        }
    }

    private void obtenerCatalogoPaginado(int pagina){
        CatalogoAPI.obtenerCatalogo(CatalogoAPI.BASE_URL, this, pagina, this);
    }

    private void renderizarListado(boolean redraw) {
        if(redraw){
            this.recycler.setAdapter(new CatalogoAdapter(this, this.branches));
            this.recycler.getAdapter().notifyDataSetChanged();
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
        this.searchButton.setImageDrawable(this.getResources().getDrawable(R.drawable.search));
        //Reset data and make a request to API Rest
        this.branches = Collections.synchronizedList(new ArrayList<Branch>());
        this.recycler.setAdapter(new CatalogoAdapter(this, this.branches));
        this.obtenerCatalogoPaginado(0);
    }
}