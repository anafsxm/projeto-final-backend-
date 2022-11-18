package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.usuario

import javax.validation.constraints.NotBlank

data class UsuarioDto(
    var id: Long? = null,
    @field:NotBlank(message = "Nome deve ser preenchido")
    var nome: String = "",
    @field:NotBlank(message = "Senha deve ser preenchida")
    var senha: String = "",
    @field:NotBlank(message = "email deve ser preenchido")
    var email: String = "",
    @field:NotBlank(message = "Telefone deve ser preenchido")
    var telefone: String,
    var role: TipoUsuario? = null,
)