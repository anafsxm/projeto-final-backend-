package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.restaurante

import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin
@RequestMapping("/v1/restaurante")
class RestauranteController(
    val restauranteService: RestauranteService
) {


    @GetMapping("/por-cidade/{cidade}")
    fun pegaResturantes(@PathVariable("cidade") cidade: String): List<Restaurante> {
        return restauranteService.pegaRestaurantePorcidade(cidade)
    }

    @GetMapping("/todos")
    fun getTodos(): List<Restaurante> {
        return restauranteService.pegatodosComMesa()
    }
}