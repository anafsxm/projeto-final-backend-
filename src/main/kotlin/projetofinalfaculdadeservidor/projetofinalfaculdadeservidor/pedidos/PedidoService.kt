package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.pedidos

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.carrinho.CarrinhoService
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.produto.ProdutoService
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.usuario.UsuarioService
import java.security.MessageDigest
import java.time.Instant
import java.time.LocalDateTime

@Service
class PedidoService(
    val pedidoRepository: PedidoRepository,
    val produtoService: ProdutoService,
    val usuarioService: UsuarioService,
    val carrinhoService: CarrinhoService
) {

    @Transactional
    fun criaPedido(userId: Long): Pedido {
        val usuario = usuarioService.pegaUsuarioPeloId(userId)
        val carrinho = carrinhoService.pegaCarrinho(userId)
        val pedido = Pedido(
            dataPedido = LocalDateTime.now(),
            status = PedidoStatus.PENDENTE_ENVIO,
            usuario = usuario
        )
        carrinho.itensCarrinho.forEach { item ->
            if (item.qt > item.produto.quantidade) {
                throw Exception("Não podemos reservar,quatidade do item  ${item.produto.nome} no pedido é maior o que possuimos no estoque")
            }
            pedido.itensPedido.add(ItemPedido(item.produto, pedido, item.qt))
        }
        carrinho.itensCarrinho.clear()
        return pedidoRepository.save(pedido)
    }

    @Transactional(readOnly = true)
    fun getPedido(id: Long): Pedido {
        return pedidoRepository.findById(id).orElseThrow { Exception("Pedido com id $id não encontrado") }
    }

    @Transactional
    fun atualizaPedidoEnviado(id: Long): Pedido {
        val pedido = getPedido(id)
        pedido.itensPedido.forEach { item ->
            if (item.produto.quantidade - item.qt < 0) {
                throw Exception("Não podemos fechar o pedido, estoque insuficiente para o produto,${item.produto}")
            }
            item.produto.quantidade -= item.qt
        }
        pedido.codigoRastreio =
            MessageDigest.getInstance("MD5").digest(Instant.now().toString().toByteArray()).toString()
                .subSequence(0..10).toString()

        pedido.status = PedidoStatus.ENVIADO
        return pedidoRepository.save(pedido)
    }

    @Transactional
    fun cancelaPedido(id: Long) {
        val pedido = getPedido(id)
        if (pedido.status == PedidoStatus.ENVIADO) {
            pedido.itensPedido.forEach { item ->
                item.produto.quantidade += item.qt
            }
        }
        pedido.status = PedidoStatus.CANCELADO
        pedidoRepository.save(pedido)
    }

    fun getPedidosUsuario(id: Long): MutableList<Pedido> {
        var pedidos = pedidoRepository.findByUsuarioId(id)
        pedidos.sortedByDescending { it.dataPedido }
        return pedidos
    }

    fun getPedidosAEnviar(): MutableList<Pedido> {
        return pedidoRepository.findByStatus(PedidoStatus.PENDENTE_ENVIO)
    }

    fun pegaTodosOsPedidos(): MutableList<Pedido> {
        var pedidos = pedidoRepository.findAll()
        pedidos.sortedByDescending { it.dataPedido }
        return pedidos
    }


    fun pagaPedido(id: Long): Pedido {
        val pedido = getPedido(id)
        pedido.status = PedidoStatus.PENDENTE_ENVIO
        return pedidoRepository.save(pedido)
    }
}