package com.example.recyclertask.api

import androidx.lifecycle.LiveData
import com.example.recyclertask.vo.ImageInfo

import retrofit2.http.GET
/**
 * REST API access points
 */
interface TaskApiService {
    /**
     * Get a list of all images by using the /list endpoint.
     */
    @GET("list")
    fun getAllImages(): LiveData<TaskApiResponse<List<ImageInfo>>>
}