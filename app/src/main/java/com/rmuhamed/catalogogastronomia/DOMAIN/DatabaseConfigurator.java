package com.rmuhamed.catalogogastronomia.DOMAIN;

import java.io.Reader;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.rmuhamed.catalogogastronomia.CONNECTION.DatabaseInteractor;
import com.rmuhamed.catalogogastronomia.CONNECTION.DatabaseStructure;

public class DatabaseConfigurator {

	public static DatabaseConfigurator instance = new DatabaseConfigurator();
	private int version;
	private Context context;

	private DatabaseConfigurator(){
		super();
	}
	
	public static DatabaseConfigurator getInstance(){
		return DatabaseConfigurator.instance;
	}
	
	public boolean configure(int versionCode, Map<Integer, Reader> updates){
		boolean upgradedToVersion = false;
		DatabaseInteractor.setDatabaseVersion(versionCode);
		
		this.setVersion(versionCode);
		
		DatabaseStructure.getInstance().setUpdates(updates);
		
		// Forces Database creation
		DatabaseInteractor interactor = new DatabaseInteractor(this.context);
		SQLiteDatabase db = interactor.getWritableDatabase();

		//check if the upgrade was succeed
		upgradedToVersion = db.getVersion() == versionCode;
		//now we close the DB connection opened before...
		db.close();

		return upgradedToVersion;
	}
	
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
