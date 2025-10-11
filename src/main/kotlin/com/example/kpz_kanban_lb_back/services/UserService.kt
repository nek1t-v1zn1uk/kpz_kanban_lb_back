package com.example.kpz_kanban_lb_back.services

import com.example.kpz_kanban_lb_back.models.User
import com.example.kpz_kanban_lb_back.repositories.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getAllUsers(): List<User> = userRepository.findAll()
    fun saveUser(user: User): User = userRepository.save(user)
    fun deleteUser(id: Long) = userRepository.deleteById(id)
    fun getById(id: Long): User = userRepository.findUserById(id) ?: throw NoSuchElementException("User not found")
}