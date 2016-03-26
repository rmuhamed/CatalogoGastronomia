/**
 * 
 */
package com.rmuhamed.catalogogastronomia.BUILDER;

import android.content.ContentValues;
import android.database.Cursor;

import com.rmuhamed.catalogogastronomia.MODEL.IEntity;


/**
 * @author rmuhamed
 *
 */
public interface IBuilder {
	
	public IEntity build(Cursor cursor);
	
	public IEntity build(Cursor cursor, IEntity entity);
	
	public ContentValues createValues(IEntity entity);
	
	public String[] getFieldsNames();
	
}
