package com.rmuhamed.catalogogastronomia.UI.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.rmuhamed.catalogogastronomia.MODEL.Branch;
import com.rmuhamed.catalogogastronomia.R;
import com.rmuhamed.catalogogastronomia.UI.listener.SearchTaskListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmuhamed on 11/10/15.
 */
public class SearchTask extends AsyncTask<Branch, Void, List<Branch>> {
    private final SearchTaskListener listener;
    private final Context contexto;
    private final List<Branch> originalList;
    private ProgressDialog progress;

    public SearchTask(Context contexto, List<Branch> originalList, SearchTaskListener listener){
        this.contexto = contexto;
        this.listener = listener;
        this.originalList = originalList;
    }

    @Override
    protected void onPreExecute() {
        this.progress = ProgressDialog.show(this.contexto, "", this.contexto.getString(R.string.searching), true, false);
        super.onPreExecute();
    }

    @Override
    protected List<Branch> doInBackground(Branch... params) {
        List<Branch> result = new ArrayList<>();
        Branch dummyBranch = params[0];

        for(Branch aBranch : this.originalList){
            if(aBranch.equals(dummyBranch)){
                result.add(aBranch);
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(List<Branch> branches) {
        if(this.progress!=null && this.progress.isShowing()){
            this.progress.dismiss();
        }

        if(this.listener!=null){
            this.listener.searchDone(branches);
        }
    }
}
