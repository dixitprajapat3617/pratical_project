package com.app.todolist.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.app.todolist.model.User
@Dao
interface UserDao {
    @Insert
    fun inserUser(user: User)
    @Query("select * from user_table")
    fun getAllItem():MutableList<User>
    @Delete
    fun deleterecord(user:User)

}