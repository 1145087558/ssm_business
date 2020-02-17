package com.qst.service;

import java.util.List;

import com.qst.entity.User;
import com.qst.entity.UserAddress;

public interface UserService {
	
	void userRegister(User user);

	User userLogin(User user);

	void modifyBalance(User user);

	void modifyPersonMsg(User user);
	
	List<User> getUserAll();

	User getUserById(int userId);

	void addUser(User user);
	
	void addUserAddress(UserAddress userAddress);

	void modifyPassword(int id, String newpassword);

	void deleteUser(int id);

	void modifyUserStatus(int id,int status);

	List<User> getUserByStatus(int status);
	
	List<UserAddress> mapUser();
	
	List<User> getOrdinaryUserAll();

	boolean checkEmail(String email);

	boolean checkPhone(String phone);
	
	boolean getPasswordByid(String pwd,int id);
	List<UserAddress> getUserAddress(int userId);

	void modifyAddress(UserAddress userAddress);

	void deleteAddress(int id);

	void modifyUser(User user);
	
	/*void investMoney(int id, double balan);*/

}
