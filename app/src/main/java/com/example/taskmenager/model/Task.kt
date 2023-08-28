package com.example.taskmenager.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val title: String,
    val description: String
) : Serializable