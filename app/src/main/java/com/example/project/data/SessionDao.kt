package com.example.project.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SessionDao {
    @Query("SELECT * FROM session")
    fun getAllData(): List<Session>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(session: Session)

}