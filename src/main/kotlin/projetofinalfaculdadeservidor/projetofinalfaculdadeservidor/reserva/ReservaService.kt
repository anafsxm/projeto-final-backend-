package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.reserva

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.restaurante.Mesa
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.restaurante.MesaRepository
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.restaurante.RestauranteReservaDTO
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.usuario.UsuarioService
import java.time.LocalDateTime

@Service
class ReservaService(
    val reservaRepository: ReservaRepository,
    val usuarioService: UsuarioService,
    val mesaRepository: MesaRepository
) {

    @Transactional
    fun criaReserva(restauranteReservaDTO: RestauranteReservaDTO, userId: Long): Reserva {
        val usuario = usuarioService.pegaUsuarioPeloId(userId)
        val dia = arredondaHorario(restauranteReservaDTO.dia)
        val mesa = mesaRepository.findById(restauranteReservaDTO.mesaId)
            .orElseThrow { Exception("Mesa com id ${restauranteReservaDTO.mesaId} não encontrada") }
        validaMesaDisponivel(mesa, dia)

        val reserva =
            Reserva(mesa, restauranteReservaDTO.qtPessoas, dia, usuario)
        mesa.reservada = true
        return reservaRepository.save(reserva)
    }

    private fun validaMesaDisponivel(mesa: Mesa, dia: LocalDateTime) {
        val inicio = arredondaHorario(dia)
        val fim = inicio.plusHours(1)
        val reservaComMesaDesejada = reservaRepository.findMesaReservada(mesa.id!!, inicio, fim)

        if (reservaComMesaDesejada != null) {
            throw Exception("Mesa com id ${mesa.id} já está reservada")
        }
    }

    private fun arredondaHorario(dia: LocalDateTime): LocalDateTime {
        return dia.withSecond(0).withMinute(0)
    }


    fun getMinhasReservas(userID: Long): List<Reserva> {
        val reservas = reservaRepository.findByUsuarioId(userID)
            ?: throw Exception("Reservas não encontradas para o usuário com id $userID")
        reservas.sortedByDescending { it.diaReservado }
        return reservas
    }

    fun getAllReservas(): List<Reserva> {
        val reservas = reservaRepository.findAll()
        reservas.sortByDescending { it.diaReservado }
        return reservas
    }

    @Transactional
    fun editaReserva(id: Long, restauranteReservaDTO: RestauranteReservaDTO): Reserva {
        val reserva = reservaRepository.findById(id)
            .orElseThrow { throw Exception("Reservas com id $id não encontrada") }

        reserva.diaReservado = arredondaHorario(restauranteReservaDTO.dia)
        val mesa = mesaRepository.findById(restauranteReservaDTO.mesaId)
            .orElseThrow { Exception("Mesa com id ${restauranteReservaDTO.mesaId} não encontrada") }

        if (reserva.mesa.id != restauranteReservaDTO.mesaId) {
            reserva.mesa.reservada = false
            validaMesaDisponivel(mesa, reserva.diaReservado)
            reserva.mesa = mesa
            reserva.mesa.reservada = true
        }

        reserva.qtPessoas = restauranteReservaDTO.qtPessoas
        reservaRepository.save(reserva)

        return reserva

    }

    @Transactional
    fun deletaReserva(id: Long) {
        val reserva =
            reservaRepository.findById(id).orElse(null) ?: throw Exception("Reserva com id $id não encontrada")
        reserva.mesa.reservada = false
        reservaRepository.deleteById(id)
    }

    fun pegaReserva(id: Long): Reserva {
        val reservas = reservaRepository.findById(id).orElse(null)
            ?: throw Exception("Reserva não encontrada com id $id")
        return reservas
    }
}