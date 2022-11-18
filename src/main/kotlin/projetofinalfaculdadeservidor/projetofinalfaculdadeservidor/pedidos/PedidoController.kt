package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.pedidos

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.base.getIDUsuario


@RestController
@CrossOrigin
@RequestMapping("/v1/pedido")
class PedidoController(
    val pedidoService: PedidoService
) {

    @PostMapping("/realiza")
    fun criaPedido(): ResponseEntity<Any> {
        val perfil =
            SecurityContextHolder.getContext().authentication?.authorities?.firstOrNull { it.authority.startsWith("ROLE_ID|") }
                ?: return ResponseEntity("User must be logged", HttpStatus.CONFLICT)

        val userID = perfil.authority.toString().getIDUsuario()
        return ResponseEntity.ok(pedidoService.criaPedido(userID))
    }

    @GetMapping("/{id}")
    fun pegaPedidoPorId(@PathVariable("id") id: Long): Pedido {
        return pedidoService.getPedido(id)
    }

    @PutMapping("/envia/{id}")
    fun atualizaPedidoEnviado(@PathVariable("id") id: Long): Pedido {
        return pedidoService.atualizaPedidoEnviado(id)
    }

    @DeleteMapping("/cancela-pedido/{id}")
    fun cancelaPedido(@PathVariable("id") id: Long) {
        pedidoService.cancelaPedido(id)
    }

    @GetMapping("/meus-pedidos")
    fun getPedidosUsuario(): ResponseEntity<List<Pedido>> {
        val perfil =
            SecurityContextHolder.getContext().authentication?.authorities?.firstOrNull { it.authority.startsWith("ROLE_ID|") }
                ?: return ResponseEntity<List<Pedido>>(HttpStatus.NO_CONTENT)

        val userID = perfil.authority.toString().getIDUsuario()

        var pedidos: List<Pedido>
        if (SecurityContextHolder.getContext().authentication?.authorities?.firstOrNull() { it.authority.toString() == "ROLE_ADMIN" } != null) {
            pedidos = pedidoService.pegaTodosOsPedidos()
        } else {
            pedidos = pedidoService.getPedidosUsuario(userID)
        }

        return ResponseEntity<List<Pedido>>(pedidos, HttpStatus.OK)
    }

    @GetMapping("/pedidos-a-enviar")
    fun getPedidosAEnviar(): MutableList<Pedido> {
        return pedidoService.getPedidosAEnviar()
    }
}