package com.ecmdeveloper.eds.beans;

import java.util.ArrayList;
import java.util.HashMap;

public class LogDatabaseResponse {
	
	public void log(ArrayList<HashMap<String, Object>> response) {
		for ( HashMap<String, Object> row : response ) {
			logRow(row);
		}
	}

	private void logRow(HashMap<String, Object> row) {
		for ( String columnName : row.keySet() ) {
			System.out.println( columnName + ":\t" + row.get(columnName)  );
		}
		System.out.println("===");
	}
}
