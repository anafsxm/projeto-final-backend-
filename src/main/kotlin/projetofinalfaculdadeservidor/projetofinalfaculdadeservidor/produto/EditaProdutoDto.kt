package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.produto

import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class EditaProdutoDto(
    val id: Long? = null,
    @NotBlank(message = "Nome não pode estar vazio")
    val nome: String = "",
    val quantidade: Long = 0L,
    val qtReservada: Long = 0L,
    @NotBlank(message = "Descrição não pode estar vazia")
    val descricao: String = "",
    @NotNull(message = "Valor do produto deve ser informado")
    val valor: BigDecimal = BigDecimal.ZERO
)
