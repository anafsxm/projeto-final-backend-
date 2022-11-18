package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.produto


fun Produto.toProdutoDto(): ProdutoDto =
    ProdutoDto(
        this.id,
        this.nome,
        this.quantidade,
        this.descricao,
        this.valor,
        this.certificacoes
    )

fun ProdutoDto.toProduto() =
    Produto(
        this.id,
        this.nome,
        this.quantidade,
        this.descricao,
        this.valor,
        this.certificacoes
    )