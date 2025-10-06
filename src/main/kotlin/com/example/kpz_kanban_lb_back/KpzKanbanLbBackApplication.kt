package com.example.kpz_kanban_lb_back

import io.github.cdimascio.dotenv.dotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KpzKanbanLbBackApplication

fun main(args: Array<String>) {
    try {
        dotenv {
            directory = "./"
            filename = ".env"
        }.entries().forEach { entry ->
            System.setProperty(entry.key, entry.value)
        }
    } catch (ex: Exception) {}
    runApplication<KpzKanbanLbBackApplication>(*args)
}
