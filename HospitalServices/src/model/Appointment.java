package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Appointment {

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
			public String insertAppointment(String date, String time, String description, String status, int hospitalid)
			{
				String output = "";
				try
				{
					Connection con = connect();
						if (con == null)
						{return "Error while connecting to the database for inserting."; }
						// create a prepared statement
						String query = " insert into appointments(`date`,`time`,`description`,`status`,`hospitalid`)"
								+ " values (?,?,?,?,?)";
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						// binding values
						//preparedStmt.setInt(1, id);
						preparedStmt.setString(1, date);
						preparedStmt.setString(2, time);
						preparedStmt.setString(3, description);
						preparedStmt.setString(4, status);
						preparedStmt.setInt(5, hospitalid);

						// execute the statement
						preparedStmt.execute();
						con.close();
						
						output = "Inserted successfully";
					}
					catch (Exception e)
					{
						output = "Error while inserting the appointment details.";
						System.err.println(e.getMessage());
					}
					
					return output;
				}
				// read function
				public String readAppointment()
				{
					String output = "";
						
					try
					{
						Connection con = connect();
						if (con == null)
						{return "Error while connecting to the database for reading."; }
						
						// Prepare the html table to be displayed
						output = "<table border=\"1\"><tr><th>id</th><th>date</th><th>time</th><th>description</th><th>status</th><th>hospitalid</th>";
						
						String query = "select * from appointments";
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
//							output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
//											+ "<td><form method=\"post\" action=\"hospital.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
//													+ "<input name=\"doctorID\" type=\"hidden\" value=\"" + id
//													+ "\">" + "</form></td></tr>";
						}
						
						con.close();
						
						// Complete the html table
						output += "</table>";
					}
					catch (Exception e)
					{
						output = "Error while reading the appointment details.";
						System.err.println(e.getMessage());
					}
					return output;
				}
		 		
				// Update function
				public String updateAppointment(String appointid, String date, String time, String description, String status,String hospitalid)
				{
				String output = "";
				
				try
				{
						Connection con = connect();
						
						if (con == null)
						{return "Error while connecting to the database for updating."; }
				
						// create a prepared statement
						String query = "update appointments set date=?,time=?,description=?,status=?,hospitalid=? where appointid=?;";
										
						PreparedStatement preparedStmt = con.prepareStatement(query);
				
						// binding values
						preparedStmt.setString(1, date);
						preparedStmt.setString(2, time);
						preparedStmt.setString(3, description);
						preparedStmt.setString(4, status);
						preparedStmt.setString(5, hospitalid);
						preparedStmt.setString(6, appointid);
						
				
						// execute the statement
						preparedStmt.execute();
						con.close();
				
						output = "Updated successfully";
				}
				catch (Exception e)
				{
					output = "Error while updating the appointment details.";
					System.err.println(e.getMessage());
				}
					return output;
			}
			//Delete function
			public String deleteAppointment(String appointid)
			{
				String output = "";
				
				try
				{
					Connection con = connect();
					
					if (con == null)
					{return "Error while connecting to the database for deleting."; }
				
					// create a prepared statement
					String query = "delete from appointments where appointid=?";
				
					PreparedStatement preparedStmt = con.prepareStatement(query);
				
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(appointid));
				
					// execute the statement
					preparedStmt.execute();
					con.close();
				
					output = "Deleted successfully";
				}
				catch (Exception e)
				{
					output = "Error while deleting the appointment details.";
					System.err.println(e.getMessage());
				}
				
				return output;
				}
}
