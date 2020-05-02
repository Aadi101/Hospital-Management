package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

import model.Appointment;

//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/apps")
public class AppointmentService {
	
	Appointment appointment = new Appointment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return appointment.readAppointment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("date") String date,
			@FormParam("time") String time, @FormParam("description") String description, @FormParam("status") String status,
			@FormParam("hospitalid") int hospitalid) {
		String output = appointment.insertAppointment(date, time, description, status, hospitalid);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String appData)
	{
	//Convert the input string to a JSON object
	JsonObject appointmentObject = new JsonParser().parse(appData).getAsJsonObject();
	//Read the values from the JSON object
	String appointid = appointmentObject.get("appointid").getAsString();
	String date = appointmentObject.get("date").getAsString();
	String time = appointmentObject.get("time").getAsString();
	String description = appointmentObject.get("description").getAsString();
	String status = appointmentObject.get("status").getAsString();
	String hospitalid = appointmentObject.get("hospitalid").getAsString();
	
	String output = appointment.updateAppointment(appointid, date, time, description, status, hospitalid);
	return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(String appData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(appData, "", Parser.xmlParser());
		// Read the value from the element <itemID>
		String appointid = doc.select("appointid").text();
		String output = appointment.deleteAppointment(appointid);
		return output;
	}

}
