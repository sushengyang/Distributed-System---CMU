package com.GAE.project;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Project4Task2GAEServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		processRequest(req,resp);
		
		
	}
	  
	private void processRequest(HttpServletRequest req,HttpServletResponse res){  
		
		String price = req.getParameter("price");
		String down = req.getParameter("downpayment");
		res.setHeader("Content-Type", "text/xml;");
		PaymentModel p = new PaymentModel();
		p.doSearch(price, down);
		String output = p.getXML();
		
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		out.write(output);
		out.flush();
		
          
	} 
}
