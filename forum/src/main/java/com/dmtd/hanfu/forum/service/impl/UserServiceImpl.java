package com.dmtd.hanfu.forum.service.impl;

import java.util.Date;

import com.dmtd.hanfu.forum.dao.UserDao;
import com.dmtd.hanfu.forum.entity.User;
import com.dmtd.hanfu.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public int addUser(String username, String password, String headimg) {
		return userDao.addUser(username, password, headimg);
	}

	@Override
	public User findUser(String username, String password) {
		return userDao.findUser(username, password);
	}

	@Override
	public User getUserByID(Integer uid) {
		return userDao.getUserByID(uid);
	}

	@Override
	public int updateHeadImg(String headImgName, Integer uid) {
		return userDao.updateHeadImg(headImgName, uid);
	}

	@Override
	public int updateUserInfo(Integer uid, String password) {
		return userDao.updateUserInfo(uid, password);
	}

	@Override
	public void updateUserLoginTime(Integer uid, Date date) {
		userDao.updateUserLoginTime(uid,date);
		
	}

}
