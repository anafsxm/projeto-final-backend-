package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.carrinho

import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.base.EntidadeBase
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.usuario.Usuario
import javax.persistence.*

@Entity(name = "carrinho")
class Carrinho(
    @OneToMany(mappedBy = "carrinho", cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true)
    var itensCarrinho: MutableList<ItemCarrinho> = mutableListOf(),
    @OneToOne
    @JoinColumn(name = "usuario_id")
    var usuario: Usuario
) : EntidadeBase()