package com.example.recyclertask.util

import androidx.lifecycle.LiveData
import com.example.recyclertask.api.TaskApiResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A Retrofit adapter that converts the Call into a LiveData of ApiResponse.
 * @param <R></R>
 * */
class LiveDataCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, LiveData<TaskApiResponse<R>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<R>): LiveData<TaskApiResponse<R>> {
        return object : LiveData<TaskApiResponse<R>>() {
            private var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(TaskApiResponse.create(response))
                        }

                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(TaskApiResponse.create(throwable))
                        }
                    })
                }
            }
        }
    }
}