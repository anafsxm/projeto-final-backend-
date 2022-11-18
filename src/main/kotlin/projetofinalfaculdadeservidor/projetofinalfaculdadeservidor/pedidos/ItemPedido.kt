package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.pedidos

import com.fasterxml.jackson.annotation.JsonBackReference
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.base.EntidadeBase
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.produto.Produto
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "item_pedido")
class ItemPedido(
    @ManyToOne(fetch = FetchType.EAGER)
    var produto: Produto,
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @JsonBackReference
    var pedido: Pedido,
    var qt: Long
) : EntidadeBase()