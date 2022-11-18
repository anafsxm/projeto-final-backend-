package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.seguranca

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.usuario.UsuarioService

@Service
class UsuarioServicoLogin(
    val usuarioService: UsuarioService
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = usuarioService.pegaUsuarioPeloEmail(username!!)
            ?: throw UsernameNotFoundException("Username $username not found")

        return User.builder().username(username).roles(user.tipoUsuario.name, "ID|${user.id}").password(user.senha)
            .build()
    }
}