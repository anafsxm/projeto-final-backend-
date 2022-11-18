package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.base

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonHibernateConfig {

    @Bean
    fun jacksonHibernate(): Hibernate5Module {
        return Hibernate5Module()
    }
}