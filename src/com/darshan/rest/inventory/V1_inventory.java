package com.darshan.rest.inventory;

import java.sql.*;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.darshan.deo.OracleDB;
import com.darshan.util.ToJSON;

@Path("/v1/inventory")
public class V1_inventory {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllPCParts() throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		String returnString = null;
		Response rb = null;
		
		try {
			conn = OracleDB.oracleDBConn().getConnection();
			query = conn.prepareStatement("select * from PC_PARTS");
			ResultSet rs = query.executeQuery();
			ToJSON converter = new ToJSON();
			JSONArray json = new JSONArray();
			
			json = converter.toJSONArray(rs);
			query.close();
			
			returnString = json.toString();
			
			rb = Response.ok(returnString).build();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return rb;
	}

}
