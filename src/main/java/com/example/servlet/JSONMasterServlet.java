package com.example.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.ReimbDaoImpl;
import com.example.dao.ReimbUserDaoImpl;
import com.example.model.ErsReimbursement;
import com.example.model.ErsUser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONMasterServlet extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
//		if(reimbUser.validateLogin(req.getParameter("uName"), req.getParameter("pWord"))) {
//			req.getSession().setAttribute("userName",req.getParameter("uName"));
//			return "html/home.html";
//		}
//		return "html/index.html";

		/*
		 * System.out.println("in serv2"); ErsUser user = new
		 * ReimbUserDaoImpl().getOne((String)
		 * req.getSession().getAttribute("userName")); System.out.println(user);
		 * res.getWriter().write(new ObjectMapper().writeValueAsString(user));
		 */

		//or i can write user in seession
//		req.getSession().setAttribute("currentUser", user);
//		ErsUser user2 = (ErsUser)req.getSession().getAttribute("currentUser");
//		res.getWriter().write(new ObjectMapper().writeValueAsString(user2));
		
		String uri = req.getRequestURI();
		switch(uri) {
		case "/ers/serv2/getUser" :
			System.out.println("in getUser");
			ErsUser user = new ReimbUserDaoImpl().getOneByUserName((String) req.getSession().getAttribute("userName"));
			res.getWriter().write(new ObjectMapper().writeValueAsString(user));
			break;
		case "/ers/serv2/getReimbs":
			ErsUser cUser= (ErsUser)req.getSession().getAttribute("currentUser");
			List<ErsReimbursement> reimbs = new ReimbDaoImpl().getAllByUser(cUser.getId());
			for(ErsReimbursement reimb:reimbs) {
				String str = new ReimbUserDaoImpl().getOneByUserId(reimb.getResolverId()).getUserName();
				reimb.setResolverUserName(str);
			}
			res.getWriter().write(new ObjectMapper().writeValueAsString(reimbs));
			break;
		case "/ers/serv2/getFReimbs":
			ErsUser cFUser= (ErsUser)req.getSession().getAttribute("currentUser");
			List<ErsReimbursement> fReimbs = new ReimbDaoImpl().getAllByResolver(cFUser.getId());
			for(ErsReimbursement reimb:fReimbs) {
				String str = new ReimbUserDaoImpl().getOneByUserId(reimb.getResolverId()).getUserName();
				reimb.setResolverUserName(str);
			}
			res.getWriter().write(new ObjectMapper().writeValueAsString(fReimbs));
			break;
		case "/ers/serv2/getManagerNames":	
			List<String> mgs = new ReimbUserDaoImpl().getAllFManagersUsers();
			res.getWriter().write(new ObjectMapper().writeValueAsString(mgs));
			break;
		case "/ers/serv2/getAuthorNames":	
			List<String> aus = new ReimbUserDaoImpl().getAllUsers();
			res.getWriter().write(new ObjectMapper().writeValueAsString(aus));
			break;
		case "/ers/serv2/getCurrentreimb":
			ErsReimbursement cReimb = (ErsReimbursement) req.getSession().getAttribute("currentReimb");
			res.getWriter().write(new ObjectMapper().writeValueAsString(cReimb));
		}
		
			
		
		
	}
}
