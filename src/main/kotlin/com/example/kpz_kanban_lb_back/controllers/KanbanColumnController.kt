package com.example.kpz_kanban_lb_back.controllers

import com.example.kpz_kanban_lb_back.dtos.KanbanBoardDto
import com.example.kpz_kanban_lb_back.dtos.KanbanColumnDto
import com.example.kpz_kanban_lb_back.dtos.ProjectDto
import com.example.kpz_kanban_lb_back.dtos.ProjectMemberDto
import com.example.kpz_kanban_lb_back.models.KanbanBoard
import com.example.kpz_kanban_lb_back.models.KanbanColumn
import com.example.kpz_kanban_lb_back.models.Project
import com.example.kpz_kanban_lb_back.models.ProjectMember
import com.example.kpz_kanban_lb_back.services.KanbanBoardService
import com.example.kpz_kanban_lb_back.services.KanbanColumnService
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
@RequestMapping("/api/kanban-column")
class KanbanColumnController(
    private val kanbanColumnService: KanbanColumnService,
    private val kanbanBoardService: KanbanBoardService,
    private val projectMembersService: ProjectMembersService,
    private val userService: UserService,
    private val projectService: ProjectService,
) {

    @GetMapping("")
    fun getKanbanColumns(): ResponseEntity<*> {
        return try{
            val allKanbanColumns = kanbanColumnService.getAllKanbanColumns()
            val allKanbanColumnsDto = allKanbanColumns.map { it.toDto() }

            ResponseEntity.ok(allKanbanColumnsDto)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
    @PostMapping("")
    fun addKanbanColumn(@RequestBody request: KanbanColumnDto): ResponseEntity<*> {
        return try{
            val kanbanColumn = kanbanColumnService.saveKanbanColumn(
                KanbanColumn(
                    id = null,
                    title = request.title,
                    position = request.position,
                    updatedAt = LocalDateTime.now(),
                    board = kanbanBoardService.getById(request.boardId)
                )
            )

            ResponseEntity.ok("Kanban column added successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
    @PutMapping("")
    fun editKanbanColumn(@RequestBody request: KanbanColumnDto): ResponseEntity<*> {
        return try{
            val kanbanColumn = kanbanColumnService.saveKanbanColumn(
                KanbanColumn(
                    id = request.id,
                    title = request.title,
                    position = request.position,
                    updatedAt = LocalDateTime.now(),
                    board = kanbanBoardService.getById(request.boardId)
                )
            )

            ResponseEntity.ok("Kanban column edited successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
    @DeleteMapping("/{id}")
    fun deleteKanbanColumn(@PathVariable id: Long): ResponseEntity<*> {
        return try{
            kanbanColumnService.deleteKanbanColumn(id)

            ResponseEntity.ok("Kanban column deleted successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:\n$e")
        }
    }
}