package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.restaurante

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RestauranteRepository : JpaRepository<Restaurante, Long> {

    fun findByCidade(cidade: String): List<Restaurante>
}