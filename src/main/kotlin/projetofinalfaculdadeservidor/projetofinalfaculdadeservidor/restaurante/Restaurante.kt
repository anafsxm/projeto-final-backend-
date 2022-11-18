package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.restaurante

import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.base.EntidadeBase
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany

@Entity(name = "restaurante")
class Restaurante(
    var nome: String,
    var cidade: String,
    @OneToMany(mappedBy = "restaurante", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var mesas: MutableList<Mesa> = mutableListOf()
) : EntidadeBase()