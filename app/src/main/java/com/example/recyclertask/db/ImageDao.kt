package com.example.recyclertask.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recyclertask.vo.ImageInfo

/**
 * Interface for database access for Image related operations.
 */
@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(imageInfo: List<ImageInfo>)

    @Query("SELECT * FROM images")
    fun getImageList(): LiveData<List<ImageInfo>>

}