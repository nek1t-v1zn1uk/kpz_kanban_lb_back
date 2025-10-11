package com.example.kpz_kanban_lb_back.models

import com.example.kpz_kanban_lb_back.dtos.KanbanBoardDto
import com.example.kpz_kanban_lb_back.dtos.KanbanTaskDto
import com.example.kpz_kanban_lb_back.dtos.ProjectDto
import com.example.kpz_kanban_lb_back.dtos.UserDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.aspectj.weaver.Position
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDateTime

@Entity
@Table(name = "kanban_tasks")
class KanbanTask (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(length = 255, nullable = false)
    var title: String,

    @Column()
    var description: String? = null,

    @Column(nullable = false)
    var position: Int,

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(nullable = false, columnDefinition = "kanban_task_priorities")
    val priority: KanbanTaskPriorities = KanbanTaskPriorities.MEDIUM,

    @Column(name = "due_date")
    val dueDate: LocalDateTime? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_id", nullable = false)
    val column: KanbanColumn,

) {
    fun toDto(): KanbanTaskDto = KanbanTaskDto(
        this.id?: -1,
        this.title,
        this.description,
        this.position,
        this.priority,
        this.dueDate,
        this.createdAt,
        this.updatedAt,
        this.column.id!!
    )
}

enum class KanbanTaskPriorities {
    HIGH,
    MEDIUM,
    LOW,
    OPTIONAL
}