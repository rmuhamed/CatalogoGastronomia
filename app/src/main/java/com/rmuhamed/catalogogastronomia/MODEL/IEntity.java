package com.rmuhamed.catalogogastronomia.MODEL;

public interface IEntity extends Comparable<IEntity> {

	public Object getId();
	
	public String getText();
}
