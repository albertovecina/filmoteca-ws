package filmoteca

import com.google.gson.Gson
import filmoteca.data.Endpoint
import filmoteca.data.repository.DataRepository
import filmoteca.utils.RequestUtils
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("/${Endpoint.ENDPOINT}/{env}/movies")
class Movies {

    private val gson = Gson()

    @Get(produces = [MediaType.APPLICATION_JSON])
    public fun doGet(env: String?): String {
        val movieTitles = DataRepository.getInstance(RequestUtils.getEnvironment(env)).getMovies()
        DataRepository.closeRepository()
        return gson.toJson(movieTitles)
    }

    @Post(produces = [MediaType.APPLICATION_JSON])
    public fun doPost(env: String?, @Body movies: List<String>): String {
        val environment = RequestUtils.getEnvironment(env)
        DataRepository.getInstance(environment).setMovies(movies)
        return doGet(env)
    }

}
