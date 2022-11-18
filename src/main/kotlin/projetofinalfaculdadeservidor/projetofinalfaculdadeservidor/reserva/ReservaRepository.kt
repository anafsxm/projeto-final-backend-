package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.reserva

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface ReservaRepository : JpaRepository<Reserva, Long> {

    fun findByUsuarioId(id: Long): List<Reserva>?

    @Query("select R from reserva R where R.mesa.id = :id and R.diaReservado >= :dataInicio and R.diaReservado <= :dataFim")
    fun findMesaReservada(id: Long, dataInicio: LocalDateTime, dataFim: LocalDateTime): Reserva?
}