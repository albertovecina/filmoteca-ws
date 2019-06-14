package filmoteca.data.db

object DataBase {

    val DATABASE_USER = System.getenv("FILMOTECA_DATABASE_USER")
    val DATEBASE_PASSWORD = System.getenv("FILMOTECA_DATABASE_PASSWORD")
    val DATABASE_SERVER_NAME = System.getenv("FILMOTECA_DATABASE_SERVER")

    val DATABASE_NAME = "Filmoteca"
    val DATABASE_NAME_TEST = "FilmotecaDev"

    val TABLE_MOVIES = "MovieTitle"
    val TABLE_REGISTRATION_TOKEN = "PushRegistrationToken"

    val ROW_TITLE = "title"
    val ROW_TOKEN = "token"

    enum class Environment private constructor(val dataBaseName: String) {
        DEV(DATABASE_NAME_TEST), PRO(DATABASE_NAME);
    }

}
