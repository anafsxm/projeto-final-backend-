package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.usuario

import com.fasterxml.jackson.annotation.JsonProperty
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.base.EntidadeBase
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity(name = "usuario")
class Usuario(
    var nome: String = "",
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var senha: String = "",
    var email: String = "",
    var telefone: String,
    @Enumerated(value = EnumType.STRING)
    var tipoUsuario: TipoUsuario = TipoUsuario.USER
) : EntidadeBase()