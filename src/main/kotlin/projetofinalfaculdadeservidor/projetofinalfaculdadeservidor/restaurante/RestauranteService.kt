package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.restaurante

import org.springframework.stereotype.Service

@Service
class RestauranteService(
    val restauranteRepository: RestauranteRepository
) {
    fun pegaRestaurantePorcidade(cidade: String): List<Restaurante> {
        return restauranteRepository.findByCidade(cidade)
    }

    fun pegatodosComMesa(): List<Restaurante> {
        return restauranteRepository.findAll().filter { restaurante -> restaurante.mesas.any { !it.reservada } }
    }
}