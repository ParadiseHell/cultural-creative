package com.chengtao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import com.chengtao.dao.NewsDao;
import com.chengtao.entity.News;

public class NewsImpl extends BaseImpl implements NewsDao{
	private static final String ID = "id";
	private static final String COMPANY_ID = "company_id";
	private static final String COMPANY_NAME = "company_name";
	private static final String TITLE = "title";
	private static final String CREATE_AT = "createAt";
	private static final String UPDATE_AT = "updateAt";
	private static final String DETAIL = "detail";
	private static final String IMAGES = "images";
	private static final String VISIT = "visit";
	private static final String TOTAL = "total";
	private static final String GET_ALL_NEWS = "call getAllNews(?)";
	@Override
	public ArrayList<News> getAllNews(int limit) {
		ArrayList<News> list = new ArrayList<News>();
		ResultSet set = getResultSet(GET_ALL_NEWS, limit);
		if (set != null) {
			try {
				while (set.next()) {
					list.add(getNews(set));
				}
			} catch (SQLException e) {
				printException("getAllNews", e);
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
	private News getNews(ResultSet set) {
		News news = new News();
		try {
			news.setId(set.getInt(ID));
			news.setCompanyId(set.getInt(COMPANY_ID));
			news.setCompanyName(set.getString(COMPANY_NAME));
			news.setTitle(set.getString(TITLE));
			try {
				news.setCreatAt(set.getTimestamp(CREATE_AT));
				news.setUpdateAt(set.getTimestamp(UPDATE_AT));
			} catch (Exception e) {
			}
			news.setDetail(set.getString(DETAIL));
			news.setTotal(set.getInt(TOTAL));
			String images = set.getString(IMAGES);
			if (images != null && !images.equals("")) {
				String mImages[] = images.split(",");
				news.setImages((ArrayList<String>)Arrays.asList(mImages));
			}
			news.setVisit(set.getInt(VISIT));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return news;
	}

}