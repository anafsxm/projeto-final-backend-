package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.base

class TrataErro(
    val mensagem: String? = ""
)

class CampoInvalido(
    val campo: String,
    val mensagem: String?
)

fun String.getIDUsuario(): Long {
    return this.split("ROLE_ID|")[1].toLong()
}