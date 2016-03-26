package com.rmuhamed.catalogogastronomia.CONNECTION;

import java.util.ArrayList;

public class CustomDatabaseStructure extends DatabaseStructure{ 
	public CustomDatabaseStructure(){
		super(); 
		this.setTables(new ArrayList<String>());
		this.getTables().add("CREATE TABLE Branch (id INTEGER PRIMARY KEY, fatherid INTEGER, fathername TEXT, name TEXT, street TEXT, streetnumber TEXT, floor TEXT, phone TEXT, webpage TEXT);");
		this.getTables().add("CREATE TABLE ImageFather (url TEXT PRIMARY KEY, type INTEGER, great INTEGER, image BLOB);");

	}
}
