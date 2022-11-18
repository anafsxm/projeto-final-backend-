package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.base

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {


    @ExceptionHandler(value = [(Exception::class)])
    fun handleException(ex: Exception): ResponseEntity<TrataErro> {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED.value()).body(TrataErro(ex.message))
    }

    @ExceptionHandler(value = [(MethodArgumentNotValidException::class)])
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<MutableMap<String, List<CampoInvalido>>> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
            .body(
                mutableMapOf(
                    Pair(
                        "erros:",
                        ex.bindingResult.fieldErrors.map { CampoInvalido(it.field, it.defaultMessage) })
                )
            )
    }
}