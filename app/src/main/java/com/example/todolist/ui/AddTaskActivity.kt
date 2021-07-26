package com.example.todolist.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.todolist.constants.Constants
import com.example.todolist.data.source.TaskDataSource
import com.example.todolist.databinding.ActivityAddTaskBinding
import com.example.todolist.extensions.format
import com.example.todolist.model.Task
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AddTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(intent.hasExtra(Constants.TASK_ID)){
            val taskId = intent.getIntExtra(Constants.TASK_ID, Constants.DEFAULT_TASK_ID)
            TaskDataSource.findById(taskId)
        }
        setupListeners()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupListeners() {
        binding.addTaskDateTextInputLayout.editText?.setOnClickListener {
            listenerDateTextInputLayout()
        }

        binding.addTaskHourTextInputLayout.editText?.setOnClickListener {
            listenerHourTextInputLayout()
        }
        binding.addTaskCancelButton.setOnClickListener {
            finish()
        }
        binding.addTaskNewTaskButton.setOnClickListener {
            listenerNewTaskButton()
        }
    }

    private fun listenerNewTaskButton() {
        val task = Task(
            binding.addTaskTitleEditText.text.toString(),
            binding.addTaskDescriptionEditTextMultiLine.text.toString(),
            binding.addTaskHourEditText.text.toString(),
            binding.addTaskDateEditText.text.toString()
        )
        TaskDataSource.insertTask(task)
        setResult(Activity.RESULT_OK)
        finish()
    }

    private fun listenerHourTextInputLayout() {
        val timePicker =
            MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build()
        timePicker.addOnPositiveButtonClickListener {
            val minute =
                if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
            val hour = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour
            binding.addTaskHourTextInputLayout.editText?.setText("$hour:$minute")
        }
        timePicker.show(supportFragmentManager, Constants.TIME_PICKER)
    }

    private fun listenerDateTextInputLayout() {
        val datePicker = MaterialDatePicker.Builder.datePicker().build()
        datePicker.addOnPositiveButtonClickListener {
            val timeZone = TimeZone.getDefault()
            val offsetTime = timeZone.getOffset(Date(it).time) * Constants.OPERATOR_TIMEZONE
            binding.addTaskDateTextInputLayout.editText?.setText(Date(it + offsetTime).format())
        }
        datePicker.show(supportFragmentManager, Constants.DATE_PICKER)
    }
}