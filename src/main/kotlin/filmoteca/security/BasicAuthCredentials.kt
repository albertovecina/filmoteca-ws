package filmoteca.security

object BasicAuthCredentials {
    val USER: String = System.getenv("BASIC_AUTH_USER")
    val PASSWORD: String = System.getenv("BASIC_AUTH_PASSWORD")
}