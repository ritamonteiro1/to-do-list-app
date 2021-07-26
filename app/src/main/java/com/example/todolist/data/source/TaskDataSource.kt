package com.example.todolist.data.source

import com.example.todolist.constants.Constants
import com.example.todolist.model.Task

object TaskDataSource {
    private val taskList = arrayListOf<Task>()

    fun getTaskList() = taskList.toList()

    fun insertTask(task: Task) {
        if (task.id == Constants.NEW_TASK_ID) {
            taskList.add(task.copy(id = taskList.size + Constants.OPERATOR_TASK_LIST_SIZE))
        } else {
            taskList.remove(task)
            taskList.add(task)
        }
    }

    fun findById(taskId: Int) = taskList.find {
        it.id == taskId
    }
}