package filmoteca.data.repository

import com.mysql.cj.jdbc.MysqlDataSource
import filmoteca.data.db.DataBase
import java.sql.Connection
import java.sql.SQLException
import java.sql.Statement
import java.util.*

class DataRepository private constructor() {

    companion object {

        private val dataRepository: DataRepository by lazy { DataRepository() }
        private lateinit var mySqlConnection: Connection

        fun getInstance(environment: DataBase.Environment): DataRepository {
            dataRepository.setEnvironment(environment)
            dataRepository.createDataBaseConnection()
            return dataRepository
        }

        fun closeRepository() =
                dataRepository.closeDataBaseConnection()

    }

    private val mySqlDataSource: MysqlDataSource = MysqlDataSource().apply {
        user = DataBase.DATABASE_USER
        password = DataBase.DATEBASE_PASSWORD
        serverName = DataBase.DATABASE_SERVER_NAME
    }

    private fun setEnvironment(environment: DataBase.Environment) {
        mySqlDataSource.databaseName = environment.dataBaseName
    }

    private fun createDataBaseConnection() {
        try {
            mySqlConnection = mySqlDataSource.connection
        } catch (e: SQLException) {
            e.printStackTrace()
        }

    }

    private fun closeDataBaseConnection() {
        try {
            mySqlConnection.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun getPushRegistrationIds(): List<String> {
        val registrationIds = ArrayList<String>()
        try {
            val statement = mySqlConnection.createStatement()
            val resultSet = statement.executeQuery("SELECT * FROM " + DataBase.TABLE_REGISTRATION_TOKEN)
            while (resultSet.next())
                registrationIds.add(resultSet.getString(DataBase.ROW_TOKEN))
            resultSet.close()
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return registrationIds
    }

    fun addPushRegistrationId(token: String) {
        try {
            val statement = mySqlConnection.createStatement()
            statement.executeUpdate("INSERT INTO " + DataBase.TABLE_REGISTRATION_TOKEN + " (" + DataBase.ROW_TOKEN
                    + ") values('" + token + "')")
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }

    }

    fun getMovies(): List<String> {
        val movieTitles = ArrayList<String>()
        try {
            val stmt = mySqlConnection.createStatement()
            val resultSet = stmt.executeQuery("SELECT * FROM " + DataBase.TABLE_MOVIES)
            while (resultSet.next())
                movieTitles.add(resultSet.getString(DataBase.ROW_TITLE))
            resultSet.close()
            stmt.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return movieTitles
    }

    fun setMovies(movieTitles: List<String>) {
        deleteMovies()
        addMovies(movieTitles)
    }

    fun addMovies(movieTitles: List<String>) {
        for (movieTitle in movieTitles) {
            val statement: Statement
            try {
                statement = mySqlConnection.createStatement()
                statement.executeUpdate("INSERT INTO " + DataBase.TABLE_MOVIES + " (" + DataBase.ROW_TITLE + ") values('"
                        + movieTitle + "')")
                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }

        }
    }

    fun deleteMovies() {
        try {
            val statement = mySqlConnection.createStatement()
            statement.executeUpdate("DELETE FROM " + DataBase.TABLE_MOVIES)
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }

    }

}
