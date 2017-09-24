package org.rex.lottery.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * Created by RexSong on 2017/9/24.
 */
@RestController
class GoController {

    @GetMapping("/go")
    fun go() = "lottery running"

    @GetMapping("/{name}/hi")
    fun sayHello(@PathVariable name:String)
             = "hello" + name
}