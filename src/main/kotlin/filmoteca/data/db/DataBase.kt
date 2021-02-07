package filmoteca.data.db

object DataBase {

    val DATABASE_USER: String = System.getenv("FILMOTECA_DATABASE_USER")
    val DATEBASE_PASSWORD: String = System.getenv("FILMOTECA_DATABASE_PASSWORD")
    val DATABASE_SERVER_NAME: String = System.getenv("FILMOTECA_DATABASE_SERVER")

    const val DATABASE_NAME: String = "Filmoteca"
    const val DATABASE_NAME_TEST: String = "FilmotecaDev"

    const val TABLE_MOVIES: String = "MovieTitle"
    const val TABLE_REGISTRATION_TOKEN: String = "PushRegistrationToken"

    const val COL_TITLE: String = "title"
    const val COL_TOKEN: String = "token"
    const val COL_REGION: String = "region"

    enum class Environment private constructor(val dataBaseName: String) {
        DEV(DATABASE_NAME_TEST), PRO(DATABASE_NAME);
    }

}
