package com.darshan.util;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import java.sql.ResultSet;

import org.owasp.esapi.ESAPI;

public class ToJSON {
	
	public JSONArray toJSONArray(ResultSet rs) throws Exception {
		
		String temp = null;
		JSONArray json = new JSONArray();
		
		try {
			
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			
			while(rs.next()) {
				int numColumns = rsmd.getColumnCount();
				
				JSONObject obj = new JSONObject();
				
				for(int i=1; i<=numColumns; i++) {
					String columnName = rsmd.getColumnName(i);
					
					if(rsmd.getColumnType(i) == java.sql.Types.ARRAY) {
						obj.put(columnName, rs.getArray(columnName));
						System.out.println("ToJSON: ARRAY");
					}
					else if (rsmd.getColumnType(i) == java.sql.Types.BIGINT) {
						obj.put(columnName, rs.getInt(columnName));
						System.out.println("ToJSON: BIGINT");
					}
					else if (rsmd.getColumnType(i) == java.sql.Types.BOOLEAN) {
						obj.put(columnName, rs.getBoolean(columnName));
						System.out.println("ToJSON: Boolean");
					}
					else if (rsmd.getColumnType(i) == java.sql.Types.BLOB) {
						obj.put(columnName, rs.getBlob(columnName));
						System.out.println("ToJSON: BLOB");
					}
					else if (rsmd.getColumnType(i) == java.sql.Types.DOUBLE) {
						obj.put(columnName, rs.getDouble(columnName));
						System.out.println("ToJSON: Double");
					}
					else if (rsmd.getColumnType(i) == java.sql.Types.FLOAT) {
						obj.put(columnName, rs.getFloat(columnName));
						System.out.println("ToJSON: Float");
					}
					else if (rsmd.getColumnType(i) == java.sql.Types.INTEGER) {
						obj.put(columnName, rs.getInt(columnName));
						System.out.println("ToJSON: Integer");
					}
					else if (rsmd.getColumnType(i) == java.sql.Types.NVARCHAR) {
						obj.put(columnName, rs.getNString(columnName));
						System.out.println("ToJSON: NVarchar");
					}
					else if (rsmd.getColumnType(i) == java.sql.Types.VARCHAR) {
						temp = rs.getString(columnName);
						temp = ESAPI.encoder().canonicalize(temp);
						temp = ESAPI.encoder().encodeForHTML(temp);
						obj.put(columnName, temp);
					
//						obj.put(columnName, rs.getString(columnName));
//						System.out.println("ToJSON: varchar");
					}
					else if (rsmd.getColumnType(i) == java.sql.Types.TINYINT) {
						obj.put(columnName, rs.getInt(columnName));
						System.out.println("ToJSON: TinyInt");
					}
					else if (rsmd.getColumnType(i) == java.sql.Types.SMALLINT) {
						obj.put(columnName, rs.getInt(columnName));
						System.out.println("ToJSON: samllInt");
					}
					else if (rsmd.getColumnType(i) == java.sql.Types.DATE) {
						obj.put(columnName, rs.getDate(columnName));
						System.out.println("ToJSON: date");
					}
					else if (rsmd.getColumnType(i) == java.sql.Types.TIMESTAMP) {
						obj.put(columnName, rs.getTimestamp(columnName));
						System.out.println("ToJSON: Timestamp");
					}
					else if (rsmd.getColumnType(i) == java.sql.Types.NUMERIC) {
						obj.put(columnName, rs.getBigDecimal(columnName));
						System.out.println("ToJSON: numberic");
					}
					else {
						obj.put(columnName, rs.getObject(columnName));
						System.out.println("ToJSON: Object"+columnName);
					}
				} //end of for looop
				json.put(obj);
			} //end of whileloop
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return json;
	}

}
