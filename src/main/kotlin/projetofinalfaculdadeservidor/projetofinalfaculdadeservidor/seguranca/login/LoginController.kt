package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.seguranca.login

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.seguranca.JwtUtils
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.seguranca.UsuarioServicoLogin

@RequestMapping("/v1/login")
@RestController
@CrossOrigin
class LoginController {

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var jwtTokenUtil: JwtUtils

    @Autowired
    lateinit var userDetailsService: UsuarioServicoLogin

    @PostMapping("/logar")
    fun logar(@RequestBody jwtLogar: JwtLogar): ResponseEntity<JwtRespostaToken> {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    jwtLogar.usuario,
                    jwtLogar.senha
                )
            )
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        }
        val userDetails: UserDetails = userDetailsService
            .loadUserByUsername(jwtLogar.usuario)


        val token = jwtTokenUtil.geraTokenParaUsuario(userDetails)

        val role = userDetails.authorities.filter { !it.authority.startsWith("ROLE_ID") }.first().authority
        return ResponseEntity.ok(JwtRespostaToken(token, role))
    }
}