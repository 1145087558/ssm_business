package com.qst.service;

import java.util.List;

import com.qst.entity.User;

public interface UserService {

	void userRegister(User user);

	User userLogin(User user);

	void modifyBalance(User user);

	void modifyPersonMsg(User user);
	
	List<User> getUserAll();

	User getUserById(int userId);

	void addUser(User user);

	void modifyPassword(int id, String newpassword);

	void deleteUser(int id);

	void modifyUserStatus(int id,int status);

	List<User> getUserByStatus(int status);

	List<User> getOrdinaryUserAll();

	boolean checkEmail(String email);

	boolean checkPhone(String phone);

	/*void investMoney(int id, double balan);*/

}
