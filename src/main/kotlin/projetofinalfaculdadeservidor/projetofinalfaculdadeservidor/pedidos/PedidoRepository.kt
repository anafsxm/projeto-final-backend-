package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.pedidos

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface PedidoRepository : JpaRepository<Pedido, Long> {

    @Query(
        """
           select p2.nome as nome, sum (ip.qt) as quantidade from pedido p join item_pedido ip on ip.pedido_id  = p.id
            join produto p2 on p2.id = ip.produto_id 
            where p.data_pedido >= ?1 and p.data_pedido <= ?2
            group by p2.nome 
        """, nativeQuery = true
    )
    fun getRelatorioPedidos(dataInicio: LocalDateTime, dataFim: LocalDateTime): MutableList<RelatorioAgregado>


    fun findByUsuarioId(id: Long): MutableList<Pedido>
    fun findByStatus(status: PedidoStatus): MutableList<Pedido>
}