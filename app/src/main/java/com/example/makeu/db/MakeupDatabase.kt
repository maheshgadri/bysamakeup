package com.example.makeu.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.makeu.model.MakeupItem

@Database(entities = [MakeupItem::class], version = 1)

@TypeConverters(Convertors::class)
abstract class MakeupDatabase : RoomDatabase() {

    abstract fun getItemDao():ItemDao

    companion object{
        @Volatile
        private var instance: MakeupDatabase?=null
        private val LOCK = Any()
        fun getDatabase(context: Context):MakeupDatabase{

            if (instance==null){

                synchronized(this){

                    instance= Room.databaseBuilder(context,MakeupDatabase::class.java,"makupdb.db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
return instance!!
        }
//
//        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
//
//            instance ?: createDatabase(context).also { instance = it }
//        }
//
//        private fun createDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                MakeupDatabase::class.java,
//                "makeup_db.db"
//            ).build()
    }
}


