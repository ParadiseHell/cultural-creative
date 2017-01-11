package com.chengtao.factory;

import com.chengtao.dao.CompanyUserDao;
import com.chengtao.dao.DemandDao;
import com.chengtao.dao.ExhibitonDao;
import com.chengtao.dao.NewsDao;
import com.chengtao.dao.PersonUserDao;
import com.chengtao.dao.SupplyDao;
import com.chengtao.impl.CompanyImpl;
import com.chengtao.impl.DemandImpl;
import com.chengtao.impl.ExhibitionImpl;
import com.chengtao.impl.NewsImpl;
import com.chengtao.impl.PersonUserImpl;
import com.chengtao.impl.SupplyImpl;

public class DaoFactory {
	public static PersonUserDao getPersonUserDao() {
		return new PersonUserImpl();
	}
	public static CompanyUserDao getCompanyUserDao() {
		return new CompanyImpl();
	}
	public static NewsDao getNewsDao() {
		return new NewsImpl();
	}
	public static ExhibitonDao getExhibitonDao() {
		return new ExhibitionImpl();
	}
	public static SupplyDao getSupplyDao() {
		return new SupplyImpl();
	}
	public static DemandDao getDemandDao() {
		return new DemandImpl();
	}
}	