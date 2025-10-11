package com.example.kpz_kanban_lb_back.models

import com.example.kpz_kanban_lb_back.dtos.UserDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(length = 32)
    var username: String? = null,

    @Column(unique = true, nullable = false)
    var email: String,

    @Column(name = "password_hash", nullable = false)
    var passwordHash: String,

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    val projects: MutableList<Project> = mutableListOf(),
) {
    fun toDto(): UserDto = UserDto(this.id?: -1, this.username, this.email, this.passwordHash)
}