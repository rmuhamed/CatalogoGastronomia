package com.rmuhamed.catalogogastronomia.DATA;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.rmuhamed.catalogogastronomia.BUILDER.IBuilder;
import com.rmuhamed.catalogogastronomia.CONNECTION.DatabaseInteractor;
import com.rmuhamed.catalogogastronomia.MODEL.IEntity;

import java.util.ArrayList;
import java.util.Collection;


public abstract class DataAccess {

	protected Context context = null;
	protected DatabaseInteractor databaseHelper = null;
	protected SQLiteDatabase database = null;
	protected IBuilder builder = null;
	
	public DataAccess(Context context, IBuilder builder){
		this.context = context;
		this.databaseHelper = new DatabaseInteractor(context);
		this.builder = builder;
	}
	
	protected void openDB() throws SQLException {
		this.database = this.databaseHelper.getWritableDatabase();
	}
	
	protected void closeDB(){
		this.databaseHelper.close();
		this.database = null;
	}
	
	public void beginTransaction(){
		if(database==null){
			this.openDB();
		}
		if(!this.database.inTransaction()){
			this.database.beginTransaction();
		}
	}
	
	public void commitTransaction(){
		if(this.database.inTransaction()){
			this.database.setTransactionSuccessful();
			this.database.endTransaction();
			this.closeDB();
		}
	}
	
	public void rollbackTransaction(){
		if(this.database.inTransaction()){
			this.database.endTransaction();
			this.closeDB();
		}
	}
	
	protected Collection<IEntity> queryFull(String query, String[] arguments){
		Collection<IEntity> entities = null;
		
		if(this.database==null){
			this.openDB();
		}
		Cursor cursor = null;
		try {
			cursor = this.database.rawQuery(query, arguments);
			if(cursor!=null){
				entities = new ArrayList<IEntity>();
				while(cursor.moveToNext()){
					IEntity entity = this.builder.build(cursor);
					if(entity!=null){
						entities.add(entity);
					}
				}
			}
		} catch (Exception e) {
			entities = null;
		} finally {
			if(cursor!=null){
				cursor.close();
			}
			if(this.database!=null && !this.database.inTransaction()){
				this.closeDB();
			}
		}
		
		return entities;
	}
}
