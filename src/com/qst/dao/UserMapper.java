package com.qst.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qst.entity.User;

public interface UserMapper {

	void userRegister(User user);

	User userLogin(User user);

	void modifyBalance(User user);

	void investMoney(int id, double balan);

	void modifyPersonMsg(User user);

	List<User> getUserAll();

	User getUserById(int id);

	void addUser(User user);

	void modifyPassword(@Param(value="id")int id, @Param(value="pwd")String  pwd);

	void deleteUser(int id);

	void modifyUserStatus(int id,int status);

	List<User> getUserByStatus(int status);

	List<User> getOrdinaryUserAll();
	
}
