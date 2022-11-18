package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.carrinho

import com.fasterxml.jackson.annotation.JsonBackReference
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.base.EntidadeBase
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.produto.Produto
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "item_carrinho")
class ItemCarrinho(
    @ManyToOne(fetch = FetchType.EAGER)
    var produto: Produto,
    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    @JsonBackReference
    var carrinho: Carrinho,
    var qt: Long
) : EntidadeBase()