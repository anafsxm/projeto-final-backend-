package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.usuario

import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@CrossOrigin
@RequestMapping("/v1/user")
class UserController(
    val usuarioService: UsuarioService
) {

    @PostMapping
    fun createAdmin(@RequestBody @Valid usuarioDto: UsuarioDto): UsuarioDto {
        return usuarioService.createUser(usuarioDto.toUsuario()).toUsuarioDto()
    }

    @PutMapping("/{id}")
    fun editaUser(@PathVariable("id") id: Long, @RequestBody @Valid usuarioDto: UsuarioDto): UsuarioDto {
        return usuarioService.editaUsuario(id, usuarioDto.toUsuario()).toUsuarioDto()
    }
}