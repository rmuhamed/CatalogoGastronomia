/**
 * 
 */
package com.rmuhamed.catalogogastronomia.MODEL;

import java.io.Serializable;

/**
 * @author maltamira
 *
 */
@SuppressWarnings("serial")
public abstract class Entity implements IEntity, Serializable {

	public abstract Object getId();

	@Override
	public int compareTo(IEntity o) {
		return 0;
	}
	
	public String getText(){
		return "Not implemented";
	}
}
