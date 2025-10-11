package com.example.kpz_kanban_lb_back.services

import com.example.kpz_kanban_lb_back.models.Project
import com.example.kpz_kanban_lb_back.models.User
import com.example.kpz_kanban_lb_back.repositories.ProjectRepository
import com.example.kpz_kanban_lb_back.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProjectService(
    private val projectRepository: ProjectRepository
) {
    fun getAllProjects(): List<Project> = projectRepository.findAll()
    fun saveProject(project: Project): Project = projectRepository.save(project)
    fun deleteProject(id: Long) = projectRepository.deleteById(id)

    fun getById(id: Long): Project = projectRepository.findProjectById(id) ?: throw NoSuchElementException("Project not found")
}