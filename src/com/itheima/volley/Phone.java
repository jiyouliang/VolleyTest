package com.itheima.volley;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Phone extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		JSONArray arr = new JSONArray();
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		JSONObject json3 = new JSONObject();
		
		try {
			json1.put("name", "iPhone SE").put("price", 3588);
			json2.put("name", "OPO R9 SE").put("price", 2799);
			json3.put("name", "HuaWei P8").put("price", 2499);
			
			arr.put(json1);
			arr.put(json2);
			arr.put(json3);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		out.print(arr.toString());
		out.flush();
		out.close();
	}


}
