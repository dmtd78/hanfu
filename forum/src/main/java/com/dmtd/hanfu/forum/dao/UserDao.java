package com.dmtd.hanfu.forum.dao;

import java.util.Date;

import com.dmtd.hanfu.forum.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "userDao")
public interface UserDao {

	public int addUser(@Param("username") String username,
			@Param("password") String password, @Param("headimg") String headimg);

	public User findUser(@Param("username") String username,
						 @Param("password") String password);

	public User getUserByID(@Param("uid") Integer uid);

	public int updateHeadImg(@Param("headimg") String headImgName,
			@Param("uid") Integer uid);

	public int updateUserInfo(@Param("uid") Integer uid,
			@Param("password") String password);

	public void updateUserLoginTime(@Param("uid")Integer uid, @Param("date")Date date);

}
