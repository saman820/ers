package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ReimbDbConnection {

	private final String URL = "jdbc:postgresql://rev-can-training.cevrrymkkbmb.us-east-2.rds.amazonaws.com:5432/project1db";
	private final String USERNAME="project1user";
	private final String PASSWORD= "passgyvyutkykfyg/uhoihouiyuftdryxgfchgygut7ir68dtgjkgut7i6tfujkug/iy.w0rd";
	
	public Connection getDbConnection() throws SQLException{
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		
	}
}
