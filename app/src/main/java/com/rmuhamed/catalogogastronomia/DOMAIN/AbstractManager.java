package com.rmuhamed.catalogogastronomia.DOMAIN;

import java.util.ArrayList;
import java.util.Collection;

import android.content.Context;
import android.util.Log;

import com.rmuhamed.catalogogastronomia.DATA.ISingleDataAccess;
import com.rmuhamed.catalogogastronomia.MODEL.IEntity;


public class AbstractManager implements IManager {
	protected ISingleDataAccess dataAccess;
	protected Context context;

	public AbstractManager(ISingleDataAccess dataAccess, Context context){
		this.dataAccess = dataAccess;
		this.context = context;
	}
	
	public Collection<IEntity> findByParent(Object parentId){
		Collection<IEntity> result = null;
		result = this.dataAccess.findForParent(parentId);
		result = this.fillRelations(result);
		return result;
	}
	
	/**
	 * Obtain only one element associated with parentId
	 * @author rmuhamed - Quares IT Solutions
	 * @param parentId
	 * @return IEntity
	 */
	public IEntity findEntityByParent(Object parentId){
		IEntity entity = null;
		Collection<IEntity> result = null;
		result = this.dataAccess.findForParent(parentId);
		
		if(result!=null && result.size()>0){
			entity = ((ArrayList<IEntity>) result).get(0);
			entity = this.fillRelations(entity);
		}
		
		return entity;
	}
	
	public Collection<IEntity> fillRelations(Collection<IEntity> entities){
		Collection<IEntity> result = new ArrayList<IEntity>();
		if(entities!=null && !entities.isEmpty()){
			for (IEntity iEntity : entities) {
				result.add(this.fillRelations(iEntity));
			}
		}
		return result;
	}
	
	public IEntity fillRelations(IEntity entity){
		return entity;
	}	
	
	@Override
	public Collection<IEntity> findAllOrdered(String orderBy) {
		Collection<IEntity> objects = null;
		try {
			objects = this.dataAccess.findAllOrdered(orderBy);
			objects = this.fillRelations(objects);
		} catch (Exception e) {
			Log.e(this.getClass() + " - getAll", "Error obtaining data list", e);
		}
		return objects; 
	}
	
	@Override
	public Collection<IEntity> findAll() {
		Collection<IEntity> objects = null;
		try {
			objects = this.dataAccess.findAll();
			objects = this.fillRelations(objects);
		} catch (Exception e) {
			Log.e(this.getClass() + " - getAll", "Error obtaining data list", e);
		}
		return objects; 
	}
	
	@Override
	public boolean deleteAll() {
		boolean succed = false;
		try {
			succed = this.dataAccess.deleteAll();
		} catch (Exception e) {
			Log.e(this.getClass() + " - deleteAll", "Error deleting data list", e);
		}
		return succed; 
	}

	@Override
	public IEntity save(IEntity entity) {
		try {
			if(this.dataAccess.exists(entity.getId())){
				this.dataAccess.update(entity);
				this.saveRelations(entity);
			}else{
				this.dataAccess.create(entity);
				this.saveRelations(entity);
			}
		} catch (Exception e) {
			entity = null;
			Log.e(this.getClass() + " - save", "Error creating or updating data", e);
		}
		
		return entity;
	}
	
	public void saveRelations(IEntity entity){
		// implement when necessary
	}

	@Override
	public IEntity find(Object id) {
		IEntity entity = null;
		try {
			entity = this.dataAccess.findForId(id);
			entity = this.fillRelations(entity);
		} catch (Exception e) {
			entity = null;
			Log.e(this.getClass() + " - find", "Error deleting data", e);
		}
		return entity;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public ISingleDataAccess getDataAccess() {
		return dataAccess;
	}

	@Override
	public boolean delete(IEntity entity) {
		boolean deletedEntity = true;
		boolean deletedEntityRelations = true;
		try{
			deletedEntityRelations = this.deleteRelations(entity);
			deletedEntity = this.dataAccess.delete(entity);
			
		} catch (Exception e) {
			entity = null;
			Log.e(this.getClass() + " - delete", "Error deleting data", e);
		}
		
		boolean succed = deletedEntity && deletedEntityRelations;
		
		return succed;
	}
	
	//Subclass responsibility
	public int count(Object id){
		return 0;
	}
	
	//Subclass responsibility
	public boolean deleteRelations(IEntity entity){
		return true;
	}
}
