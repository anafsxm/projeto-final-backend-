package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.carrinho

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarrinhoRepository : JpaRepository<Carrinho, Long> {

    fun findByUsuarioId(id: Long): Carrinho?
}