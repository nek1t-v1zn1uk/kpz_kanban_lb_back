package com.example.kpz_kanban_lb_back.controllers

import com.example.kpz_kanban_lb_back.dtos.KanbanBoardDto
import com.example.kpz_kanban_lb_back.dtos.ProjectDto
import com.example.kpz_kanban_lb_back.dtos.ProjectMemberDto
import com.example.kpz_kanban_lb_back.models.KanbanBoard
import com.example.kpz_kanban_lb_back.models.Project
import com.example.kpz_kanban_lb_back.models.ProjectMember
import com.example.kpz_kanban_lb_back.services.KanbanBoardService
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
@RequestMapping("/api/kanban-board")
class KanbanBoardController(
    private val kanbanBoardService: KanbanBoardService,
    private val projectMembersService: ProjectMembersService,
    private val userService: UserService,
    private val projectService: ProjectService,
) {

    @GetMapping("")
    fun getKanbanBoards(): ResponseEntity<*> {
        return try{
            val allKanbanBoards = kanbanBoardService.getAllKanbanBoards()
            val allKanbanBoardsDto = allKanbanBoards.map { it.toDto() }

            ResponseEntity.ok(allKanbanBoardsDto)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
    @PostMapping("")
    fun addKanbanBoard(@RequestBody request: KanbanBoardDto): ResponseEntity<*> {
        return try{
            val kanbanBoard = kanbanBoardService.saveKanbanBoard(
                KanbanBoard(
                    id = null,
                    title = request.title,
                    description = request.description,
                    updatedAt = LocalDateTime.now(),
                    project = projectService.getById(request.projectId)
                )
            )

            ResponseEntity.ok("Kanban board added successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
    @PutMapping("")
    fun editKanbanBoard(@RequestBody request: KanbanBoardDto): ResponseEntity<*> {
        return try{
            val kanbanBoard = kanbanBoardService.saveKanbanBoard(
                KanbanBoard(
                    id = request.id,
                    title = request.title,
                    description = request.description,
                    updatedAt = LocalDateTime.now(),
                    project = projectService.getById(request.projectId)
                )
            )

            ResponseEntity.ok("Kanban board edited successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
    @DeleteMapping("/{id}")
    fun deleteKanbanBoard(@PathVariable id: Long): ResponseEntity<*> {
        return try{
            kanbanBoardService.deleteKanbanBoard(id)

            ResponseEntity.ok("Kanban board deleted successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
}