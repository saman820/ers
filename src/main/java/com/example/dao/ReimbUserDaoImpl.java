package com.example.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.example.model.ErsUser;

public class ReimbUserDaoImpl implements ReimbUserDao{
	private ReimbDbConnection reimbCon;
	public ReimbUserDaoImpl() {
		reimbCon = new ReimbDbConnection();
	}
	@Override
	public List<ErsUser> getAllFManagers() {
		try(Connection con = reimbCon.getDbConnection()){
			String sql = "select * from ers_users where user_role_id=0";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List <ErsUser> users = new ArrayList<>();
			while(rs.next()) {
				users.add(new ErsUser(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
			return users;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<String> getAllFManagersUsers() {
		try(Connection con = reimbCon.getDbConnection()){
			String sql = "select ers_username from ers_users where user_role_id=0";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List <String> managers = new ArrayList<>();
			while(rs.next()) {
				managers.add(rs.getString(1));
			}
			return managers;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<String> getAllUsers() {
		try(Connection con = reimbCon.getDbConnection()){
			String sql = "select ers_username from ers_users";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List <String> managers = new ArrayList<>();
			while(rs.next()) {
				managers.add(rs.getString(1));
			}
			return managers;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ErsUser getOneByUserName(String uName) {
		try(Connection con = reimbCon.getDbConnection()){
			String sql = "select * from ers_users where ers_username=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,uName);
			ResultSet rs = ps.executeQuery();
			ErsUser user = null;
			while(rs.next()) {
				user = new ErsUser(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
			}
			return user;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ErsUser getOneByUserId(int userId) {
		try(Connection con = reimbCon.getDbConnection()){
			String sql = "select * from ers_users where ers_users_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,userId);
			ResultSet rs = ps.executeQuery();
			ErsUser user = null;
			while(rs.next()) {
				user = new ErsUser(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
			}
			return user;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean validateLogin(String username, String password) {
		try(Connection con = reimbCon.getDbConnection()){
			String sql = "select * from ers_users where ers_username=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(password.equals(rs.getString(3)))
					return true;
			}
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean validateFinanceByUserName(String username) {
		try(Connection con = reimbCon.getDbConnection()){
			String sql = "select * from ers_users where ers_username=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if((rs.getInt(7)==0))
					return true;
			}
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void insert(ErsUser user) {
		try(Connection con = reimbCon.getDbConnection()){
			String sql = "{? = call create_user (?,?,?,?,?,?)}";
			CallableStatement cs =  con.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2,user.getUserName());
			cs.setString(3,user.getPassWord());
			cs.setString(4,user.getfName());
			cs.setString(5,user.getlName());
			cs.setString(6,user.getEmail());
			cs.setInt(7,user.getRoleId());
			cs.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(int id, ErsUser user) {
		try(Connection con= reimbCon.getDbConnection()){
			String sql= "UPDATE ers_users SET ers_username=?, ers_password=?, user_first_name=?, user_last_name=?, user_email=?, user_role_id=?  WHERE ers_users_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassWord());
			ps.setString(3, user.getfName());
			ps.setString(4, user.getlName());
			ps.setString(5, user.getEmail());
			ps.setInt(6, user.getRoleId());
			ps.setInt(7, id);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		try(Connection con = reimbCon.getDbConnection()){
			String sql = "delete from ers_users where ers_users_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	

}
