package com.rmuhamed.catalogogastronomia.UI.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rmuhamed.catalogogastronomia.MODEL.Branch;
import com.rmuhamed.catalogogastronomia.R;
import com.rmuhamed.catalogogastronomia.UI.adapter.viewholder.ViewHolderItemDeCatalogo;

import java.util.List;

/**
 * Created by rmuhamed on 08/10/2015.
 */
public class CatalogoAdapter extends RecyclerView.Adapter<ViewHolderItemDeCatalogo> {
    private static final String LOG_TAG = CatalogoAdapter.class.getSimpleName();

    private final Context contexto;
    private final List<Branch> branchList;

    public CatalogoAdapter(Context contexto, List<Branch> branchList){
        this.contexto = contexto;
        this.branchList = branchList;
    }

    @Override
    public ViewHolderItemDeCatalogo onCreateViewHolder(ViewGroup parent, int viewType) {
        View catalogoItemView = LayoutInflater.from(this.contexto).inflate(R.layout.catalogo_item, null);
        ViewHolderItemDeCatalogo viewHolder = new ViewHolderItemDeCatalogo(catalogoItemView, this.contexto);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderItemDeCatalogo holder, int position) {
        try {
            if (this.branchList!=null && !this.branchList.isEmpty()) {
                Branch unBranch = this.branchList.get(position);
                holder.mostrarItemDelCatalogo(unBranch);
            }
        } catch (Exception e) {
            Log.e(LOG_TAG+" "+"onBindViewHolder", "Error al mostrar viewHolder" + e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if(this.branchList!=null && !this.branchList.isEmpty()){
            count = this.branchList.size();
        }

        return count;
    }
}
