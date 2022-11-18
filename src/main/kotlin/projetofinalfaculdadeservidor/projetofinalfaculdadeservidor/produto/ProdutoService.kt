package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.produto

import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ProdutoService(
    val produtoRepository: ProdutoRepository
) {

    @Transactional
    fun criaProduto(produto: Produto): Produto {
        return produtoRepository.save(produto)
    }

    fun pegaProduto(id: Long): Produto {
        return produtoRepository.findById(id).orElseThrow {
            Exception("Produto com id $id não encontrado")
        }
    }

    @Transactional
    fun editaProduto(id: Long, editaProdutoDto: EditaProdutoDto): Produto {
        val produto = pegaProduto(id)

        produto.nome = editaProdutoDto.nome
        produto.quantidade = editaProdutoDto.quantidade
        produto.descricao = editaProdutoDto.descricao
        produto.valor = editaProdutoDto.valor
        return produtoRepository.save(produto)
    }

    @Transactional
    fun deletaProduto(id: Long) {
        produtoRepository.deleteById(id)
    }

    fun pegaProdutosComEstoque(): List<Produto> {
        return produtoRepository.findAll().filter { prod -> prod.quantidade > 0 }
    }

    fun pegaProdutos(): List<Produto> {
        return produtoRepository.findAll()
    }

    fun buscaProdutoPeloNome(nome: String): List<Produto> {
        return pegaProdutosComEstoque().filter {
            it.nome.uppercase() == nome.uppercase()
        }
    }
}