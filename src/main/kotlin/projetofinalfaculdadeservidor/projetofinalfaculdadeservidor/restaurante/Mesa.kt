package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.restaurante

import com.fasterxml.jackson.annotation.JsonBackReference
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.base.EntidadeBase
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "mesa")
class Mesa(
    var qtLugares: Long = 0L,
    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    @JsonBackReference
    var restaurante: Restaurante,
    var reservada: Boolean = false
) : EntidadeBase()
