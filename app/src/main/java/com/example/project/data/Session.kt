package com.example.project.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "session")
data class Session(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "uid") val uid: Double,
    @ColumnInfo(name = "udesc") val udesc: String,
    )