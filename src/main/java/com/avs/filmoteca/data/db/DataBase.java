package com.avs.filmoteca.data.db;

public class DataBase {

	public enum Environment {
		DEV(DATABASE_NAME_TEST), PRO(DATABASE_NAME);

		private String dataBaseName;

		Environment(String dataBaseName) {
			this.dataBaseName = dataBaseName;
		}

		public String getDataBaseName() {
			return dataBaseName;
		}

		public static Environment findByPath(String path) {
			if (path.equals("dev"))
				return Environment.DEV;
			else
				return Environment.PRO;
		}
	};

	public static final String DATABASE_USER = System.getenv("FILMOTECA_DATABASE_USER");
	public static final String DATEBASE_PASSWORD = System.getenv("FILMOTECA_DATABASE_PASSWORD");
	public static final String DATABASE_SERVER_NAME = System.getenv("FILMOTECA_DATABASE_SERVER");

	public static final String DATABASE_NAME = "Filmoteca";
	public static final String DATABASE_NAME_TEST = "FilmotecaDev";

	public static final String TABLE_MOVIES = "MovieTitle";
	public static final String TABLE_REGISTRATION_TOKEN = "PushRegistrationToken";

	public static final String ROW_TITLE = "title";
	public static final String ROW_TOKEN = "token";

}
