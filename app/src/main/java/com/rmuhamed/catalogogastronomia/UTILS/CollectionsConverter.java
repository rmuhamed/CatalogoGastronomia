package com.rmuhamed.catalogogastronomia.UTILS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionsConverter<T> {

	@SuppressWarnings("unchecked")
	public List<T> convert(Collection<Object> collection){
		List<T> list = new ArrayList<T>();
		for (Object t : collection) {
			list.add((T)t);
		}
		return list;
	}
	
	public List<Object> convertToInterface(Collection<T> collection){
		List<Object> list = new ArrayList<Object>();
		for (T t : collection) {
			list.add((Object)t);
		}
		return list;
	}
}
