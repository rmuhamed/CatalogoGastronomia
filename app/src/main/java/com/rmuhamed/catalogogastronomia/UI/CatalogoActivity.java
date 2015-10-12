package com.rmuhamed.catalogogastronomia.UI;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.VolleyError;
import com.rmuhamed.catalogogastronomia.API.CatalogoAPI;
import com.rmuhamed.catalogogastronomia.API.CatalogoAPIListener;
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
import com.rmuhamed.catalogogastronomia.UTILS.ElementoCatalagoComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rmuhamed on 09/10/2015.
 */
public class CatalogoActivity extends BaseActivity implements CatalogoAPIListener, OnNewPageToBeDownloadedListener, View.OnClickListener, SearchTaskListener, SortTaskListener {

    private RecyclerView recycler;

    private List<Branch> branches;
    private View orderButton;
    private View searchButton;
    private SearchTask searchTask;
    private SortTask sortTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_catalogo);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        this.branches = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

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
    public void onSuccess(SearchResult response) {
        if(this.branches==null){
            this.branches = new ArrayList<>();
        }

        this.branches.addAll(response.getBranches());
        this.renderizarListado();
    }

    private void renderizarListado() {
        if(this.recycler!=null && this.recycler.getAdapter()!=null) {
            this.recycler.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onError(VolleyError error) {

    }

    private void obtenerCatalogoPaginado(int pagina){
        CatalogoAPI.obtenerCatalogo(CatalogoAPI.BASE_URL, this, pagina, this);
    }

    @Override
    public void onNewPageToLoad(int page) {
        this.obtenerCatalogoPaginado(page);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.search_button:
                this.doSearch();
                break;

            case R.id.order_button:
                this.doSort();
                break;
        }
    }

    @Override
    public void searchDone(List<Branch> branches) {

    }

    @Override
    public void sortDone(List<Branch> branc) {
        this.renderizarListado();
    }

    private void doSort() {
        if(AsyncTaskUtils.isPossibleToLaunchTask(this.sortTask)){
            this.sortTask = new SortTask(this, this.branches, this);
            this.sortTask.execute();
        }
    }

    private void doSearch() {

        //this.searchTask = new SearchTask(this, this.branches, this);
        //this.searchTask.execute();
    }
}
