package com.rmuhamed.catalogogastronomia.UTILS;

import android.os.AsyncTask;

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
}
