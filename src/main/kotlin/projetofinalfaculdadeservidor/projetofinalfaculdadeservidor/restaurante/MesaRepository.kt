package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.restaurante

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MesaRepository : JpaRepository<Mesa, Long>