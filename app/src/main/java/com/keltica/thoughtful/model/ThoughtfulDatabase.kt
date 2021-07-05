package com.keltica.thoughtful.model

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database as Database


@Database(entities = [ContactModel::class], version = 1, exportSchema = true)
abstract class ThoughtfulDatabase: RoomDatabase() {

    abstract fun contactDao(): ContactDao

    //java style singleton
    companion object{
        @Volatile
        private var INSTANCE: ThoughtfulDatabase? = null
        fun getDatabase(context: Context): ThoughtfulDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ThoughtfulDatabase::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}