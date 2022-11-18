package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.usuario

import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@CrossOrigin
@RequestMapping("/v1/admin/user")
class UserControllerAdmin(
    val usuarioService: UsuarioService
) {

    @PostMapping
    fun createAdmin(@RequestBody @Valid usuarioDto: UsuarioDto): UsuarioDto {
        return usuarioService.createAdmin(usuarioDto.toUsuario()).toUsuarioDto()
    }

    @PutMapping("/{id}")
    fun editaAdmin(@PathVariable("id") id: Long, @RequestBody @Valid usuarioDto: UsuarioDto): UsuarioDto {
        return usuarioService.editaUsuario(id, usuarioDto.toUsuario()).toUsuarioDto()
    }
}