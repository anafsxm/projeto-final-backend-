package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.produto

import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@CrossOrigin
@RequestMapping("/v1/produtos")
class ProdutoController(
    val produtoService: ProdutoService
) {

    @PostMapping
    fun criaprodutos(@RequestBody @Valid produtoDto: ProdutoDto): ProdutoDto {
        return produtoService.criaProduto(produtoDto.toProduto()).toProdutoDto()
    }

    @GetMapping("/{id}")
    fun pegaProduto(@PathVariable("id") id: Long): ProdutoDto? {
        return produtoService.pegaProduto(id).toProdutoDto()
    }

    @PutMapping("/{id}")
    fun editaProdutos(@RequestBody @Valid editaProdutoDto: EditaProdutoDto, @PathVariable("id") id: Long): ProdutoDto {
        return produtoService.editaProduto(id, editaProdutoDto).toProdutoDto()
    }


    @DeleteMapping("/{id}")
    fun deletaProduto(@PathVariable("id") id: Long) {
        produtoService.deletaProduto(id)
    }

    @GetMapping("/estoque")
    fun getProdutosComEstoque(): List<Produto> {
        return produtoService.pegaProdutosComEstoque()
    }


    @GetMapping("/todos")
    fun getTodosProdutos(): List<Produto> {
        return produtoService.pegaProdutos()
    }

    @GetMapping("/por-nome/{produto}")
    fun pesquisaProduto(@PathVariable("produto") produto: String): List<Produto> {
        return produtoService.buscaProdutoPeloNome(produto)
    }
}
