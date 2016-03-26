package com.rmuhamed.catalogogastronomia.DATA;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.rmuhamed.catalogogastronomia.BUILDER.IBuilder;
import com.rmuhamed.catalogogastronomia.MODEL.IEntity;
import com.rmuhamed.catalogogastronomia.UTILS.DataConversionHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public abstract class SingleDataAccess extends DataAccess implements ISingleDataAccess {

	public SingleDataAccess(Context context, IBuilder builder){
		super(context, builder);
	}
	
	public SingleDataAccess(SingleDataAccess dataAccess, IBuilder builder){
		super(dataAccess.context, builder);
		this.database = dataAccess.database;
	}
	
	public boolean create(IEntity entity){
		if(this.database==null){
			this.openDB();
		}
		long quantity = 0;
		try {
			 quantity = this.database.insert(this.getTableName(), null, this.builder.createValues(entity));
			
		} catch (Exception e) {
			Log.e("create", "Fails inserting entity", e);
		} finally {
			if(this.database!=null && !this.database.inTransaction()){
				this.closeDB();
			}
		}
		return (quantity > 0);
	}
	
	public boolean update(IEntity entity){
		if(this.database==null){
			this.openDB();
		}
		long quantity = 0;
		try {
			quantity = this.database.update(this.getTableName(), this.builder.createValues(entity), this.getIdWhereClause(entity), null);
		} catch (Exception e) {
			Log.e("update", "Fails updating entity", e);
		} finally {
			if(this.database!=null && !this.database.inTransaction()){
				this.closeDB();
			}
		}
		return (quantity > 0);
	}
	
	public boolean delete(IEntity entity){
		if(this.database==null){
			this.openDB();
		}
		long quantity = 0;
		try {
			quantity = this.database.delete(this.getTableName(), this.getIdWhereClause(entity), null);
		} catch (Exception e) {
			Log.e("delete", "Fails deleting entity", e);
		} finally {
			if(this.database!=null && !this.database.inTransaction()){
				this.closeDB();
			}
		}
		return (quantity > 0);
	}
	
	public boolean deleteForParent(Object parentId){
		if(this.database==null){
			this.openDB();
		}
		long quantity = 0;
		try {
			quantity = this.database.delete(this.getTableName(), this.getParentIdWhereClauseForId(parentId), null);
		} catch (Exception e) {
			Log.e("delete", "Fails deleting entity for parent", e);
		} finally {
			if(this.database!=null && !this.database.inTransaction()){
				this.closeDB();
			}
		}
		return (quantity > 0);
	}
	
	protected boolean delete(String whereClause){
		if(this.database==null){
			this.openDB();
		}
		long quantity = 0;
		try {
			quantity = this.database.delete(this.getTableName(), whereClause, null);
		} catch (Exception e) {
			Log.e("delete", "Fails deleting entity/ies", e);
		} finally {
			if(this.database!=null && !this.database.inTransaction()){
				this.closeDB();
			}
		}
		return (quantity > 0);
	}
	
	public boolean exists(Object id){
		IEntity find = this.findForId(id);
		return (find!=null);
	}
	
	public boolean deleteAll(){
		if(this.database==null){
			this.openDB();
		}
		long quantity = 0;
		try {
			quantity = this.database.delete(this.getTableName(), null, null);
		} catch (Exception e) {
			Log.e("deleteAll", "Fails deleting all entities", e);
		} finally {
			if(this.database!=null && !this.database.inTransaction()){
				this.closeDB();
			}
		}
		return (quantity >= 0);
	}
	
	public IEntity findForId(Object id){
		IEntity result = null;
		if(this.database==null){
			this.openDB();
		}
		Cursor cursor = null;
		try {
			cursor = this.database.query(true, this.getTableName(), this.builder.getFieldsNames(), this.getIdWhereClauseForId(id), null, null, null, null, null);
			if(cursor!=null){
				if(cursor.moveToNext()){
					result = this.builder.build(cursor);
				}
			}
		} catch (Exception e) {
			Log.e("findForId", "Fails finding entity with id", e);
		} finally {
			if(cursor!=null){
				cursor.close();
			}
			if(this.database!=null && !this.database.inTransaction()){
				this.closeDB();
			}
		}
		return result;
	}
	
	@Override
	public Collection<IEntity> findForParent(Object parentId) {
		Collection<IEntity> result = new ArrayList<IEntity>();
		if(this.database==null){
			this.openDB();
		}
		Cursor cursor = null;
		try {
			cursor = this.database.query(true, this.getTableName(), this.builder.getFieldsNames(), this.getParentIdWhereClauseForId(parentId), null, null, null, null, null);
			if(cursor!=null){
				while (cursor.moveToNext()){
					result.add(this.builder.build(cursor));
				}
			}
		} catch (Exception e) {
			Log.e("Find", "Fails finding entity with parentId", e);
		} finally {
			if(cursor!=null){
				cursor.close();
			}
			if(this.database!=null && !this.database.inTransaction()){
				this.closeDB();
			}
		}
		return result;
	}

	public Collection<IEntity> findAllOrdered(String orderBy){
		Collection<IEntity> result = null;
		if(this.database==null){
			this.openDB();
		}
		Cursor cursor = null;
		try {
			cursor = this.database.query(true, this.getTableName(), this.builder.getFieldsNames(), null, null, null, null, orderBy, null);
			if(cursor!=null){
				result = new ArrayList<IEntity>();
				while(cursor.moveToNext()){
					IEntity entity = this.builder.build(cursor);
					if(entity!=null){
						result.add(entity);
					}
				}
			}
		} catch (Exception e) {
			Log.e("findAll", "Fails finding all entities", e);
		} finally {
			if(cursor!=null){
				cursor.close();
			}
			if(this.database!=null && !this.database.inTransaction()){
				this.closeDB();
			}
		}
		return result;
	}


	public Collection<IEntity> findAll(){
		Collection<IEntity> result = null;
		if(this.database==null){
			this.openDB();
		}
		Cursor cursor = null;
		try {
			cursor = this.database.query(true, this.getTableName(), this.builder.getFieldsNames(), null, null, null, null, null, null);
			if(cursor!=null){
				result = new ArrayList<IEntity>();
				while(cursor.moveToNext()){
					IEntity entity = this.builder.build(cursor);
					if(entity!=null){
						result.add(entity);
					}
				}
			}
		} catch (Exception e) {
			Log.e("findAll", "Fails finding all entities", e);
		} finally {
			if(cursor!=null){
				cursor.close();
			}
			if(this.database!=null && !this.database.inTransaction()){
				this.closeDB();
			}
		}
		return result;
	}

	public Collection<IEntity> query(String selection, String[] selectionArgs){
		Collection<IEntity> result = null;
		if(this.database==null){
			this.openDB();
		}
		Cursor cursor = null;
		try {
			cursor = this.database.query(true, this.getTableName(), this.builder.getFieldsNames(), selection, selectionArgs, null, null, null, null);
			if(cursor!=null){
				result = new ArrayList<IEntity>();
				while(cursor.moveToNext()){
					IEntity entity = this.builder.build(cursor);
					if(entity!=null){
						result.add(entity);
					}
				}
			}
		} catch (Exception e) {
			Log.e("query", "Fails executign query with selection string", e);
		} finally {
			if(cursor!=null){
				cursor.close();
			}
			if(this.database!=null && !this.database.inTransaction()){
				this.closeDB();
			}
		}
		return result;
	}
	
	protected String convertArgument(Object value){
		String result = "";
		if(value instanceof Integer){
			result = ((Integer)value).toString();
		}else if(value instanceof Date){
			Date date = (Date)value;
			result = DataConversionHelper.convertDateTimeDbToString(date);
		}else if(value instanceof Boolean){
			Boolean bool = (Boolean)value;
			Integer iValue = DataConversionHelper.convertBooleanToInteger(bool);
			result = iValue.toString();
		}else if(value instanceof Float){
			result = ((Float)value).toString();
		}else if(value instanceof Double){
			result = ((Double)value).toString();
		}
		else{
			result = value.toString();
		}
		return result;
	}

	public IEntity findForKey(String key){
		String selection = this.getKeyFieldWhereClause() + "=?";
		String[] args = new String[]{key};
		
		Collection<IEntity> result = this.query(selection, args);
		if (result != null && !result.isEmpty()){
			return ((ArrayList<IEntity>)result).get(0);
		}else{
			return null;
		}
	}

	protected abstract String getTableName();
	
	protected abstract String getIdWhereClause(IEntity entity);
	
	protected abstract String getIdWhereClauseForId(Object id);
	
	protected String getParentIdWhereClauseForId(Object parentId) {
		return null;
	}
	
	protected String getKeyFieldWhereClause(){
		return null;
	}
}
