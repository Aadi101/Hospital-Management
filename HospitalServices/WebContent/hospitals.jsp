<%@page import="com.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/css/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src=Components/hospital.js></script>
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
				    <p>Emergency Room:</p>
					<input type="radio" id="er" name="er" value="true">
					<label for="true">True</label>
					<input type="radio" id="er" name="er" value="false">
					<label for="false">False</label>
					<br>
					<p>Surgery Room:</p>
					<input type="radio" id="surg" name="surg" value="true">
					<label for="true">True</label>
					<input type="radio" id="surg" name="surg" value="false">
					<label for="false">False</label>
					<br>
				    <p>XRay:</p>
					<input type="radio" id="xray" name="xray" value="true">
					<label for="true">True</label>
					<input type="radio" id="xray" name="xray" value="false">
					<label for="false">False</label>
					<br>
					<p>Lab:</p>
					<input type="radio" id="lab" name="lab" value="true">
					<label for="true">True</label>
					<input type="radio" id="lab" name="lab" value="false">
					<label for="false">False</label>
					<br>
					<p>Government:</p>
					<input type="radio" id="gov" name="gov" value="true">
					<label for="true">True</label>
					<input type="radio" id="gov" name="gov" value="false">
					<label for="false">False</label>
					<br> 
				    <input id="btnSave" name="btnSave" type="button" value="Save"
				          class="btn btn-primary">
				    <input type="hidden" id="hidHosIDSave" name="hidHosIDSave" value="">
				  </form>
				  
				  <div id="alertSuccess" class="alert alert-success"></div>
				  <div id="alertError" class="alert alert-danger"></div>
				  
				  <br>
				  <div id = "divHospitalGrid">
				  <%
				  	Hospital hosObj = new Hospital();
				    out.print(hosObj.readHospital());
				  %>
				  </div>
			  </div>
		  </div>
	  </div>
</body>
</html>