package filmoteca.security

import io.micronaut.security.authentication.*
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import java.util.*
import javax.inject.Singleton


@Singleton
class BasicAuthenticationProvider : AuthenticationProvider {

    override fun authenticate(authenticationRequest: AuthenticationRequest<*, *>?): Publisher<AuthenticationResponse> {
        authenticationRequest?.let {
            return if (checkCredentials(it)) {
                Flowable.just(UserDetails(authenticationRequest.identity as String, ArrayList()))
            } else
                Flowable.just(AuthenticationFailed())
        } ?: return Flowable.just(AuthenticationFailed())
    }

    private fun checkCredentials(authenticationRequest: AuthenticationRequest<*, *>): Boolean =
        authenticationRequest.identity == BasicAuthCredentials.USER
                && authenticationRequest.secret == BasicAuthCredentials.PASSWORD

}