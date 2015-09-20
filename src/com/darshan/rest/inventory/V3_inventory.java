package com.darshan.rest.inventory;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.darshan.deo.Schema;

@Path("/v3/inventory")
public class V3_inventory {

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPCParts2(String incomingData) throws Exception {

		String returnString = null;
		JSONArray json = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		Schema dao = new Schema();

		try {
			JSONObject partsData = new JSONObject(incomingData);
			System.out.println("jsonData: " + partsData.toString());

			int http_code = dao.insertIntoPCParts(
					partsData.optString("PC_PARTS_TITLE"),
					partsData.optString("PC_PARTS_CODE"),
					partsData.optString("PC_PARTS_MAKER"),
					partsData.optString("PC_PARTS_AVAIL"),
					partsData.optString("PC_PARTS_DESC"));

			if (http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG",
						"Item has been entered successfully, version 3");
				returnString = json.put(jsonObject).toString();
			} else {
				return Response.status(500).entity("Unable to enter Item")
						.build();
			}
			System.out.println("returnString:" + returnString);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(500)
					.entity("Server was not able to process your Request.")
					.build();
		}
		return Response.ok(returnString).build();
	}

	@PUT
	@Path("/{brand}/{item_number}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateItem(@PathParam("brand") String brand,
			@PathParam("item_number") int item_number, String incomingData)
			throws Exception {

		int pk;
		int avail;
		int http_code;
		String returnString;
		JSONArray json = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		Schema dao = new Schema();

		try {

			JSONObject partData = new JSONObject(incomingData);
			pk = partData.optInt("PC_PARTS_PK", 0);
			avail = Integer.parseInt(partData.optString("PC_PARTS_AVAIL",
					"None"));

			http_code = dao.updatePCParts(pk, avail);

			if (http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been updated successfully.");
			} else {
				return Response.status(500)
						.entity("Server was not able to process your request.")
						.build();
			}
			returnString = json.put(jsonObject).toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(500)
					.entity("Server was not able to process your request.")
					.build();
		}
		return Response.ok(returnString).build();

	}

	@DELETE
	@Path("/{brand}/{item_number}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteItem(@PathParam("brand") String brand,
			@PathParam("item_number") int item_number, String incomingData)
			throws Exception {

		int pk;
		int http_code;
		String returnString;
		JSONArray json = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		Schema dao = new Schema();

		try {

			JSONObject partData = new JSONObject(incomingData);
			pk = partData.optInt("PC_PARTS_PK", 0);

			http_code = dao.deletePCPart(pk);

			if (http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been deleted successfully.");
			} else {
				return Response.status(500)
						.entity("Server was not able to process your request.")
						.build();
			}
			returnString = json.put(jsonObject).toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(500)
					.entity("Server was not able to process your request.")
					.build();
		}
		return Response.ok(returnString).build();

	}
}
