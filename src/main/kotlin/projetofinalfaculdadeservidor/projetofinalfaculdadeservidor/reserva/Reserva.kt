package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.reserva

import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.base.EntidadeBase
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.restaurante.Mesa
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.usuario.Usuario
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "reserva")
class Reserva(
    @ManyToOne
    @JoinColumn(name = "mesa_id")
    var mesa: Mesa,
    var qtPessoas: Long,
    var diaReservado: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    var usuario: Usuario
) : EntidadeBase()