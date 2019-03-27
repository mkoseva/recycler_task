package com.example.recyclertask.repository

import androidx.lifecycle.LiveData
import com.example.recyclertask.AppExecutors
import com.example.recyclertask.api.TaskApiService
import com.example.recyclertask.db.ImageDao
import com.example.recyclertask.vo.ImageInfo
import com.example.recyclertask.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository that handles Category objects.
 */
@Singleton
class ImageRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val imageDao: ImageDao,
    private val taskApiService: TaskApiService
) {

    fun loadImageObject(): LiveData<Resource<List<ImageInfo>>>{
        return object : NetworkBoundResource<List<ImageInfo>, List<ImageInfo>>(appExecutors) {
            override fun saveCallResult(item: List<ImageInfo>) {
                imageDao.insert(item)
            }

            override fun shouldFetch(data: List<ImageInfo>?): Boolean = data == null

            override fun loadFromDb() = imageDao.getImageList()

            override fun createCall() = taskApiService.getAllImages()
        }.asLiveData()
    }


}