package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.produto

import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ProdutoDto(
    var id: Long? = 0L,
    @field:NotBlank(message = "Nome não pode estar vazio")
    var nome: String = "",
    var quantidade: Long = 0L,
    @field:NotBlank(message = "Descrição não pode estar vazia")
    var descricao: String = "",
    @field:NotNull(message = "Valor do produto deve ser informado")
    var valor: BigDecimal = BigDecimal.ZERO,
    var certificacoes: String
)

