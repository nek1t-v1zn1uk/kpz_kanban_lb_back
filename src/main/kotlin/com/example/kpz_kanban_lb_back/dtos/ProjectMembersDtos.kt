package com.example.kpz_kanban_lb_back.dtos

import com.example.kpz_kanban_lb_back.models.Project
import com.example.kpz_kanban_lb_back.models.ProjectMemberRole
import com.example.kpz_kanban_lb_back.models.User
import jakarta.persistence.Column
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

data class ProjectMemberDto (
    val id: Long? = null,
    val role: ProjectMemberRole = ProjectMemberRole.MEMBER,
    val projectId: Long,
    val userId: Long,
)