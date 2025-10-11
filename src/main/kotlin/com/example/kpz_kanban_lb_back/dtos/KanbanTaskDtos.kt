package com.example.kpz_kanban_lb_back.dtos

import com.example.kpz_kanban_lb_back.models.KanbanColumn
import com.example.kpz_kanban_lb_back.models.KanbanTaskPriorities
import jakarta.persistence.Column
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDateTime

data class KanbanTaskDto (
    val id: Long? = null,
    var title: String,
    var description: String? = null,
    var position: Int,
    val priority: KanbanTaskPriorities = KanbanTaskPriorities.MEDIUM,
    val dueDate: LocalDateTime? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val columnId: Long
)