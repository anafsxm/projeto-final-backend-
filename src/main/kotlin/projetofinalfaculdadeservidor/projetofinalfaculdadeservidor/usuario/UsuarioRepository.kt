package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.usuario

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long> {

    fun findByNome(nome: String): Usuario?
    fun findByEmail(nome: String): Usuario?
}