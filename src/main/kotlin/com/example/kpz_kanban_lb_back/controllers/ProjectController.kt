package com.example.kpz_kanban_lb_back.controllers

import com.example.kpz_kanban_lb_back.dtos.ProjectDto
import com.example.kpz_kanban_lb_back.models.Project
import com.example.kpz_kanban_lb_back.services.ProjectService
import com.example.kpz_kanban_lb_back.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/project")
class ProjectController(
    private val projectService: ProjectService,
    private val userService: UserService
) {

    @GetMapping("")
    fun getProjects(): ResponseEntity<*> {
        return try{
            val allProjects = projectService.getAllProjects()
            val allProjectsDto = allProjects.map { it.toDto() }

            ResponseEntity.ok(allProjectsDto)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
    @PostMapping("")
    fun addProject(@RequestBody request: ProjectDto): ResponseEntity<*> {
        return try{
            val project = projectService.saveProject(Project(
                id = null,
                title = request.title,
                description = request.description,
                owner = userService.getById(request.ownerId)
            ))

            ResponseEntity.ok("Project added successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
    @PutMapping("")
    fun editProject(@RequestBody request: ProjectDto): ResponseEntity<*> {
        return try{
            val project = projectService.saveProject(Project(
                id = request.id,
                title = request.title,
                description = request.description,
                updatedAt = LocalDateTime.now(),
                owner = userService.getById(request.ownerId)
            ))

            ResponseEntity.ok("Project edited successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
    @DeleteMapping("/{id}")
    fun deleteProject(@PathVariable id: Long): ResponseEntity<*> {
        return try{
            projectService.deleteProject(id)

            ResponseEntity.ok("Project deleted successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
}