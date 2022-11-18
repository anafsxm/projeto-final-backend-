package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.pedidos

class PedidoDto(
    val itensPedido: List<ItemPedidoDto> = mutableListOf()
)


data class ItemPedidoDto(
    var produtoId: Long,
    var quantidade: Long
)