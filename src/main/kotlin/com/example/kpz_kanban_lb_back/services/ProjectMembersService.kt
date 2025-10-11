package com.example.kpz_kanban_lb_back.services

import com.example.kpz_kanban_lb_back.models.Project
import com.example.kpz_kanban_lb_back.models.ProjectMember
import com.example.kpz_kanban_lb_back.models.User
import com.example.kpz_kanban_lb_back.repositories.ProjectMembersRepository
import com.example.kpz_kanban_lb_back.repositories.ProjectRepository
import com.example.kpz_kanban_lb_back.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProjectMembersService(
    private val projectMembersRepository: ProjectMembersRepository
) {
    fun getAllProjectMembers(): List<ProjectMember> = projectMembersRepository.findAll()
    fun saveProjectMember(projectMember: ProjectMember): ProjectMember = projectMembersRepository.save(projectMember)
    fun deleteProjectMember(id: Long) = projectMembersRepository.deleteById(id)
}