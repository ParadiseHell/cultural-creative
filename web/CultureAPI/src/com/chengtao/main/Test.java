package com.chengtao.main;

import com.chengtao.dao.NewsDao;
import com.chengtao.dao.PersonUserDao;
import com.chengtao.entity.PersonUser;
import com.chengtao.factory.DaoFactory;

public class Test {
	public static void main(String[] args) {
		/*PersonUserDao personUserDao = DaoFactory.getPersonUserDao();
		PersonUser user = null;
		user = personUserDao.signUp("a", "123");
		System.out.println(user.toString());
		user = personUserDao.login("a", "123");
		System.out.println(user.toString());*/
		NewsDao dao = DaoFactory.getNewsDao();
		dao.getAllNews(2);
	}
}
