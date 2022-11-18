package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.base

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null
}