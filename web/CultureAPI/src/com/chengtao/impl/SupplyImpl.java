package com.chengtao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import com.chengtao.dao.SupplyDao;
import com.chengtao.entity.Supply;

public class SupplyImpl extends BaseImpl implements SupplyDao{
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String DETAIL = "detail";
	private static final String IMAGES = "images";
	private static final String CREATE_AT = "createAt";
	private static final String UPDATE_AT = "updateAt";
	private static final String DEAD_LINE = "deadline";
	private static final String VISIT = "visit";
	private static final String COMPANY_ID	= "company_id";
	private static final String COMPANY_NAME	= "company_name";
	private static final String TOTAL = "total";
	
	private static final String GET_ALL_SUPPLY = "call getAllSupply(?)";
	
	private Supply getSupply(ResultSet set) {
		Supply supply = new Supply();
		try {
			supply.setId(set.getInt(ID));
			supply.setName(set.getString(NAME));
			supply.setDetail(DETAIL);
			String images = set.getString(IMAGES);
			if (images != null && !images.equals("")) {
				String mImages[] = images.split(",");
				supply.setImages((ArrayList<String>)Arrays.asList(mImages));
			}
			try {
				supply.setCreatAt(set.getTimestamp(CREATE_AT));
				supply.setUpdateAt(set.getTimestamp(UPDATE_AT));
				supply.setDeadLine(set.getTimestamp(DEAD_LINE));
			} catch (Exception e) {
				
			}
			supply.setCompanyName(set.getString(COMPANY_NAME));
			supply.setTotal(set.getInt(TOTAL));
			supply.setVisit(set.getInt(VISIT));
			supply.setCompanyId(set.getInt(COMPANY_ID));
		} catch (Exception e) {
			printException("getDemand", e);
		}
		return supply;
	}
	
	@Override
	public ArrayList<Supply> getAllSupplys(int limit) {
		ArrayList<Supply> list = new ArrayList<Supply>();
		ResultSet set = getResultSet(GET_ALL_SUPPLY, limit);
		if (set != null) {
			try {
				while (set.next()) {
					list.add(getSupply(set));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					set.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

}
