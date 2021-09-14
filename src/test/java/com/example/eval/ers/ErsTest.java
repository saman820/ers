package com.example.eval.ers;
//  "***REMOVED***" sxdcfgvhljbh;jknbycfxdurdutcfiygvujbh;kj
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.controller.Controller;
import com.example.dao.ReimbDaoImpl;
import com.example.dao.ReimbUserDaoImpl;
import com.example.model.ErsReimbursement;
import com.example.model.ErsUser;

public class ErsTest {
	
	private static final Throwable IllegalArgumentException = null;
	@SuppressWarnings("deprecation")
	@Mock
	HttpServletRequest req = mock (HttpServletRequest.class);
	HttpSession ses = mock (HttpSession.class);
	ReimbUserDaoImpl reiUser = mock(ReimbUserDaoImpl.class);
	ReimbDaoImpl re = mock(ReimbDaoImpl.class);
//	Integer integ = mock(Integer.class);
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ErsUser user= new ErsUser("user0","pass0","f0","l0","user0@.com",4);
//		reimb1 = new ErsReimbursement(500,"CAD",10018,10019,2);
//		reimb2 = new ErsReimbursement(1000,500,"CAD","a","b",10018,10019,2,2);
		when(req.getParameter("uName")).thenReturn("user0");
		when(req.getParameter("pWord")).thenReturn("pass0");
		when(req.getParameter("username")).thenReturn("user0");
		when(req.getParameter("password")).thenReturn("pass0");
		when(req.getParameter("fname")).thenReturn("f0");
		when(req.getParameter("lname")).thenReturn("l0");
		when(req.getParameter("email")).thenReturn("user0@.com");
		when(reiUser.getOneByUserName("user0")).thenReturn(user);
//		when(Integer.parseInt(anyString())).thenReturn(4);
//		doNothing().when(reiUser).insert(user);
		
		when(reiUser.validateLogin("user0", "pass0")).thenReturn(true);
		when(req.getSession()).thenReturn(ses);
		doNothing().when(ses).setAttribute(anyString(),anyString());
		
	}
	@Test
	void viewreimbSuccess() {
		when(ses.getAttribute(anyString())).thenReturn("not null");
		assertEquals("html/create-reimb.html",new Controller(reiUser,re).createReimb(req));
	}
	@Test
	void viewreimbFailure() {
		when(ses.getAttribute(anyString())).thenReturn(null);
		assertEquals("html/index.html",new Controller(reiUser,re).createReimb(req));
	}
	
	@Test 
	void loginFinanceSuccess() {
		when(reiUser.validateFinanceByUserName(anyString())).thenReturn(true);
		assertEquals("html/fhome.html",new Controller(reiUser,re).login(req));
	}

	@Test 
	void loginDBSuccess() {
		assertEquals("html/home.html",new Controller(reiUser,re).login(req));
	}
	
	@Test
	void logOutSuccess() {
		assertEquals("html/index.html", new Controller(reiUser,re).logOut(req));
	}
	
	@Test
	void createUserSuccess() {
		assertEquals("html/create-user.html",new Controller(reiUser,re).createUser(req));
	}
	
	 @Test 
		 void voidModelSetUserNameSuccess() { 
		 ErsUser user1 = mock(ErsUser.class); 
		 doNothing().when(user1).setUserName(anyString());	 
		 when(user1.getUserName()).thenReturn("saman"); 
		 user1.setUserName("Saman");
		 assertEquals("saman",user1.getUserName()); 
	}
	 
	 @Test 
	 void voidModelSetUserNameThrow() { 
		 ErsUser user1 = mock(ErsUser.class); 
		 doThrow(IllegalArgumentException.class).when(user1).setUserName(null);
		 when(user1.getUserName()).thenReturn("saman"); 
	 	 assertThrows(IllegalArgumentException.class,()->user1.setUserName(null));
	 }
	 
	 @Test
		public void JUnitValidateUserNameSuccess() {
			assertTrue(new ReimbUserDaoImpl().validateFinanceByUserName("user0"));
	}
	 
		@Test
		void viewUserSuccess() {
			when(ses.getAttribute(anyString())).thenReturn("not null");
			assertEquals("html/view-user.html",new Controller(reiUser,re).viewUser(req));
		}
		@Test
		void viewUserFailure() {
			when(ses.getAttribute(anyString())).thenReturn(null);
			assertEquals("html/index.html",new Controller(reiUser,re).viewUser(req));
		}
		
	 
	
}
 
