package com.chengtao.pool;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionPool {
	private static final String MYSQL_NAME = "MySQL";
	private static ComboPooledDataSource source = null;
	static{
		try {
			source = new ComboPooledDataSource(MYSQL_NAME);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static Connection getConnection() throws SQLException {
		if (source == null) {
			throw new RuntimeException("��ʼ�����ӳ�ʧ��");
		}
		return source.getConnection();
	}
}
