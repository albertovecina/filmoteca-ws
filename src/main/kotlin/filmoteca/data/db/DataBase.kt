package filmoteca.data.db

object DataBase {

    val DATABASE_USER: String = System.getenv("FILMOTECA_DATABASE_USER")
    val DATEBASE_PASSWORD: String = System.getenv("FILMOTECA_DATABASE_PASSWORD")
    val DATABASE_SERVER_NAME: String = System.getenv("FILMOTECA_DATABASE_SERVER")

    val DATABASE_NAME: String = "Filmoteca"
    val DATABASE_NAME_TEST: String = "FilmotecaDev"

    val TABLE_MOVIES: String = "MovieTitle"
    val TABLE_REGISTRATION_TOKEN: String = "PushRegistrationToken"

    val ROW_TITLE: String = "title"
    val ROW_TOKEN: String = "token"

    enum class Environment private constructor(val dataBaseName: String) {
        DEV(DATABASE_NAME_TEST), PRO(DATABASE_NAME);
    }

}
