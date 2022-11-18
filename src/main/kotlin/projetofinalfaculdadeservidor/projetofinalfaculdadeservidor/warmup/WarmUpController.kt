package projetofinalfaculdadeservidor.projetofinalfaculdadeservidor.warmup

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class WarmUpController {

    @RequestMapping("/_ah/warmup")
    fun warmup() {
        return
    }
}