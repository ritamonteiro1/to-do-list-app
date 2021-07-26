package com.example.todolist.extensions

import com.example.todolist.constants.Constants
import java.text.SimpleDateFormat
import java.util.*


fun Date.format(): String {
    val localeBr = Locale("pt", "BR")
    return SimpleDateFormat(Constants.DATE_FORMAT, localeBr).format(this)
}