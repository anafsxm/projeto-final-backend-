package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.seguranca.filtro

import io.jsonwebtoken.ExpiredJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.seguranca.JwtUtils
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.seguranca.UsuarioServicoLogin
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TokenFiltro : OncePerRequestFilter() {

    @Autowired
    lateinit var jwtUserDetailsService: UsuarioServicoLogin

    @Autowired
    lateinit var jwtTokenUtil: JwtUtils


    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val headerComtoken = request.getHeader("Authorization")
        var nomeUsuario: String? = null
        var token: String? = null
        if (headerComtoken != null && headerComtoken.startsWith("Bearer ")) {
            token = headerComtoken.substring(7)
            try {
                nomeUsuario = jwtTokenUtil.pegaUsuarioDoBodyToken(token)
            } catch (e: IllegalArgumentException) {
                println("Unable to get JWT Token")
            } catch (e: ExpiredJwtException) {
                println("JWT Token has expired")
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String")
        }

        //Once we get the token validate it.
        if (nomeUsuario != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails: UserDetails = jwtUserDetailsService.loadUserByUsername(nomeUsuario)

            // if token is valid configure Spring Security to manually set authentication
            if (jwtTokenUtil.validaToken(token!!, userDetails)) {
                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
                )
                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the Spring Security Configurations successfully.
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }
        chain.doFilter(request, response)
    }
}