package com.example.project.data


import androidx.room.Dao
import androidx.room.Insert

@Dao
interface SessionDao {

    @Insert()
    fun insert(session: Session)

}