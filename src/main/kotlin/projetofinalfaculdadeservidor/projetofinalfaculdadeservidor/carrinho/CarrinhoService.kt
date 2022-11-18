package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.carrinho

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.produto.ProdutoService
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.usuario.UsuarioService

@Service
class CarrinhoService(
    val carrinhoRepository: CarrinhoRepository,
    val usuarioService: UsuarioService,
    val produtoService: ProdutoService
) {


    @Transactional
    fun atualizaCarrinho(usuarioId: Long, itemCarrinho: ItemCarrinhoDto): Carrinho {
        val usuario = usuarioService.pegaUsuarioPeloId(usuarioId)
        val carrinho = carrinhoRepository.findByUsuarioId(usuarioId) ?: Carrinho(usuario = usuario)
        val item = carrinho.itensCarrinho.find { it.produto.id == itemCarrinho.produtoId }
        item?.let {
            if (itemCarrinho.qt == 0L) {
                println("Removendo item da lista ${itemCarrinho.produtoId}  ::${carrinho.itensCarrinho.remove(item)}")
            }else{
                it.qt = itemCarrinho.qt
            }
        } ?: kotlin.run {
            val prod = produtoService.pegaProduto(itemCarrinho.produtoId)
            carrinho.itensCarrinho.add(ItemCarrinho(prod, carrinho, itemCarrinho.qt))
        }
        return carrinhoRepository.save(carrinho)
    }

    fun pegaCarrinho(usuarioId: Long): Carrinho {
        return carrinhoRepository.findByUsuarioId(usuarioId)
            ?: throw Exception("Carrinho não encontrado para usuário id $usuarioId")
    }
}