package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class Hospital {

	// A common method to connect to the DB
		private Connection connect()
		{
			Connection con = null;
			String url = "jdbc:mysql://localhost:3306/pafdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection(url, "root", "");
			}
			catch (Exception e)
			{e.printStackTrace();}
			return con;
		}

		// insert function
		public String insertHospital(String id, String hosname, String email, String prov, String city,int pc,int phn,boolean er,boolean surg,boolean xray,boolean lab,boolean gov)
		{
			String output = "";
			try
			{
				Connection con = connect();
					if (con == null)
					{return "Error while connecting to the database for inserting."; }
					// create a prepared statement
					String query = " insert into hospital(`hosid`,`hosname`,`email`,`prov`,`city`,`pc`,`phn`,`er`,`surg`,`xray`,`lab`,`gov`)"
							+ " values (?,?,?,?,?,?,?,?,?,?,?,?)";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					preparedStmt.setString(1, id);
					preparedStmt.setString(2, hosname);
					preparedStmt.setString(3, email);
					preparedStmt.setString(4, prov);
					preparedStmt.setString(5, city);
					preparedStmt.setInt(6, pc);
					preparedStmt.setInt(7, phn);
					preparedStmt.setBoolean(8, er);
					preparedStmt.setBoolean(9, surg);
					preparedStmt.setBoolean(10, xray);
					preparedStmt.setBoolean(11, lab);
					preparedStmt.setBoolean(12, gov);

					// execute the statement
					preparedStmt.execute();
					con.close();
					
					String newHospital = readHospital();
					output = "{\"status\":\"success\", \"data\": \"" + newHospital + "\"}";
				}
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\": \"Error while inserting the hospital details. \"}";
					System.err.println(e.getMessage());
				}
				
				return output;
			}
			// read function
			public String readHospital()
			{
				String output = "";
					
				try
				{
					Connection con = connect();
					if (con == null)
					{return "Error while connecting to the database for reading."; }
					
					// Prepare the html table to be displayed
					output = "<table border='1\'><tr><th>ID</th><th>Hospital Name</th><th>Hospital Mail</th><th>Province</th><th>City</th><th>PostalCode</th><th>Phone No</th><th>ER</th><th>Surgery</th><th>Xray</th><th>Lab</th><th>Goverment</th><th>Update</th><th>Remove</th></tr>";
					
					String query = "select * from hospital";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					// iterate through the rows in the result set
					while (rs.next())
					{
						String id = Integer.toString(rs.getInt("hosid"));
						String hosName = rs.getString("hosname");
						String email = rs.getString("email");
						String provice =rs.getString("prov");
						String city = rs.getString("city");
						String pc = Integer.toString(rs.getInt("pc"));
						String phn = Integer.toString(rs.getInt("phn"));
						String er = Boolean.toString(rs.getBoolean("er"));
						String surg = Boolean.toString(rs.getBoolean("surg"));
						String xray = Boolean.toString(rs.getBoolean("xray"));
						String lab = Boolean.toString(rs.getBoolean("lab"));
						String gov = Boolean.toString(rs.getBoolean("gov"));
					
						// Add into the html table
						output += "<tr><td><input id='hidHosIDUpdate' name='hidHosIDUpdate' type='hidden' value='' + id + >" + id + "</td>";
						output += "<td>" + hosName + "</td>";
						output += "<td>" + email + "</td>";
						output += "<td>" + provice + "</td>";
						output += "<td>" + city + "</td>";
						output += "<td>" + pc + "</td>";
						output += "<td>" + phn + "</td>";
						output += "<td>" + er + "</td>";
						output += "<td>" + surg + "</td>";
						output += "<td>" + xray + "</td>";
						output += "<td>" + lab + "</td>";
						output += "<td>" + gov + "</td>";
					
						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-id='"
										 + id + "'>" + "</td></tr>";
								
					}
					
					con.close();
					
					// Complete the html table
					output += "</table>";
				}
				catch (Exception e)
				{
					output = "Error while reading the hospital details.";
					System.err.println(e.getMessage());
				}
				return output;
			}
	 		
			// Update function
			public String updateHospital(String id, String hosname, String email, String prov, String city,String pc,String phn,String er,String surg,String xray,String lab,String gov)
			{
			String output = "";
			
			try
			{
					Connection con = connect();
					
					if (con == null)
					{return "Error while connecting to the database for updating."; }
			
					// create a prepared statement
					String query = "update hospital set hosname=?,email=?,prov=?,city=?,pc=?,phn=?,er=?,surg=?,xray=?,lab=?,gov=? where hosid=?;";
									
					PreparedStatement preparedStmt = con.prepareStatement(query);
			
					// binding values
					preparedStmt.setString(1, hosname);
					preparedStmt.setString(2, email);
					preparedStmt.setString(3, prov);
					preparedStmt.setString(4, city);
					preparedStmt.setString(5, pc);
					preparedStmt.setString(6, phn);
					preparedStmt.setString(7, er);
					preparedStmt.setString(8, surg);
					preparedStmt.setString(9, xray);
					preparedStmt.setString(10, lab);
					preparedStmt.setString(11, gov);
					preparedStmt.setString(12, id);
					
			
					// execute the statement
					preparedStmt.execute();
					con.close();
			
					String newHospital = readHospital();
					output = "{\"status\":\"success\", \"data\": \"" + newHospital + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while updating the hospital details. \"}";
				System.err.println(e.getMessage());
			}
				return output;
		}
		//Delete function
		public String deleteHospital(String hosID)
		{
			String output = "";
			
			try
			{
				Connection con = connect();
				
				if (con == null)
				{return "Error while connecting to the database for deleting."; }
			
				// create a prepared statement
				String query = "delete from hospital where hosid=?";
			
				PreparedStatement preparedStmt = con.prepareStatement(query);
			
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(hosID));
			
				// execute the statement
				preparedStmt.execute();
				con.close();
			
				String newHospital = readHospital();
				output = "{\"status\":\"success\", \"data\": \"" + newHospital + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while deleting the hospital.\"}"; 				System.err.println(e.getMessage());
			}
			
			return output;
			}
		
		//getAppointments
		public String readAppointment(int id)
		{
			String output = "";
				
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for reading."; }
				
				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>ID</th><th>HospitalID</th><th>Date</th><th>Time</th><th>Decription</th><th>Status</th><th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from appointments where hospitalid=" + id;
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String appointid = Integer.toString(rs.getInt("appointid"));
					String date = rs.getString("date");
					String time = rs.getString("time");
					String description =rs.getString("description");
					String status = rs.getString("status");
					String hospitalid = Integer.toString(rs.getInt("hospitalid"));
				
				
					// Add into the html table
					output += "<tr><td>" + appointid + "</td>";
					output += "<td>" + date + "</td>";
					output += "<td>" + time + "</td>";
					output += "<td>" + description + "</td>";
					output += "<td>" + status + "</td>";
					output += "<td>" + hospitalid + "</td>";
				
					// buttons
					output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
									+ "<td><form method=\"post\" action=\"hospital.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
											+ "<input name=\"hosid\" type=\"hidden\" value=\"" + id
											+ "\">" + "</form></td></tr>";
				}
				
				con.close();
				
				// Complete the html table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the hospital details.";
				System.err.println(e.getMessage());
			}
			return output;
		}
}
