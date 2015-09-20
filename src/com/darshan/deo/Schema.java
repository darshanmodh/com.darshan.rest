package com.darshan.deo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;

import com.darshan.util.ToJSON;

public class Schema extends OracleDB {

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

}
