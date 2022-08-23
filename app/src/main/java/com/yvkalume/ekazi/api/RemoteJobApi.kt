package com.yvkalume.ekazi.api

import com.yvkalume.ekazi.model.RemoteJobResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface RemoteJobApi {
    @GET("remote-jobs")
    suspend fun getAllJobs() : RemoteJobResponse
}