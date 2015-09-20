package com.darshan.deo;

import javax.naming.*;
import javax.sql.*;
import java.sql.Connection;

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
	
	protected static Connection oraclePCPartsConnection() {
		Connection conn = null;
		try {
			conn = oracleDBConn().getConnection();
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}	//end of protected method

}
