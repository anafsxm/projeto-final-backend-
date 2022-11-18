package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.web.config.EnableSpringDataWebSupport

@SpringBootApplication
@EnableSpringDataWebSupport
class ProjetofinalFaculdadeServidorApplication

fun main(args: Array<String>) {
	runApplication<ProjetofinalFaculdadeServidorApplication>(*args)
}
