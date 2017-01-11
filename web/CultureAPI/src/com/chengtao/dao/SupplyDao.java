package com.chengtao.dao;

import java.util.ArrayList;

import com.chengtao.entity.Supply;

public interface SupplyDao {
	ArrayList<Supply> getAllSupplys(int limit);
}
