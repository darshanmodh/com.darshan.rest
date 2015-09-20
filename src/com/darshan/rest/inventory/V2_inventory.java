package com.darshan.rest.inventory;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.darshan.deo.Schema;

@Path("v2/inventory")
public class V2_inventory {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrandParts(
			@QueryParam("brand") String brand) throws Exception {
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try {
			if (brand == null)
				return Response.status(400).entity("Error: Please specify brand for the search.").build();
			Schema dao = new Schema();
			json = dao.queryReturnsBrandParts(brand);
			returnString = json.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		return Response.ok(returnString).build();
	}
	
	@GET
	@Path("/{brand}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrand(
			@PathParam("brand") String brand) throws Exception {
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try {
			Schema dao = new Schema();
			json = dao.queryReturnsBrandParts(brand);
			returnString = json.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		return Response.ok(returnString).build();
	}
	
	/*
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnErrorOnBrand() throws Exception {
		return Response.status(400).entity("Error: Please specify brand for the search.").build();
	}
	*/
	
	@GET
	@Path("/{brand}/{item_number}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnSpecificBrandItem(
			@PathParam("brand") String brand,
			@PathParam("item_number") int item_number) throws Exception {
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try {
			Schema dao = new Schema();
			json = dao.queryReturnsBrandItemNumber(brand, item_number);
			returnString = json.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		return Response.ok(returnString).build();
	}
	

}
