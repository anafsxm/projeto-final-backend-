package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.pedidos

import org.springframework.data.annotation.CreatedDate
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.base.EntidadeBase
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.usuario.Usuario
import java.time.LocalDateTime
import javax.persistence.*


@Entity(name = "pedido")
class Pedido(
    @OneToMany(mappedBy = "pedido", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var itensPedido: MutableList<ItemPedido> = mutableListOf(),
    @CreatedDate
    var dataPedido: LocalDateTime,
    @Enumerated(value = EnumType.STRING)
    var status: PedidoStatus,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    var usuario: Usuario,
    var codigoRastreio: String = ""
) : EntidadeBase()