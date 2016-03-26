package com.rmuhamed.catalogogastronomia.CONNECTION;

import java.util.ArrayList;

public class CustomDatabaseStructure extends DatabaseStructure{ 
	public CustomDatabaseStructure(){
		super(); 
		this.setTables(new ArrayList<String>());
		this.getTables().add("CREATE TABLE Valet (id INTEGER PRIMARY KEY, code TEXT, firstname TEXT, lastname TEXT, email TEXT, phone TEXT, coderh TEXT, picture_blob BLOB);");
		this.getTables().add("CREATE TABLE Parking (parkingid TEXT PRIMARY KEY, workingday_id TEXT, parkingNumber INTEGER, licenseplate TEXT, licenseplateimageguid TEXT, carvideoguid TEXT, checkintime TEXT, description TEXT, custom1 TEXT, custom2 TEXT, latitude REAL, longitude REAL, geoloctype TEXT, addresstype TEXT, street TEXT, postalcode TEXT, city TEXT, country TEXT, complement TEXT, checkouttime TEXT, amountnet REAL, vatamount REAL, amount REAL, paymentmode TEXT, incident INTEGER, valetin_id INTEGER, valetout_id INTEGER, state TEXT, invoicenumber TEXT, email TEXT, created_at TEXT, updated_at TEXT, checkInPending INTEGER, checkOutPending INTEGER, parkingPending INTEGER, videoPending INTEGER, licensePlateImageBlob BLOB, vehicle_id INTEGER, vehicle_externalcar INTEGER, vehicle_brand TEXT, vehicle_model TEXT, vehicle_color TEXT, vehicle_comments TEXT, endcustomer_id INTEGER, endcustomer_name TEXT, endcustomer_phone TEXT);");
		
		this.getTables().add("CREATE TABLE Incident (incidentid TEXT PRIMARY KEY, parkingid TEXT, incidentdatetime TEXT, description TEXT, type_id INTEGER, detail TEXT, pictureid TEXT, valet_id INTEGER, created_at TEXT, updated_at TEXT, incidentPending INTEGER);");
		this.getTables().add("CREATE TABLE IncidentType (id INTEGER PRIMARY KEY, code TEXT, description TEXT, activestatus INTEGER);");
		this.getTables().add("CREATE TABLE Location (id INTEGER PRIMARY KEY, code TEXT, description TEXT, customer_id TEXT, typelocation_id INTEGER, sector_id INTEGER, address TEXT, zip TEXT, city TEXT, country TEXT, mainlocation INTEGER, facturationtype TEXT, amountnetREAL , vatrate REAL, totalamount REAL, latitude REAL, longitude REAL, geoloctype TEXT, addresstype TEXT, activestatus INTEGER);");
		this.getTables().add("CREATE TABLE WorkingDay (workingdayid TEXT PRIMARY KEY, date TEXT, starttime TEXT, stoptime TEXT, valet_id INTEGER, startlongitude REAL, stoplongitude REAL, startlatitude REAL, stoplatitude REAL, location_id INTEGER, type TEXT, startPending INTEGER, endPending INTEGER);");
	}
}
