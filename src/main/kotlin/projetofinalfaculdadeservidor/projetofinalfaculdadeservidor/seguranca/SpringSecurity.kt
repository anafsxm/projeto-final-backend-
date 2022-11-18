package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.seguranca

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.seguranca.filtro.TokenFiltro


@Configuration
class SpringSecurity {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun securityFilterChain(
        httpSecurity: HttpSecurity,
        tokenFiltro: TokenFiltro,
        loginIncorretoHandler: LoginIncorretoHandler
    ): SecurityFilterChain {
        httpSecurity.csrf().disable()
            .authorizeRequests().antMatchers("/v1/login/logar").permitAll()
            .anyRequest().permitAll()
            .and().exceptionHandling().authenticationEntryPoint(loginIncorretoHandler).and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        httpSecurity.addFilterBefore(tokenFiltro, UsernamePasswordAuthenticationFilter::class.java)

        return httpSecurity.build()
    }

    @Bean
    fun setUserdetailsService(
        http: HttpSecurity,
        bCryptPasswordEncoder: BCryptPasswordEncoder,
        @Autowired userDetailService: UsuarioServicoLogin
    ): AuthenticationManager {
        return http.getSharedObject(AuthenticationManagerBuilder::class.java)
            .userDetailsService(userDetailService)
            .passwordEncoder(bCryptPasswordEncoder)
            .and()
            .build()
    }
}