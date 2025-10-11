package com.example.kpz_kanban_lb_back.repositories

import com.example.kpz_kanban_lb_back.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun findUserById(id: Long): User?
}