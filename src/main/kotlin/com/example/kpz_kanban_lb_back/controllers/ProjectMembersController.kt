package com.example.kpz_kanban_lb_back.controllers

import com.example.kpz_kanban_lb_back.dtos.ProjectDto
import com.example.kpz_kanban_lb_back.dtos.ProjectMemberDto
import com.example.kpz_kanban_lb_back.models.Project
import com.example.kpz_kanban_lb_back.models.ProjectMember
import com.example.kpz_kanban_lb_back.services.ProjectMembersService
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
@RequestMapping("/api/project-member")
class ProjectMembersController(
    private val projectMembersService: ProjectMembersService,
    private val userService: UserService,
    private val projectService: ProjectService,
) {

    @GetMapping("")
    fun getProjectMembers(): ResponseEntity<*> {
        return try{
            val allProjectMembers = projectMembersService.getAllProjectMembers()
            val allProjectMembersDto = allProjectMembers.map { it.toDto() }

            ResponseEntity.ok(allProjectMembersDto)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
    @PostMapping("")
    fun addProjectMember(@RequestBody request: ProjectMemberDto): ResponseEntity<*> {
        return try{
            val projectMember = projectMembersService.saveProjectMember(
                ProjectMember(
                    id = null,
                    role = request.role,
                    project = projectService.getById(request.projectId),
                    user = userService.getById(request.userId)
                )
            )

            ResponseEntity.ok("Project member added successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
    @PutMapping("")
    fun editProjectMember(@RequestBody request: ProjectMemberDto): ResponseEntity<*> {
        return try{
            val projectMember = projectMembersService.saveProjectMember(ProjectMember(
                id = request.id,
                role = request.role,
                project = projectService.getById(request.projectId),
                user = userService.getById(request.userId)
            ))

            ResponseEntity.ok("Project member edited successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
    @DeleteMapping("/{id}")
    fun deleteProjectMember(@PathVariable id: Long): ResponseEntity<*> {
        return try{
            projectMembersService.deleteProjectMember(id)

            ResponseEntity.ok("Project member deleted successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
}