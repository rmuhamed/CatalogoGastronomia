package com.rmuhamed.catalogogastronomia.UI.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.rmuhamed.catalogogastronomia.MODEL.Branch;
import com.rmuhamed.catalogogastronomia.UI.listener.SortTaskListener;
import com.rmuhamed.catalogogastronomia.UTILS.ElementoCatalagoComparator;

import java.util.Collections;
import java.util.List;

/**
 * Created by rmuhamed on domingo.
 */
public class SortTask extends AsyncTask<String, Void, List<Branch>> {
    private final List<Branch> brancesUnsorted;
    private final SortTaskListener listener;
    private final Context contexto;
    private ProgressDialog progress;

    public SortTask(Context contexto, List<Branch> brancesUnsorted, SortTaskListener listener){
        this.brancesUnsorted = brancesUnsorted;
        this.listener = listener;
        this.contexto = contexto;
    }

    @Override
    protected void onPreExecute() {
        this.progress = ProgressDialog.show(this.contexto, "", "Ordenando...", true, false);
    }

    @Override
    protected List<Branch> doInBackground(String... params) {
        Collections.sort(this.brancesUnsorted, new ElementoCatalagoComparator());

        return this.brancesUnsorted;
    }

    @Override
    protected void onPostExecute(List<Branch> sortedBranches) {
        if(this.progress!=null && this.progress.isShowing()){
            this.progress.dismiss();
        }
        if(this.listener!=null){
            this.listener.sortDone(sortedBranches);
        }
    }
}
