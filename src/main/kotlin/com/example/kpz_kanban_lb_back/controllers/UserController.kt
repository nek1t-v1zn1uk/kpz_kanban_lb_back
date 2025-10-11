package com.example.kpz_kanban_lb_back.controllers

import com.example.kpz_kanban_lb_back.dtos.UserDto
import com.example.kpz_kanban_lb_back.models.User
import com.example.kpz_kanban_lb_back.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService
) {

    @GetMapping("")
    fun getUsers(): ResponseEntity<*> {
        return try{
            val allUsers = userService.getAllUsers()
            val allUsersDto = allUsers.map { it.toDto() }

            ResponseEntity.ok(allUsersDto)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
    @PostMapping("")
    fun addUser(@RequestBody request: UserDto): ResponseEntity<*> {
        return try{
            val user = userService.saveUser(User(null, request.username, request.email, request.passwordHash))

            ResponseEntity.ok("User added successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
    @PutMapping("")
    fun editUser(@RequestBody request: UserDto): ResponseEntity<*> {
        return try{
            val user = userService.saveUser(User(request.id, request.username, request.email, request.passwordHash))

            ResponseEntity.ok("User edited successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<*> {
        return try{
            userService.deleteUser(id)

            ResponseEntity.ok("User deleted successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
}