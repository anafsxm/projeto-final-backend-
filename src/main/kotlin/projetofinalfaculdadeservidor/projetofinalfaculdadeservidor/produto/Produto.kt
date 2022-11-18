package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.produto

import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.base.EntidadeBase
import java.math.BigDecimal
import javax.persistence.Entity
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity(name = "produto")
class Produto(
    override var id: Long? = null,
    @field:NotBlank(message = "Nome do produto não pode estar vazio")
    var nome: String = "",
    var quantidade: Long = 0L,
    @field:NotBlank(message = "Descrição não pode estar vazia")
    var descricao: String = "",
    @field:NotNull
    var valor: BigDecimal = BigDecimal.ZERO,
    var certificacoes: String
) : EntidadeBase()