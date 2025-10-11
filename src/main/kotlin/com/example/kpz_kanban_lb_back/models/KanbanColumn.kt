package com.example.kpz_kanban_lb_back.models

import com.example.kpz_kanban_lb_back.dtos.KanbanBoardDto
import com.example.kpz_kanban_lb_back.dtos.KanbanColumnDto
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
@Table(name = "kanban_columns")
class KanbanColumn (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(length = 255, nullable = false)
    var title: String,

    @Column(nullable = false)
    var position: Int,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    val board: KanbanBoard,

    @OneToMany(mappedBy = "column", fetch = FetchType.LAZY)
    val tasks: List<KanbanTask> = emptyList()
) {
    fun toDto(): KanbanColumnDto = KanbanColumnDto(
        this.id?: -1,
        this.title,
        this.position,
        this.createdAt,
        this.updatedAt,
        this.board.id!!
    )
}
