package com.example.kpz_kanban_lb_back.models

import com.example.kpz_kanban_lb_back.dtos.KanbanBoardDto
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
import java.time.LocalDateTime

@Entity
@Table(name = "kanban_boards")
class KanbanBoard (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(length = 255, nullable = false)
    var title: String,

    @Column()
    var description: String? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    val project: Project,

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    val columns: List<KanbanColumn> = emptyList(),
) {
    fun toDto(): KanbanBoardDto = KanbanBoardDto(
        this.id?: -1,
        this.title,
        this.description,
        this.createdAt,
        this.updatedAt,
        this.project.id!!
    )
}
