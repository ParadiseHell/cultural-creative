package com.chengtao.dao;

import java.util.ArrayList;

import com.chengtao.entity.Demand;

public interface DemandDao {
	ArrayList<Demand> getAllDemands(int limit);
}
