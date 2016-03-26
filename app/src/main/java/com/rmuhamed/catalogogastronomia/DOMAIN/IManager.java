package com.rmuhamed.catalogogastronomia.DOMAIN;


import com.rmuhamed.catalogogastronomia.MODEL.IEntity;

import java.util.Collection;

public interface IManager {

	 Collection<IEntity> findAll();
	 
	 Collection<IEntity> findAllOrdered(String orderBy);
	 
	 IEntity save(IEntity entity);
	 
	 IEntity find(Object id);
	 
	 boolean delete(IEntity entity);
	 
	 boolean deleteAll();
}
