package com.chengtao.dao;

import com.chengtao.entity.PersonUser;

public interface PersonUserDao {
	PersonUser signUp(String name,String password);
	PersonUser login(String name,String password);
	boolean uploadAvatar(int id,String avatarPath);
	PersonUser modify(PersonUser newUser);
}