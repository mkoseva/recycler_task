package com.example.recyclertask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recyclertask.vo.ImageInfo

/**
 * Main database description
 */
@Database(
    entities = [
        ImageInfo::class],
    version = 1,
    exportSchema = false
)
abstract class TaskApiDatabase : RoomDatabase() {

    abstract fun imageDao(): ImageDao
}