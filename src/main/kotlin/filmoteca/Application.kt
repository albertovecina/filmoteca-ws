package filmoteca

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("filmoteca")
                .mainClass(Application.javaClass)
                .start()
    }
}