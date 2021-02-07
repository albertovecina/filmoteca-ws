package filmoteca

import com.google.gson.Gson
import filmoteca.data.Endpoint
import filmoteca.data.repository.DataRepository
import filmoteca.utils.RequestUtils
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/${Endpoint.ENDPOINT}/{env}/movies")
class Movies {

    private val gson = Gson()

    @Get(produces = [MediaType.APPLICATION_JSON])
    fun doGet(
        env: String?,
        @QueryValue(QueryParameter.REGION, defaultValue = QueryParameter.REGION_DEFAULT) region: String
    ): String {
        val movieTitles = DataRepository.getInstance(RequestUtils.getEnvironment(env)).getMovies(region)
        DataRepository.closeRepository()
        return gson.toJson(movieTitles)
    }

    @Post(produces = [MediaType.APPLICATION_JSON])
    fun doPost(
        env: String?,
        @QueryValue(QueryParameter.REGION, defaultValue = QueryParameter.REGION_DEFAULT) region: String,
        @Body movies: List<String>
    ): String {
        val environment = RequestUtils.getEnvironment(env)
        DataRepository.getInstance(environment).setMovies(movies, region)
        return doGet(env, region)
    }

}
