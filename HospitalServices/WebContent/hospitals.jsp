<%@page import="model.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%
	//Initilize----------------------------------------------
	session.setAttribute("statusMsg", "");
	System.out.println("Trying to process...");
	
	//Save----------------------------------------------------
	if(request.getParameter("hosid") != null)
	{
		Hospital hosObj = new Hospital();
		String stsMsg = "";
		
		//Insert----------------------------------
		if(request.getParameter("hidHosIDSave") == "")
		{
			boolean emer= Boolean.parseBoolean(request.getParameter("er"));
			boolean surg= Boolean.parseBoolean(request.getParameter("surg"));
			boolean xray= Boolean.parseBoolean(request.getParameter("xray"));
			boolean lab= Boolean.parseBoolean(request.getParameter("lab"));
			boolean gov= Boolean.parseBoolean(request.getParameter("gov"));
			int poscode = Integer.parseInt(request.getParameter("pc"));
			int phone = Integer.parseInt(request.getParameter("phn"));
			
			
			stsMsg = hosObj.insertHospital(request.getParameter("id"),
										   request.getParameter("hosname"),
										   request.getParameter("email"),
										   request.getParameter("prov"),
										   request.getParameter("city"),
										   poscode,
										   phone,
										   emer,
										   surg,
										   xray,
										   lab,
										   gov );
		}
		else //Update
		{
			stsMsg = hosObj.updateHospital(request.getParameter("id"),
					request.getParameter("hosname"),
					request.getParameter("email"),
					request.getParameter("prov"),
					request.getParameter("city"),
					request.getParameter("pc"),
					request.getParameter("phn"),
					request.getParameter("er"),
					request.getParameter("surg"),
					request.getParameter("xray"),
					request.getParameter("lab"),
					request.getParameter("gov") );
		}
		
		session.setAttribute("statusMsg", stsMsg);
	}
	
	//DELETE--------------------------------------------------
	if(request.getParameter("hidHosIDDelete") != null)
	{
		Hospital hosObj = new Hospital();
		String stsMsg = hosObj.deleteHospital(request.getParameter("hidHosIDDelete"));
		session.setAttribute("statusMsg", stsMsg);
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/css/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<title>Hospital Management</title>
<style>
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1 style="text-align:center">Hospital Management</h1>
				<form id="formHospital" name="formHospital" method="post" action="hospitals.jsp">
				  	Hospital ID:
				  	<input id="hosID" name="hosID" type="text" 
				 	         class="form-control form-control-sm">
				  	<br> Hospital Name:
				  	<input id="hosName" name="hosName" type="text"
				         	 class="form-control form-control-sm">
				   	<br> Hospital Email:
				   	<input id="hosMail" name="hosMail" type="text"
				           		class="form-control form-control-sm">
				   	<br> Province:
				   	<input id="prov" name="prov" type="text"
				           	  class="form-control form-control-sm">
				    <br> City:
				   	<input id="city" name="city" type="text"
				           		class="form-control form-control-sm">
				     <br> Postal Code:
				   	<input id="posCode" name="posCode" type="text"
				           		class="form-control form-control-sm">
				    <br> Phone Number:
				   	<input id="phnNo" name="phnNo" type="text"
				           		class="form-control form-control-sm">
				    <br> Services:
				    <br>
				    <input id="er" name="er" type="checkbox" value="ER">
				    <label for="er">Emergency Room</label>
				    <input id="surg" name="surg" type="checkbox" value="Surgery">
				    <label for="surg">Surgery Room</label>
				    <br>
				    <input id="xray" name="xray" type="checkbox" value="XRay">
				    <label for="xray">XRay</label>
				    <input id="lab" name="lab" type="checkbox" value="Lab">
				    <label for="lab">Laboratory</label>
				    <br> Government :
				    <br>
				    <input id="gov" name="gov" type="checkbox" value="Government">
				    <label for="gov">Government</label>
				    <br> 
				    <input id="btnSave" name="btnSave" type="button" value="Save"
				          class="btn btn-primary">
				    <input type="hidden" id="hidHosIDSave" name="hidHosIDSave" value="">
				  </form>
				  
				  <div id="alertSuccess" class="alert alert-success">
				  		<%
				  			out.print(session.getAttribute("statusMsg"));
				  		%>
				  </div>
				  <div id="alertError" class="alert alert-danger"></div>
				  
				  <br>
				  <%
				  	Hospital hosObj = new Hospital();
				    out.print(hosObj.readHospital());
				  %>
			  </div>
		  </div>
	  </div>
</body>
</html>