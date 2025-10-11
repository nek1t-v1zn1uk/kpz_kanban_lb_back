package com.example.kpz_kanban_lb_back.dtos

import com.example.kpz_kanban_lb_back.models.KanbanBoard
import jakarta.persistence.Column
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

data class KanbanColumnDto (
    val id: Long? = null,
    var title: String,
    var position: Int,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val boardId: Long
)