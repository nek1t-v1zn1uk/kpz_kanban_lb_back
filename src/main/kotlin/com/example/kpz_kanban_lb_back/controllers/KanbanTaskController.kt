package com.example.kpz_kanban_lb_back.controllers

import com.example.kpz_kanban_lb_back.dtos.KanbanBoardDto
import com.example.kpz_kanban_lb_back.dtos.KanbanTaskDto
import com.example.kpz_kanban_lb_back.dtos.ProjectDto
import com.example.kpz_kanban_lb_back.dtos.ProjectMemberDto
import com.example.kpz_kanban_lb_back.models.KanbanBoard
import com.example.kpz_kanban_lb_back.models.KanbanTask
import com.example.kpz_kanban_lb_back.models.Project
import com.example.kpz_kanban_lb_back.models.ProjectMember
import com.example.kpz_kanban_lb_back.services.KanbanBoardService
import com.example.kpz_kanban_lb_back.services.KanbanColumnService
import com.example.kpz_kanban_lb_back.services.KanbanTaskService
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
@RequestMapping("/api/kanban-task")
class KanbanTaskController(
    private val kanbanTaskService: KanbanTaskService,
    private val kanbanColumnService: KanbanColumnService,
    private val kanbanBoardService: KanbanBoardService,
    private val projectMembersService: ProjectMembersService,
    private val userService: UserService,
    private val projectService: ProjectService,
) {

    @GetMapping("")
    fun getKanbanTasks(): ResponseEntity<*> {
        return try{
            val allKanbanTasks = kanbanTaskService.getAllKanbanTasks()
            val allKanbanTasksDto = allKanbanTasks.map { it.toDto() }

            ResponseEntity.ok(allKanbanTasksDto)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
    @PostMapping("")
    fun addKanbanTask(@RequestBody request: KanbanTaskDto): ResponseEntity<*> {
        return try{
            val kanbanTask = kanbanTaskService.saveKanbanTask(
                KanbanTask(
                    id = null,
                    title = request.title,
                    description = request.description,
                    position = request.position,
                    priority = request.priority,
                    dueDate = request.dueDate,
                    updatedAt = LocalDateTime.now(),
                    column = kanbanColumnService.getById(request.columnId)
                )
            )

            ResponseEntity.ok("Kanban task added successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
    @PutMapping("")
    fun editKanbanTask(@RequestBody request: KanbanTaskDto): ResponseEntity<*> {
        return try{
            val kanbanTask = kanbanTaskService.saveKanbanTask(
                KanbanTask(
                    id = request.id,
                    title = request.title,
                    description = request.description,
                    position = request.position,
                    priority = request.priority,
                    dueDate = request.dueDate,
                    updatedAt = LocalDateTime.now(),
                    column = kanbanColumnService.getById(request.columnId)
                )
            )

            ResponseEntity.ok("Kanban task edited successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
    @DeleteMapping("/{id}")
    fun deleteKanbanTask(@PathVariable id: Long): ResponseEntity<*> {
        return try{
            kanbanTaskService.deleteKanbanTask(id)

            ResponseEntity.ok("Kanban task deleted successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
}