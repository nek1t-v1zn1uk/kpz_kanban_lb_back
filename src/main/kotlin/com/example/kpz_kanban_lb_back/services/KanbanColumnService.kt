package com.example.kpz_kanban_lb_back.services

import com.example.kpz_kanban_lb_back.models.KanbanBoard
import com.example.kpz_kanban_lb_back.models.KanbanColumn
import com.example.kpz_kanban_lb_back.models.Project
import com.example.kpz_kanban_lb_back.models.ProjectMember
import com.example.kpz_kanban_lb_back.models.User
import com.example.kpz_kanban_lb_back.repositories.KanbanBoardRepository
import com.example.kpz_kanban_lb_back.repositories.KanbanColumnRepository
import com.example.kpz_kanban_lb_back.repositories.ProjectMembersRepository
import com.example.kpz_kanban_lb_back.repositories.ProjectRepository
import com.example.kpz_kanban_lb_back.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class KanbanColumnService(
    private val kanbanColumnRepository: KanbanColumnRepository
) {
    fun getAllKanbanColumns(): List<KanbanColumn> = kanbanColumnRepository.findAll()
    fun saveKanbanColumn(kanbanColumn: KanbanColumn): KanbanColumn = kanbanColumnRepository.save(kanbanColumn)
    fun deleteKanbanColumn(id: Long) = kanbanColumnRepository.deleteById(id)

    fun getById(id: Long) = kanbanColumnRepository.findKanbanColumnById(id) ?: throw NoSuchElementException("Kanban column not found")
}