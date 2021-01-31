package filmoteca.utils

import filmoteca.data.db.DataBase.Environment

object RequestUtils {

    fun getEnvironment(environment: String?): Environment {
        return when (environment) {
            "pro" -> Environment.PRO
            "dev" -> Environment.DEV
            else -> Environment.DEV
        }
    }

}
