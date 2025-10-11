package com.example.kpz_kanban_lb_back.models

import com.example.kpz_kanban_lb_back.dtos.ProjectDto
import com.example.kpz_kanban_lb_back.dtos.ProjectMemberDto
import com.example.kpz_kanban_lb_back.dtos.UserDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDateTime

@Entity
@Table(
    name = "project_members",
    uniqueConstraints = [
        UniqueConstraint(
            name = "UC_PROJECT_USER",
            columnNames = ["project_id", "user_id"]
        )
    ]

)
class ProjectMember (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(nullable = false, columnDefinition = "project_member_roles")
    val role: ProjectMemberRole = ProjectMemberRole.MEMBER,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    val project: Project,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,
) {
    fun toDto(): ProjectMemberDto = ProjectMemberDto(
        this.id?: -1,
        role = this.role,
        projectId = this.project.id!!,
        userId = this.user.id!!,
    )
}

enum class ProjectMemberRole {
    OWNER,
    ADMIN,
    MEMBER,
    VIEWER
}