package com.example.kpz_kanban_lb_back.dtos

data class UserDto (
    val id: Long? = null,
    var username: String? = null,
    var email: String,
    var passwordHash: String,
)