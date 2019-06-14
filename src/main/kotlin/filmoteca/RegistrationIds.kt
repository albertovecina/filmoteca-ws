package filmoteca


import com.google.gson.Gson
import filmoteca.data.Endpoint
import filmoteca.data.repository.DataRepository
import filmoteca.utils.RequestUtils
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue

@Controller("/${Endpoint.ENDPOINT}/{env}/registrationId")
class RegistrationIds {

    @Get(produces = [MediaType.APPLICATION_JSON])
    public fun doGet(env: String): String {
        val registrationIds = DataRepository.getInstance(RequestUtils.getEnvironment(env))
                .getPushRegistrationIds()
        DataRepository.closeRepository()
        return Gson().toJson(registrationIds)
    }

    @Post
    public fun doPost(env: String, @QueryValue token: String) {
        DataRepository.getInstance(RequestUtils.getEnvironment(env))
                .addPushRegistrationId(token)
        DataRepository.closeRepository()
    }

}