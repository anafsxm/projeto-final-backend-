package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.seguranca

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Function


@Component
class JwtUtils {
    @Value("\${app.jwtSecret}")
    private val secret: String? = null

    fun pegaUsuarioDoBodyToken(token: String?): String {
        return pegaClaimsToken(token) { obj: Claims -> obj.subject }
    }

    fun pegaDataExpiracaoToken(token: String?): Date {
        return pegaClaimsToken(
            token
        ) { obj: Claims -> obj.expiration }
    }

    fun <T> pegaClaimsToken(token: String?, claimsResolver: Function<Claims, T>): T {
        val claims = pegaTodosClaimsdoToken(token)
        return claimsResolver.apply(claims)
    }

    private fun pegaTodosClaimsdoToken(token: String?): Claims {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
    }

    private fun tokenExpirado(token: String): Boolean {
        val expiration = pegaDataExpiracaoToken(token)
        return expiration.before(Date())
    }

    fun geraTokenParaUsuario(userDetails: UserDetails): String {
        val claims: Map<String, Any> = HashMap()
        return geraToken(claims, userDetails.username)
    }

    private fun geraToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + JWT_TEMPO_DUCARAO))
            .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret).compact()
    }

    fun validaToken(token: String, userDetails: UserDetails): Boolean {
        val username = pegaUsuarioDoBodyToken(token)
        return username == userDetails.username && !tokenExpirado(token)
    }

    companion object {
        //salvo em millisegundos == 5 horas
        const val JWT_TEMPO_DUCARAO = 18000000
    }
}