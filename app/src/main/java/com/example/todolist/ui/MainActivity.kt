package com.example.todolist.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.adapter.TaskListAdapter
import com.example.todolist.constants.Constants
import com.example.todolist.data.source.TaskDataSource
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val taskListAdapter by lazy { TaskListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainReyclerView.adapter = taskListAdapter
        updateTaskList()
        setupListeners()
    }

    private fun setupListeners() {
        binding.mainFloatingActionButton.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivityForResult(intent, Constants.CREATE_NEW_TASK)
        }
        taskListAdapter.listenerDeleteTask = {
            TaskDataSource.deleteTask(it)
        }
        taskListAdapter.listenerEditTask = {
            val intent = Intent(this, AddTaskActivity::class.java)
            intent.putExtra(Constants.TASK_ID, it.id)
            startActivityForResult(intent, Constants.CREATE_NEW_TASK)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.CREATE_NEW_TASK && resultCode == Activity.RESULT_OK) {
            updateTaskList()
        }
    }

    private fun updateTaskList() {
        taskListAdapter.submitList(TaskDataSource.getTaskList())
    }
}