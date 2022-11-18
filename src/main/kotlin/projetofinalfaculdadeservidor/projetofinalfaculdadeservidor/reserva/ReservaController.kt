package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.reserva

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.base.getIDUsuario
import projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.restaurante.RestauranteReservaDTO

@RestController
@CrossOrigin
@RequestMapping("/v1/reserva")
class ReservaController(
    val reservaService: ReservaService
) {

    @PostMapping
    fun criaReserva(@RequestBody restauranteReservaDTO: RestauranteReservaDTO): ResponseEntity<Reserva> {
        val perfil =
            SecurityContextHolder.getContext().authentication?.authorities?.firstOrNull { it.authority.startsWith("ROLE_ID|") }
                ?: return ResponseEntity<Reserva>(HttpStatus.BAD_REQUEST)

        val userID = perfil.authority.toString().getIDUsuario()
        return ResponseEntity<Reserva>(reservaService.criaReserva(restauranteReservaDTO, userID), HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun editaReserva(@PathVariable("id") id: Long, @RequestBody restauranteReservaDTO: RestauranteReservaDTO): Reserva {
        return reservaService.editaReserva(id, restauranteReservaDTO)
    }

    @DeleteMapping("/{id}")
    fun cancelaReserva(@PathVariable("id") id: Long) {
        reservaService.deletaReserva(id)
    }

    @GetMapping("/{id}")
    fun pegaReserva(@PathVariable("id") id: Long): MinhaReservaDto {
        val reserva = reservaService.pegaReserva(id)
        return MinhaReservaDto(
            reserva.mesa.restaurante.nome,
            reserva.diaReservado,
            reserva.qtPessoas,
            reserva.id!!,
            reserva.mesa.id!!
        )
    }


    @GetMapping("/minhas-reservas")
    fun getMinhasReservas(): ResponseEntity<List<MinhaReservaDto>> {
        val perfil =
            SecurityContextHolder.getContext().authentication?.authorities?.firstOrNull { it.authority.startsWith("ROLE_ID|") }
                ?: return ResponseEntity<List<MinhaReservaDto>>(HttpStatus.NO_CONTENT)

        var reservas: List<Reserva>
        if (SecurityContextHolder.getContext().authentication?.authorities?.firstOrNull() { it.authority.toString() == "ROLE_ADMIN" } != null) {
            reservas = reservaService.getAllReservas()
        } else {
            val userID = perfil.authority.toString().getIDUsuario()
            reservas = reservaService.getMinhasReservas(userID)
        }

        val retorno = reservas.map {
            MinhaReservaDto(it.mesa.restaurante.nome, it.diaReservado, it.qtPessoas, it.id!!, it.mesa.id!!)
        }
        return ResponseEntity<List<MinhaReservaDto>>(retorno, HttpStatus.OK)
    }
}