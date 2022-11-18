package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.carrinho

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.base.getIDUsuario

@RestController
@CrossOrigin
@RequestMapping("/v1/carrinho")
class CarrinhoController(
    val carrinhoService: CarrinhoService
) {

    @PutMapping
    fun atualizaCarrinho(@RequestBody itemCarrinhoDto: ItemCarrinhoDto): ResponseEntity<Carrinho> {
        val perfil =
            SecurityContextHolder.getContext().authentication?.authorities?.firstOrNull { it.authority.startsWith("ROLE_ID|") }
                ?: return ResponseEntity<Carrinho>(HttpStatus.NO_CONTENT)

        val userID = perfil.authority.toString().getIDUsuario()
        return ResponseEntity<Carrinho>(carrinhoService.atualizaCarrinho(userID, itemCarrinhoDto), HttpStatus.OK)
    }

    @GetMapping
    fun pegaCarrinho(): ResponseEntity<Carrinho> {
        val perfil =
            SecurityContextHolder.getContext().authentication?.authorities?.firstOrNull { it.authority.startsWith("ROLE_ID|") }
                ?: return ResponseEntity<Carrinho>(HttpStatus.NO_CONTENT)

        val userID = perfil.authority.toString().getIDUsuario()
        return ResponseEntity<Carrinho>(
            carrinhoService.pegaCarrinho(userID), HttpStatus.OK
        )
    }

}