package com.qst.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qst.dao.UserMapper;
import com.qst.entity.User;
import com.qst.entity.UserAddress;
import com.qst.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	@Override
	public void userRegister(User user) {
		userMapper.userRegister(user);
		
	}

	@Override
	public User userLogin(User user) {
		
		return user=userMapper.userLogin(user);
		
	}

	@Override
	public void modifyBalance(User user) {
		userMapper.modifyBalance(user);
		
	}

	@Override
	public void modifyPersonMsg(User user) {
		userMapper.modifyPersonMsg(user);
		
	}

	@Override
	public List<User> getUserAll() {
		
		return userMapper.getUserAll();
	}

	@Override
	public User getUserById(int userId) {
		
		return userMapper.getUserById(userId);
	}

	@Override
	public void addUser(User user) {
		userMapper.addUser(user);
		
	}

	@Override
	public void modifyPassword(int id, String newpassword) {
		userMapper.modifyPassword(id, newpassword);
		
	}

	@Override
	public void deleteUser(int id) {
		userMapper.deleteUser(id);
		
	}

	@Override
	public void modifyUserStatus(int id,int status) {
		
		userMapper.modifyUserStatus(id,status);
	}

	@Override
	public List<User> getUserByStatus(int status) {
		
		return userMapper.getUserByStatus(status);
	}

	@Override
	public List<User> getOrdinaryUserAll() {
		
		return userMapper.getOrdinaryUserAll();
	}

	@Override
	public boolean checkEmail(String email) {
		if(userMapper.getUserEmail(email)!=null){
			return true;
		}
		return false;
	}

	@Override
	public boolean checkPhone(String phone) {
		if(userMapper.getUserTel(phone)!=null){
			return true;
		}
		return false;
	}

	@Override
	public void addUserAddress(UserAddress userAddress) {
		userMapper.addUserAddress(userAddress);
	}

	@Override
	public List<UserAddress> getUserAddress(int userId) {
		
		return userMapper.getUserAddress(userId);
	}

	@Override
	public void modifyAddress(UserAddress userAddress) {
		userMapper.modifyAddress(userAddress);
		
	}

	@Override
	public void deleteAddress(int id) {
		
		userMapper.deleteAddress(id);
	}

	@Override
	public List<UserAddress> mapUser() {
		// TODO Auto-generated method stub
		return userMapper.mapUser();
	}

	//@Override
	/*public void investMoney(int id, double balan) {
		userMapper.investMoney(id,balan);
		
	}*/

}
