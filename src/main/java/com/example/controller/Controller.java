package com.example.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.example.dao.ReimbDaoImpl;
import com.example.dao.ReimbUserDaoImpl;
import com.example.model.ErsReimbursement;
import com.example.model.ErsUser;

public class Controller {
	ReimbUserDaoImpl reUser =new ReimbUserDaoImpl();
	ReimbDaoImpl rei =new ReimbDaoImpl();
	public Controller(ReimbUserDaoImpl reUser, ReimbDaoImpl rei) {
		this.reUser = reUser;
		this.rei =rei;
	}
	public Controller() {
		// TODO Auto-generated constructor stub
	}
	public String login(HttpServletRequest req) {
		if(reUser.validateLogin(req.getParameter("uName"), req.getParameter("pWord"))) {
			req.getSession().setAttribute("userName",req.getParameter("uName"));
			ErsUser cUser= reUser.getOneByUserName(req.getParameter("uName"));
			req.getSession().setAttribute("currentUser", cUser);
			
			if(reUser.validateFinanceByUserName(req.getParameter("uName"))) {
				return"html/fhome.html";
			}		
			return "html/home.html";
		}
		return "html/index.html";
	}
	public String homer(HttpServletRequest req) {
		String sesUser =null;
		if(req.getSession().getAttribute("currentUser")!=null) {
			sesUser = ((ErsUser) req.getSession().getAttribute("currentUser")).getUserName();
		}
		if(sesUser!=null) {			
			if(reUser.validateFinanceByUserName(sesUser)) {
				return"html/fhome.html";
			}		
			return "html/home.html";
		}
		return "html/index.html";
	}
	public String logOut(HttpServletRequest req) {
		req.getSession().setAttribute("currentUser", null);
		req.getSession().setAttribute("userName", null);
		req.getSession().invalidate(); 
		return "html/index.html";
	}
	public String createUser(HttpServletRequest req) {
		return "html/create-user.html";
	}
	public String newUser(HttpServletRequest req) {
		String uName= req.getParameter("username");
		String pWord= req.getParameter("password");
		String fname= req.getParameter("fname");
		String lname= req.getParameter("lname");
		String email= req.getParameter("email");
		int typeId= Integer.parseInt(req.getParameter("role"));
		ErsUser newUser = new ErsUser(uName,pWord,fname,lname,email,typeId);
		try {
			new ReimbUserDaoImpl().insert(newUser);
			req.getSession().setAttribute("message", "User successfully added");
			req.getSession().setAttribute("messageClass", "alert-success");
			return "html/index.html";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.getSession().setAttribute("message", "An error happened");
			req.getSession().setAttribute("messageClass", "alert-danger");
		}
		return "html/index.html";
	}
	public String createReimb(HttpServletRequest req) {
		if(req.getSession().getAttribute("currentUser")!=null)
			return "html/create-reimb.html";
		return "html/index.html";
	}
	public String addReimb(HttpServletRequest req) throws IOException, ServletException {
		if(req.getSession().getAttribute("currentUser")!=null){
			
			Part filePart = req.getPart("file");
//			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			InputStream fileContent = filePart.getInputStream();
			byte[] image = IOUtils.toByteArray(fileContent);			
			double amount = Double.parseDouble(req.getParameter("amount"));
			String currency = req.getParameter("currency");
			int resolverId= new ReimbUserDaoImpl().getOneByUserName(req.getParameter("resolver")).getId();
			int typeId = Integer.parseInt(req.getParameter("type"));
			String description = req.getParameter("description");
			int authorId = ((ErsUser) req.getSession().getAttribute("currentUser")).getId();
			ErsReimbursement newReimb = new ErsReimbursement(amount,currency, description,image,authorId,resolverId,typeId);
			try {
				new ReimbDaoImpl().insert(newReimb);
				req.getSession().setAttribute("message", "Your ticket was successfully created");
				req.getSession().setAttribute("messageClass", "alert-success");
				return "html/home.html";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				req.getSession().setAttribute("message", "An error happened");
				req.getSession().setAttribute("messageClass", "alert-danger");
			}
		}	
		return "html/index.html";
	}
	public String viewUser(HttpServletRequest req) {
		if(req.getSession().getAttribute("currentUser")!=null)
			return "html/view-user.html";
		return "html/index.html";
	}
	public String viewUsers(HttpServletRequest req) {
		if(req.getSession().getAttribute("currentUser")!=null)
			return "html/view-users.html";
		return "html/index.html";
	}
	public String viewReimb(HttpServletRequest req) {
		if(req.getSession().getAttribute("currentUser")!=null)
			return "html/view-reimb.html";
		return "html/index.html";
	}
	public String viewReimbs(HttpServletRequest req) {
		if(req.getSession().getAttribute("currentUser")!=null) 
			return "html/view-reimbs.html";
		return "html/index.html";
	}
	public String updateUser(HttpServletRequest req) {
		if(req.getSession().getAttribute("currentUser")!=null)
			return "html/update-user.html";
		return "html/index.html";
	}
	public String updateReimb(HttpServletRequest req) {
		if(req.getSession().getAttribute("currentUser")!=null) {
			ErsReimbursement cReimb = new ReimbDaoImpl().getOne(Integer.parseInt(req.getParameter("currentReimbId")));
			cReimb.setAuthorUserName(new ReimbUserDaoImpl().getOneByUserId(cReimb.getAuthorId()).getUserName());
			cReimb.setResolverUserName(new ReimbUserDaoImpl().getOneByUserId(cReimb.getResolverId()).getUserName());
			req.getSession().setAttribute("currentReimb", cReimb);
			return "html/update-reimb.html";
			
		}
		return "html/index.html";
	}
	public String fUpdateReimb(HttpServletRequest req) {
		if(req.getSession().getAttribute("currentUser")!=null) {
			ErsReimbursement cReimb = new ReimbDaoImpl().getOne(Integer.parseInt(req.getParameter("currentReimbId")));
			cReimb.setAuthorUserName(new ReimbUserDaoImpl().getOneByUserId(cReimb.getAuthorId()).getUserName());
			cReimb.setResolverUserName(new ReimbUserDaoImpl().getOneByUserId(cReimb.getResolverId()).getUserName());
			req.getSession().setAttribute("currentReimb", cReimb);
			System.out.println("hi");
			return "html/fupdate-reimb.html";
		}
		return "html/index.html";
	}
	public String editReimb(HttpServletRequest req) throws IOException, ServletException {
		if(req.getSession().getAttribute("currentUser")!=null) {
			Part filePart = req.getPart("file");
//			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			InputStream fileContent = filePart.getInputStream();
			byte[] image = IOUtils.toByteArray(fileContent);
			
			double amount = Double.parseDouble(req.getParameter("amount"));
			String currency = req.getParameter("currency");
			int resolverId= new ReimbUserDaoImpl().getOneByUserName(req.getParameter("resolver")).getId();
			int typeId = Integer.parseInt(req.getParameter("type"));
			String description = req.getParameter("description");
			ErsReimbursement cReimb = new ReimbDaoImpl().getOne(((ErsReimbursement) req.getSession().getAttribute("currentReimb")).getId());
			ErsReimbursement newReimb = new ErsReimbursement(amount,currency, description,image,resolverId,typeId);
			try {
				new ReimbDaoImpl().update(cReimb.getId(), newReimb);
				req.getSession().setAttribute("message", "Ticket was successfully updated");
				req.getSession().setAttribute("messageClass", "alert-success");
				return "html/home.html";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				req.getSession().setAttribute("message", "An error happened");
				req.getSession().setAttribute("messageClass", "alert-danger");
			}
		}
		return "html/index.html";
	}
	public String fEditReimb(HttpServletRequest req) {
		if(req.getSession().getAttribute("currentUser")!=null) {
			int status = Integer.parseInt(req.getParameter("status"));
			ErsReimbursement cReimb = new ReimbDaoImpl().getOne(((ErsReimbursement) req.getSession().getAttribute("currentReimb")).getId());
			try {
				new ReimbDaoImpl().setStatus(cReimb.getId(), status);
				req.getSession().setAttribute("message", "Ticket was successfully updated");
				req.getSession().setAttribute("messageClass", "alert-success");
				return "html/fhome.html";
			} catch (Exception e) {
				e.printStackTrace();
				req.getSession().setAttribute("message", "An error happened");
				req.getSession().setAttribute("messageClass", "alert-danger");
			}
		}
		return "html/index.html";
	}
	public String deleteReimb(HttpServletRequest req) {
		if(req.getSession().getAttribute("currentUser")!=null) {
			ErsReimbursement cReimb = new ReimbDaoImpl().getOne(((ErsReimbursement) req.getSession().getAttribute("currentReimb")).getId());
			try {
				new ReimbDaoImpl().delete(cReimb.getId());
				req.getSession().setAttribute("message", "Ticket was successfully deleted");
				req.getSession().setAttribute("messageClass", "alert-success");
				return "html/home.html";
			} catch (Exception e) {
				e.printStackTrace();
				req.getSession().setAttribute("message", "An error happened");
				req.getSession().setAttribute("messageClass", "alert-danger");
			}
		}
		return "html/index.html";
	}

	
	/*
	 * public String serv2(HttpServletRequest req, HttpServletResponse res){ try {
	 * ErsUser user = new ReimbUserDaoImpl().getOne((String)
	 * req.getSession().getAttribute("userName")); System.out.println(user);
	 * res.getWriter().write(new ObjectMapper().writeValueAsString(user));
	 * System.out.println(res.toString()); return ""; } catch
	 * (JsonProcessingException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } return "success"; }
	 */
	
	
	
	/*
	 * public void getSessUserName(HttpServletRequest req, HttpServletResponse res)
	 * throws IOException{ String name =
	 * (String)req.getSession().getAttribute("userName");
	 * res.getWriter().write(name); }
	 */
}
