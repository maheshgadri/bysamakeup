package com.example.makeu.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.makeu.model.MakeupItem

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun additems(items: MakeupItem)

    @Query("SELECT * FROM makeup")
    fun getItems(): LiveData<List<MakeupItem>>

    @Query("DELETE FROM makeup")
    fun deleteAllRecords()
}