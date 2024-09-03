package com.maya.notesapp.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Note::class], version = 2, exportSchema = false)
abstract class RoomDBHelper : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDBHelper? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE note ADD COLUMN title TEXT")
            }
        }

        fun getInstance(context: Context): RoomDBHelper {
            return INSTANCE ?: synchronized(this) {

                val instance = Room
                    .databaseBuilder(context, RoomDBHelper::class.java, "DB")
                    .addMigrations(MIGRATION_1_2)
                    .build()

                INSTANCE = instance
                instance
            }
        }

    }

}