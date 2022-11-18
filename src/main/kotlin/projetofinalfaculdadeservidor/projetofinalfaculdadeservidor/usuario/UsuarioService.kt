package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.usuario

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UsuarioService(
    val usuarioRepository: UsuarioRepository,
    val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun createAdmin(usuario: Usuario): Usuario {
        val usuarioDB = usuarioRepository.findByEmail(usuario.email)
        if (usuarioDB != null) {
            throw Exception("Usuário com email ${usuario.email} já existe.")
        }
        usuario.tipoUsuario = TipoUsuario.ADMIN
        usuario.senha = passwordEncoder.encode(usuario.senha)
        return usuarioRepository.save(usuario)
    }

    fun pegaUsuarioPeloId(id: Long): Usuario {
        return usuarioRepository.findById(id).orElseThrow { Exception("Usuário com id $id não encontrado") }
    }

    fun pegaUsuarioPeloEmail(email: String): Usuario? {
        return usuarioRepository.findByEmail(email)
    }


    @Transactional
    fun editaUsuario(id: Long, toUsuario: Usuario): Usuario {
        val admin = pegaUsuarioPeloId(id)
        admin.email = toUsuario.email
        admin.nome = toUsuario.nome
        admin.telefone = toUsuario.telefone
        return usuarioRepository.save(admin)
    }

    fun createUser(usuario: Usuario): Usuario {
        val usuarioDB = usuarioRepository.findByEmail(usuario.email)
        if (usuarioDB != null) {
            throw Exception("Usuário com email ${usuario.email} já existe.")
        }
        usuario.tipoUsuario = TipoUsuario.USER
        usuario.senha = passwordEncoder.encode(usuario.senha)
        return usuarioRepository.save(usuario)
    }
}