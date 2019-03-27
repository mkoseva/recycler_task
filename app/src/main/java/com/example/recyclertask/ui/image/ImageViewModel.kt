package com.example.recyclertask.ui.image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyclertask.db.ImageDao
import com.example.recyclertask.repository.ImageRepository
import com.example.recyclertask.vo.ImageInfo
import com.example.recyclertask.vo.Resource
import javax.inject.Inject

/**
 * The ViewModel for [ImageFragment].
 */
class ImageViewModel  @Inject constructor(repository: ImageRepository) : ViewModel() {
    private var data: LiveData<Resource<List<ImageInfo>>>? = null
    private var imageDao : ImageDao? = null



    private val imageListObservable = repository.loadImageObject()
    val images: LiveData<Resource<List<ImageInfo>>> = repository.loadImageObject()


    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
 //   fun getImageListObservable(): LiveData<List<ImageInfo>> {
 //       return repository.
 ///   }

    fun init() {
        if (this.data != null) {
            // ViewModel is created per Fragment so
            // we know the userId won't change
            return
        }
        data = imageListObservable
    }

    fun getImageInfo(): LiveData<Resource<List<ImageInfo>>>? {
        return data
    }


}
