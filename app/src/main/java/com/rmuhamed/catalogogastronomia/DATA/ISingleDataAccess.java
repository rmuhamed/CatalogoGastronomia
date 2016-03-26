/**
 * 
 */
package com.rmuhamed.catalogogastronomia.DATA;


import com.rmuhamed.catalogogastronomia.MODEL.IEntity;

import java.util.Collection;

/**
 * @author maltamira
 *
 */
public interface ISingleDataAccess {

	public void beginTransaction();
	
	public void commitTransaction();
	
	public void rollbackTransaction();
	
	public boolean create(IEntity entity);
	
	public boolean update(IEntity entity);
	
	public boolean delete(IEntity entity);
	
	public boolean exists(Object id);
	
	public boolean deleteAll();
	
	public IEntity findForId(Object id);
	
	public Collection<IEntity> findAll();
	
	public Collection<IEntity> findAllOrdered(String orderBy);

	public Collection<IEntity> findForParent(Object parentId);
}
