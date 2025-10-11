package com.example.kpz_kanban_lb_back.services

import com.example.kpz_kanban_lb_back.models.KanbanBoard
import com.example.kpz_kanban_lb_back.models.Project
import com.example.kpz_kanban_lb_back.models.ProjectMember
import com.example.kpz_kanban_lb_back.models.User
import com.example.kpz_kanban_lb_back.repositories.KanbanBoardRepository
import com.example.kpz_kanban_lb_back.repositories.ProjectMembersRepository
import com.example.kpz_kanban_lb_back.repositories.ProjectRepository
import com.example.kpz_kanban_lb_back.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class KanbanBoardService(
    private val kanbanBoardRepository: KanbanBoardRepository
) {
    fun getAllKanbanBoards(): List<KanbanBoard> = kanbanBoardRepository.findAll()
    fun saveKanbanBoard(kanbanBoard: KanbanBoard): KanbanBoard = kanbanBoardRepository.save(kanbanBoard)
    fun deleteKanbanBoard(id: Long) = kanbanBoardRepository.deleteById(id)

    fun getById(id: Long): KanbanBoard = kanbanBoardRepository.findKanbanBoardById(id) ?: throw NoSuchElementException("Kanban board not found")
}