package com.example.kpz_kanban_lb_back.services

import com.example.kpz_kanban_lb_back.models.KanbanBoard
import com.example.kpz_kanban_lb_back.models.KanbanTask
import com.example.kpz_kanban_lb_back.models.Project
import com.example.kpz_kanban_lb_back.models.ProjectMember
import com.example.kpz_kanban_lb_back.models.User
import com.example.kpz_kanban_lb_back.repositories.KanbanBoardRepository
import com.example.kpz_kanban_lb_back.repositories.KanbanTaskRepository
import com.example.kpz_kanban_lb_back.repositories.ProjectMembersRepository
import com.example.kpz_kanban_lb_back.repositories.ProjectRepository
import com.example.kpz_kanban_lb_back.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class KanbanTaskService(
    private val kanbanTaskRepository: KanbanTaskRepository
) {
    fun getAllKanbanTasks(): List<KanbanTask> = kanbanTaskRepository.findAll()
    fun saveKanbanTask(kanbanTask: KanbanTask): KanbanTask = kanbanTaskRepository.save(kanbanTask)
    fun deleteKanbanTask(id: Long) = kanbanTaskRepository.deleteById(id)

    fun getById(id: Long): KanbanTask = kanbanTaskRepository.findKanbanTaskById(id) ?: throw NoSuchElementException("Kanban task not found")
}