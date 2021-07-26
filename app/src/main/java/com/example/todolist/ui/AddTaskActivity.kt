package com.example.todolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.constants.Constants
import com.example.todolist.databinding.ActivityAddTaskBinding
import com.example.todolist.extensions.format
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
        setupListeners()
    }

    private fun setupListeners() {
        binding.addTaskDateTextInputLayout.editText?.setOnClickListener {
            listenerAddTaskDateTextInputLayout()
        }

        binding.addTaskHourTextInputLayout.editText?.setOnClickListener {
            listenerAddTaskHourTextInputLayout()
        }
        binding.addTaskCancelButton.setOnClickListener {
            finish()
        }
        binding.addTaskNewTaskButton.setOnClickListener {

        }
    }

    private fun listenerAddTaskHourTextInputLayout() {
        val timePicker =
            MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build()
        timePicker.addOnPositiveButtonClickListener {
            binding.addTaskHourTextInputLayout.editText?.setText("${timePicker.hour} ${timePicker.minute}")
        }
        timePicker.show(supportFragmentManager, Constants.TIME_PICKER)
    }

    private fun listenerAddTaskDateTextInputLayout() {
        val datePicker = MaterialDatePicker.Builder.datePicker().build()
        datePicker.addOnPositiveButtonClickListener {
            val timeZone = TimeZone.getDefault()
            val offsetTime = timeZone.getOffset(Date(it).time) * Constants.OPERATOR_TIMEZONE
            binding.addTaskDateTextInputLayout.editText?.setText(Date(it + offsetTime).format())
        }
        datePicker.show(supportFragmentManager, Constants.DATE_PICKER)
    }
}