package com.rmuhamed.catalogogastronomia.UI;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.VolleyError;
import com.rmuhamed.catalogogastronomia.API.CatalogoAPI;
import com.rmuhamed.catalogogastronomia.API.CatalogoAPIListener;
import com.rmuhamed.catalogogastronomia.MODEL.Branch;
import com.rmuhamed.catalogogastronomia.MODEL.SearchResult;
import com.rmuhamed.catalogogastronomia.R;
import com.rmuhamed.catalogogastronomia.UI.adapter.CatalogoAdapter;
import com.rmuhamed.catalogogastronomia.UI.listener.CustomOnScrollListener;
import com.rmuhamed.catalogogastronomia.UI.listener.OnNewPageToBeDownloadedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmuhamed on 09/10/2015.
 */
public class CatalogoActivity extends BaseActivity implements CatalogoAPIListener, OnNewPageToBeDownloadedListener {

    private RecyclerView recycler;

    private List<Branch> branches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_catalogo);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        this.branches = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        this.recycler = (RecyclerView) findViewById(R.id.catalogo_list);
        this.recycler.setLayoutManager(layoutManager);
        this.recycler.setAdapter(new CatalogoAdapter(this, this.branches));

        this.recycler.addOnScrollListener(new CustomOnScrollListener(layoutManager, this));

        this.obtenerCatalogoPaginado(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_catalogo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(SearchResult response) {
        if(this.branches==null){
            this.branches = new ArrayList<>();
        }

        this.branches.addAll(response.getBranches());
        this.recycler.getAdapter().notifyDataSetChanged();
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
}
