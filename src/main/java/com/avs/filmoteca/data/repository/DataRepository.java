package com.avs.filmoteca.data.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.avs.filmoteca.data.db.DataBase;
import com.mysql.cj.jdbc.MysqlDataSource;

public class DataRepository {

	private static DataRepository sDataRepository;

	private MysqlDataSource mMySqlDataSource;
	private static Connection sMySqlConnection;

	public static DataRepository getInstance() {
		if (sDataRepository == null)
			sDataRepository = new DataRepository();
		return sDataRepository;
	}

	private DataRepository() {
		mMySqlDataSource = new MysqlDataSource();
		mMySqlDataSource.setUser(DataBase.DATABASE_USER);
		mMySqlDataSource.setPassword(DataBase.DATEBASE_PASSWORD);
		mMySqlDataSource.setServerName(DataBase.DATABASE_SERVER_NAME);
		mMySqlDataSource.setDatabaseName(DataBase.DATABASE_NAME);
		try {
			sMySqlConnection = mMySqlDataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<String> getMovies() {
		List<String> movieTitles = new ArrayList<String>();
		try {
			Statement stmt = sMySqlConnection.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM " + DataBase.TABLE_MOVIES);
			while (resultSet.next())
				movieTitles.add(resultSet.getString(DataBase.ROW_TITLE));
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movieTitles;
	}

	public void addMovies(List<String> movieTitles) {
		for (String movieTitle : movieTitles) {
			Statement stmt;
			try {
				stmt = sMySqlConnection.createStatement();
				stmt.executeUpdate("INSERT INTO " + DataBase.TABLE_MOVIES + " (" + DataBase.ROW_TITLE + ") values('"
						+ movieTitle + "')");
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void deleteMovies() {
		try {
			Statement stmt = sMySqlConnection.createStatement();
			stmt.executeUpdate("DELETE FROM " + DataBase.TABLE_MOVIES);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeRepository() {
		try {
			sDataRepository = null;
			if (sMySqlConnection != null)
				sMySqlConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
