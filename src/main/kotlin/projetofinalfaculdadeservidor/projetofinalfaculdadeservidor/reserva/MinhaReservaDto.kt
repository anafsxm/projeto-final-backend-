package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.reserva

import java.time.LocalDateTime

class MinhaReservaDto(
    val restaurante: String = "",
    val dia: LocalDateTime = LocalDateTime.now(),
    val qtPessoas: Long = 0,
    val idReserva: Long = 0,
    val mesaId: Long = 0
) {
}