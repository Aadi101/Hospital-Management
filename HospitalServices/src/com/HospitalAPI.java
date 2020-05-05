package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HospitalAPI
 */
@WebServlet("/HospitalAPI")
public class HospitalAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Hospital hosObj = new Hospital();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HospitalAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
		boolean emer= Boolean.parseBoolean(request.getParameter("er"));
		boolean surg= Boolean.parseBoolean(request.getParameter("surg"));
		boolean xray= Boolean.parseBoolean(request.getParameter("xray"));
		boolean lab= Boolean.parseBoolean(request.getParameter("lab"));
		boolean gov= Boolean.parseBoolean(request.getParameter("gov"));
		int poscode = Integer.parseInt(request.getParameter("posCode"));
		int phone = Integer.parseInt(request.getParameter("phnNo"));
		
		String output = hosObj.insertHospital(request.getParameter("hosID"),
												request.getParameter("hosName"),
												request.getParameter("hosMail"),
												request.getParameter("prov"),
												request.getParameter("city"),
												poscode,
												phone,
												emer,
												surg,
												xray,
												lab,
												gov);
		
		
		response.getWriter().write(output);
														
				
		// TODO Auto-generated method stub
//		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		
		System.out.println("PUT function called");
		//Replacing numbers and special characters while updating 
		String hosMail = paras.get("hosMail").toString();
		String NewhosMail = hosMail.replace("%40", "@");
		
		String hosName = paras.get("hosName").toString();
		String newhosName = hosName.replace("+", " ");
		
		String output = hosObj.updateHospital(
				paras.get("hosID").toString(),
				newhosName,
				NewhosMail,
				paras.get("prov").toString(),
				paras.get("city").toString(),
				paras.get("posCode").toString(),
				paras.get("phnNo").toString(),
				convertTobool(paras.get("er").toString()),
				convertTobool(paras.get("surg").toString()),
				convertTobool(paras.get("xray").toString()),
				convertTobool(paras.get("lab").toString()),
				convertTobool(paras.get("gov").toString()));
		
		response.getWriter().write(output);
	}
	
	//Converting to Boolean values
	public String convertTobool(String inputBool) {
		if (inputBool.equals("true")) {
			return "1";
		}else {
			return "0";
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		
		String output = hosObj.deleteHospital(paras.get("id").toString());
		
		response.getWriter().write(output);

	}
	
	// Convert request parameters to a Map 
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{ 
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
								scanner.useDelimiter("\\A").next() : "";
			scanner.close(); 
	 
			String[] params = queryString.split("&");
			for (String param : params)   { 
					String[] p = param.split("=");
					map.put(p[0], p[1]);
			} 
		}
		catch (Exception e)
		{
			
		}  return map;
	} 

}
