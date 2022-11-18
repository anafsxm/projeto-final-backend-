package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.relatorios

import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.pedidos.PedidoRepository
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.pedidos.RelatorioAgregado
import java.time.LocalDateTime

@RestController
@CrossOrigin
@RequestMapping("/v1/relatorios")
class RelatorioController(
    val pedidoRepository: PedidoRepository
) {

    @GetMapping("/produtos")
    fun getRelatorioProduto(
        @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) dataInicio: LocalDateTime,
        @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) dataFim: LocalDateTime
    ): MutableList<RelatorioAgregado> {
        return pedidoRepository.getRelatorioPedidos(dataInicio, dataFim)
    }
}