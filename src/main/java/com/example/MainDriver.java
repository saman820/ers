package com.example;

import com.example.dao.ReimbDaoImpl;
import com.example.dao.ReimbUserDaoImpl;
import com.example.model.ErsReimbursement;

public class MainDriver {
	public static void main(String[] args) {
		ReimbDaoImpl reimb = new ReimbDaoImpl();
		ReimbUserDaoImpl reimbUser = new ReimbUserDaoImpl();
		
//		reimb.insert(new ErsReimbursement(160.6,"CAD",1001,1001,2));
//		System.out.println(reimb.getAll());
//		System.out.println(reimb.getAllByUser(1001));
//		System.out.println(reimb.getAllByResolver(1000));
//		System.out.println(reimb.getOne(10004));
//		reimb.update(10001,new ErsReimbursement(400.69,"CAD","lunch in vanc",1000,1));
//		reimb.setStatus(10002,4);
//		reimb.delete(10001);
		
		System.out.println(reimbUser.getAllFManagers());
//		System.out.println(reimbUser.getOneByUserName("user1"));
//		System.out.println(reimbUser.getOneByUserId(1001));
//		System.out.println(reimbUser.validateFinanceByUserName("user0"));
//		reimbUser.insert(new ErsUser("user2", "password2", "s", "k", "saman_820@yahoo.com",6));
//		reimbUser.update(1002, new ErsUser("user2", "password2", "s", "k", "saman_820@yahoo.com",4));
//		reimbUser.delete(1002);
//		System.out.println(reimbUser.getOne("user1"));
//		System.out.println(reimbUser.validateLogin("user1", "password1"));
	}
}
