package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.restaurante

import java.time.LocalDateTime

class RestauranteReservaDTO(
    val mesaId: Long,
    val dia: LocalDateTime,
    val qtPessoas: Long
)