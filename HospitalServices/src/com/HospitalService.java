package com;

import model.Hospital;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Hospitals")
public class HospitalService {
	Hospital hosObj = new Hospital();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return hosObj.readHospital();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertHospital(@FormParam("hosid") String id, @FormParam("hosname") String hosname,
			@FormParam("email") String email, @FormParam("prov") String prov, @FormParam("city") String city,
			@FormParam("pc") int pc, @FormParam("phn") int phn, @FormParam("er") Boolean er,
			@FormParam("surg") Boolean surg, @FormParam("xray") Boolean xray, @FormParam("lab") Boolean lab,
			@FormParam("gov") Boolean gov) {
		String output = hosObj.insertHospital(id, hosname, email, prov, city, pc, phn, er, surg, xray, lab, gov);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHospital(String hospitalData)
	{
	//Convert the input string to a JSON object
	JsonObject hospitalObject = new JsonParser().parse(hospitalData).getAsJsonObject();
	//Read the values from the JSON object
	String id = hospitalObject.get("id").getAsString();
	String hosname = hospitalObject.get("hosname").getAsString();
	String email = hospitalObject.get("email").getAsString();
	String prov = hospitalObject.get("prov").getAsString();
	String city = hospitalObject.get("city").getAsString();
	String pc = hospitalObject.get("pc").getAsString();
	String phn = hospitalObject.get("phn").getAsString();
	String er = hospitalObject.get("er").getAsString();
	String surg = hospitalObject.get("surg").getAsString();
	String xray = hospitalObject.get("xray").getAsString();
	String lab = hospitalObject.get("lab").getAsString();
	String gov = hospitalObject.get("gov").getAsString();
	String output = hosObj.updateHospital(id, hosname, email, prov, city, pc, phn, er, surg, xray, lab, gov);
	return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospital(String hospitalData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(hospitalData, "", Parser.xmlParser());
		// Read the value from the element <itemID>
		String id = doc.select("hosid").text();
		String output = hosObj.deleteHospital(id);
		return output;
	}
	
	

	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String getAppointments(@PathParam("id") int id)
	{	
		return hosObj.readAppointment(id);
	}

}
