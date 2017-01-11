package com.chengtao.dao;

import java.util.ArrayList;

import com.chengtao.entity.News;

public interface NewsDao {
	ArrayList<News> getAllNews(int limit);
}
