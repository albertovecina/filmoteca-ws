package com.avs.filmoteca.data.db;

public class DataBase {
	
	public static final String DATABASE_USER = System.getenv("FILMOTECA_DATABASE_USER");
	public static final String DATEBASE_PASSWORD = System.getenv("FILMOTECA_DATABASE_PASSWORD");
	public static final String DATABASE_SERVER_NAME = System.getenv("FILMOTECA_DATABASE_SERVER");
	
	public static final String DATABASE_NAME = "Filmoteca";
	
	public static final String TABLE_MOVIES = "MovieTitle";
	public static final String ROW_TITLE = "title";


}
