package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.usuario

fun UsuarioDto.toUsuario() =
    Usuario(
        this.nome,
        this.senha,
        this.email,
        this.telefone
    )

fun Usuario.toUsuarioDto() =
    UsuarioDto(
        this.id,
        this.nome,
        "",
        this.email,
        this.telefone,
        this.tipoUsuario
    )