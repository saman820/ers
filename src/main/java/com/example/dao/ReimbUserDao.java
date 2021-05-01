package com.example.dao;

import java.util.List;

import com.example.model.ErsUser;

public interface ReimbUserDao {
	public List<ErsUser> getAllFManagers();
	public ErsUser getOneByUserName(String username);
	public void update(int id, ErsUser user);
	public void delete(int id);
	
}
