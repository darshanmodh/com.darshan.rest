package com.darshan.deo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;

import com.darshan.util.ToJSON;

public class Schema extends OracleDB {
	
	public int insertIntoPCParts(String PC_PARTS_TITLE, 
			String PC_PARTS_CODE, 
			String PC_PARTS_MAKER, 
			String PC_PARTS_AVAIL, 
			String PC_PARTS_DESC) throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		try {
			conn = oracleDBConn().getConnection();
			query = conn.prepareStatement("insert into PC_PARTS " + 
					"(PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC)" +
					"VALUES ( ?, ?, ?, ?, ? )");
			query.setString(1, PC_PARTS_TITLE);
			query.setString(2, PC_PARTS_CODE);
			query.setString(3, PC_PARTS_MAKER);
			query.setString(4, PC_PARTS_AVAIL);
			query.setString(5, PC_PARTS_DESC);
			query.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 500;
		} finally {
			if (conn != null)
				conn.close();
		}
		return 200;
	}
	

	public JSONArray queryReturnsBrandParts(String brand) throws Exception {

		PreparedStatement query = null;
		Connection conn = null;

		JSONArray json = new JSONArray();
		ToJSON converter = new ToJSON();

		try {
			conn = oraclePCPartsConnection();
			query = conn
					.prepareStatement("select PC_PARTS_PK, PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC "
							+ "from PC_PARTS "
							+ "where UPPER(PC_PARTS_MAKER) = ? ");
			query.setString(1, brand.toUpperCase());
			ResultSet rs = query.executeQuery();

			json = converter.toJSONArray(rs);
			query.close();

		} catch (SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return json;
	}

	public JSONArray queryReturnsBrandItemNumber(String brand, int item_number) throws Exception {

		PreparedStatement query = null;
		Connection conn = null;

		JSONArray json = new JSONArray();
		ToJSON converter = new ToJSON();

		try {
			conn = oraclePCPartsConnection();
			query = conn
					.prepareStatement("select PC_PARTS_PK, PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC "
							+ "from PC_PARTS "
							+ "where UPPER(PC_PARTS_MAKER) = ? "
							+ "and PC_PARTS_CODE = ? ");
			query.setString(1, brand.toUpperCase());
			query.setInt(2, item_number);
			ResultSet rs = query.executeQuery();

			json = converter.toJSONArray(rs);
			query.close();

		} catch (SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return json;
	}


	public int updatePCParts(int pk, int avail) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement query = null;
		Connection conn = null;
		try {
			conn = oracleDBConn().getConnection();
			query = conn.prepareStatement("update PC_PARTS " + 
					"set PC_PARTS_AVAIL = ? " +
					"where PC_PARTS_PK = ? ");
			query.setInt(1, pk);
			query.setInt(2, avail);
			query.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 500;
		} finally {
			if (conn != null)
				conn.close(); 
		}
		return 200;
	}


	public int deletePCPart(int pk) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement query = null;
		Connection conn = null;
		try {
			conn = oracleDBConn().getConnection();
			query = conn.prepareStatement("delete from PC_PARTS " + 
					"where PC_PARTS_PK = ? ");
			query.setInt(1, pk);
			query.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 500;
		} finally {
			if (conn != null)
				conn.close(); 
		}
		return 200;
	}

}
