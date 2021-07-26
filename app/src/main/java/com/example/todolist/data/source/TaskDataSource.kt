package com.example.todolist.data.source

import com.example.todolist.constants.Constants
import com.example.todolist.model.Task

object TaskDataSource {
    private val taskList = arrayListOf<Task>()

    fun getTaskList() = taskList

    fun insertTask(task: Task) {
        taskList.add(task.copy(id = taskList.size + Constants.OPERATOR_TASK_LIST_SIZE))
    }
}