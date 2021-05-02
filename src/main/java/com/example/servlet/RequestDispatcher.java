package com.example.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.controller.Controller;

public class RequestDispatcher {
	Controller controller ;
	public RequestDispatcher() {
		controller = new Controller();
	}
	public String process(HttpServletRequest req) throws ServletException, IOException {
		String uri = req.getRequestURI();
		switch(uri) {
		/*
		 * case "/ers" : return "html/index.html"; case "/ers/" : return
		 * "html/index.html";
		 */
		case "/ers/home.ers" :
			return controller.login(req);
		case "/ers/back-home.ers" :
			return controller.homer(req);
		case "/ers/logout.ers":
			return controller.logOut(req);
		case "/ers/create-user.ers" :
			return controller.createUser(req);
		case "/ers/newUser.ers" :
			return controller.newUser(req);
		case "/ers/create-reimb.ers" :
			return controller.createReimb(req);
		case "/ers/add-reimb.ers" :
			return controller.addReimb(req);
		case "/ers/view-user.ers" :
			return controller.viewUser(req);
		case "/ers/view-users.ers" :
			return controller.viewUsers(req);
		case "/ers/view-reimb.ers" :
			return controller.viewReimb(req);
		case "/ers/view-reimbs.ers" :
			return controller.viewReimbs(req);
		case "/ers/update-user.ers" :
			return controller.updateUser(req);
		case "/ers/update-reimb.ers" :
			return controller.updateReimb(req);
		case "/ers/fupdate-reimb.ers" :
			return controller.fUpdateReimb(req);
		case "/ers/edit-reimb.ers" :
			return controller.editReimb(req);
		case "/ers/fEdit-reimb.ers" :
			return controller.fEditReimb(req);
		case "/ers/delete-reimb.ers" :
			return controller.deleteReimb(req);
		/*
		 * case "/ers/serv2.change" : return controller.serv2(req, res);
		 */
		default:
			return "/html/index.html";
		}
		
		/* return controller.login(req); */
	}
}
