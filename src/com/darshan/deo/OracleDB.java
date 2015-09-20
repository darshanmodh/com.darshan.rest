package com.darshan.deo;

import javax.naming.*;
import javax.sql.*;

public class OracleDB {
	
	private static DataSource oracleDB = null;
	private static Context context = null;
	
	public static DataSource oracleDBConn() throws Exception {
		if (oracleDB != null) {
			return oracleDB;
		}
		try {
			if (context == null) {
				context = new InitialContext();
			}
			oracleDB = (DataSource)context.lookup("Database for Java REST Web Service");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return oracleDB;
	}

}
