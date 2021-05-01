package com.example.dao;

import java.util.List;

import com.example.model.ErsReimbursement;

public interface ReimbDao {
	public List<ErsReimbursement> getAll();
	public ErsReimbursement getOne(int id);
	public void update(int id, ErsReimbursement reimb);
	public void insert(ErsReimbursement reimb);
	public void delete(int id);
}
