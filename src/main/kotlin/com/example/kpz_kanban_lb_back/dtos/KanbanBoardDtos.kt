package com.example.kpz_kanban_lb_back.dtos

import java.time.LocalDateTime

data class KanbanBoardDto (
    val id: Long? = null,
    var title: String,
    var description: String? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val projectId: Long
)