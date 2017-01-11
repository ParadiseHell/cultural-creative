package com.chengtao.dao;

import com.chengtao.entity.CompanyUser;

public interface CompanyUserDao {
	CompanyUser signUp(String name,String password);
	CompanyUser login(String name,String password);
	boolean uploadAvatar(int id,String avatarPath);
	CompanyUser modify(CompanyUser newUser);
}
