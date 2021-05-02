package com.example.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
public class MasterServlet extends HttpServlet{
	RequestDispatcher reqDis;
	public MasterServlet() {
		reqDis= new RequestDispatcher();
	}
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws
	IOException, ServletException{ 
	res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
//	res.setHeader("Cache-Control", "must-revalidate"); 
    res.setHeader("Pragma", "no-cache");
    res.setHeader("Expires", "DateTimeAtCompleted"); 
	req.getRequestDispatcher(reqDis.process(req)).forward(req,res); 
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
//		res.setHeader("Cache-Control", "must-revalidate"); 
	    res.setHeader("Pragma", "no-cache");
	    res.setHeader("Expires", "DateTimeAtCompleted");
		req.getRequestDispatcher(reqDis.process(req)).forward(req,res);
	}
}
