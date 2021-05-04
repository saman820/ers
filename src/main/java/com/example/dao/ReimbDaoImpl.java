package com.example.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.example.Logging;
import com.example.model.ErsReimbursement;

public class ReimbDaoImpl implements ReimbDao{
	private ReimbDbConnection reimbCon;
	public ReimbDaoImpl() {
		reimbCon = new ReimbDbConnection();
	}

	@Override
	public List<ErsReimbursement> getAll() {
		List<ErsReimbursement> reimbList = new ArrayList<>();
		try(Connection con = reimbCon.getDbConnection()){
			String sql = "select * from ers_reimbursement";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			new Logging().log.debug("in getting All Reimbursements");
			while(rs.next()) {
				reimbList.add(new ErsReimbursement(rs.getInt(1), rs.getDouble(2),rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getBytes(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11) ));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			new Logging().log.error(e.getMessage());
		}
		return reimbList;
	}
	public List<ErsReimbursement> getAllByUser(int authorId) {
		List<ErsReimbursement> reimbList = new ArrayList<>();
		try(Connection con = reimbCon.getDbConnection()){
			String sql = "select * from ers_reimbursement where reimb_author=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, authorId);
			ResultSet rs = ps.executeQuery();
			new Logging().log.debug("in getting all reeimbursements by user authorID");
			while(rs.next()) {
				ErsReimbursement rei=new ErsReimbursement(rs.getInt(1), rs.getDouble(2),rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getBytes(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11) );
				rei.setResolverUserName(new ReimbUserDaoImpl().getOneByUserId(rei.getResolverId()).getUserName());
				rei.setAuthorUserName(new ReimbUserDaoImpl().getOneByUserId(rei.getAuthorId()).getUserName());
				reimbList.add(rei);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			new Logging().log.error(e.getMessage());
		}
		return reimbList;
	}
	public List<ErsReimbursement> getAllByResolver(int resolverId) {
		List<ErsReimbursement> reimbList = new ArrayList<>();
		try(Connection con = reimbCon.getDbConnection()){
			String sql = "select * from ers_reimbursement where reimb_resolver=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, resolverId);
			ResultSet rs = ps.executeQuery();
			new Logging().log.debug("in getting all reimbursements by resolver ID");
			while(rs.next()) {
				ErsReimbursement rei=new ErsReimbursement(rs.getInt(1), rs.getDouble(2),rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getBytes(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11) );
				rei.setResolverUserName(new ReimbUserDaoImpl().getOneByUserId(rei.getResolverId()).getUserName());
				rei.setAuthorUserName(new ReimbUserDaoImpl().getOneByUserId(rei.getAuthorId()).getUserName());
				reimbList.add(rei);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			new Logging().log.error(e.getMessage());
		}
		return reimbList;
	}

	@Override
	public ErsReimbursement getOne(int id) {
		ErsReimbursement reimb = null;
		try(Connection con = reimbCon.getDbConnection()){
			String sql = "select * from ers_reimbursement where reimb_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			new Logging().log.debug("in getting one reibmursement by id");
			while(rs.next()) {
				reimb=new ErsReimbursement(rs.getInt(1), rs.getDouble(2),rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getBytes(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11) );
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			new Logging().log.error(e.getMessage());
		}
		return reimb;
	}

	@Override
	public void update(int id, ErsReimbursement reimb) {
		try(Connection con= reimbCon.getDbConnection()){
			String sql= "UPDATE ers_reimbursement SET reimb_amount=?, reimb_currency=?, reimb_description=?, reimb_resolver=?, reimb_type_id=?, reimb_receipt=?  WHERE reimb_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, reimb.getAmount());
			ps.setString(2, reimb.getErsCurrency());
			ps.setString(3, reimb.getDescription());
			ps.setInt(4, reimb.getResolverId());
			ps.setInt(5, reimb.getTypeId());
			ps.setBytes(6, reimb.getReceipt());
			ps.setInt(7, id);
			ps.executeUpdate();
			new Logging().log.debug("in updating a reimbursemnt");
		}catch(SQLException e) {
			e.printStackTrace();
			new Logging().log.error(e.getMessage());
		}
	}
	
	public void setStatus(int reimbId, int statusId) {
		try(Connection con = reimbCon.getDbConnection()){
			String sql="";
			if(statusId==4 || statusId==3) {
				sql = "UPDATE ers_reimbursement SET reimb_resolved=current_timestamp, reimb_status_id=?  WHERE reimb_id=?";
			}else {
				sql = "UPDATE ers_reimbursement SET reimb_status_id=?  WHERE reimb_id=?";
			}
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, statusId);
			ps.setInt(2, reimbId);	
			ps.executeUpdate();
			new Logging().log.debug("in setting the status of a reimbursement");
		}catch(SQLException e) {
			e.printStackTrace();
			new Logging().log.error(e.getMessage());
		}
	}

	@Override
	public void insert(ErsReimbursement reimb) {
		try(Connection con = reimbCon.getDbConnection()){
			String sql = "{? = call create_reimbursement_with_img (?,?,?,?,?,?,?)}";
			CallableStatement cs =  con.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setDouble(2, reimb.getAmount());
			cs.setString(3, reimb.getErsCurrency());
			cs.setBytes(4, reimb.getReceipt());
			cs.setString(5, reimb.getDescription());
			cs.setInt(6, reimb.getAuthorId());
			cs.setInt(7, reimb.getResolverId());
			cs.setInt(8, reimb.getTypeId());
			cs.execute();
			new Logging().log.debug("creating a new ticket");
		}catch(SQLException e) {
			e.printStackTrace();
			new Logging().log.error(e.getMessage());
		}
	}
//	@Override
//	public void insert(ErsReimbursement reimb) {
//		try(Connection con = reimbCon.getDbConnection()){
//			String sql = "{? = call create_reimbursement_with_desc (?,?,?,?,?,?)}";
//			CallableStatement cs =  con.prepareCall(sql);
//			cs.registerOutParameter(1, Types.VARCHAR);
//			cs.setDouble(2, reimb.getAmount());
//			cs.setString(3, reimb.getErsCurrency());
//			cs.setString(4, reimb.getDescription());
//			cs.setInt(5, reimb.getAuthorId());
//			cs.setInt(6, reimb.getResolverId());
//			cs.setInt(7, reimb.getTypeId());
//			cs.execute();
//			new Logging().log.debug("in Dao");
//		}catch(SQLException e) {
//			e.printStackTrace();
//			new Logging().log.error(e.getMessage());
//		}
//	}

	@Override
	public void delete(int reimbId) {
		try(Connection con = reimbCon.getDbConnection()){
			String sql = "delete from ers_reimbursement where reimb_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, reimbId);
			ps.executeUpdate();		
			new Logging().log.debug("deleting a new ticket");
		}catch(SQLException e) {
			e.printStackTrace();
			new Logging().log.error(e.getMessage());
		}
	}

}
