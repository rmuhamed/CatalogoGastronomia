package com.rmuhamed.catalogogastronomia.UTILS;

import android.os.AsyncTask;

import com.rmuhamed.catalogogastronomia.UI.asynctask.SortTask;

/**
 * Created by rmuhamed on domingo.
 */
public class AsyncTaskUtils {
    public static boolean isPossibleToLaunchTask(AsyncTask anAsyncTask) {
        boolean possibleToLaunch = true;

        if(anAsyncTask!=null) {
            possibleToLaunch = !anAsyncTask.getStatus().equals(AsyncTask.Status.PENDING)
                                    && !anAsyncTask.getStatus().equals(AsyncTask.Status.RUNNING);
        }

        return possibleToLaunch;
    }

    public static void cancelTask(AsyncTask aTask) {
        if( aTask!=null &&
                (aTask.getStatus().equals(AsyncTask.Status.PENDING)
                || aTask.getStatus().equals(AsyncTask.Status.RUNNING)) ){
            aTask.cancel(true);
        }
    }
}
