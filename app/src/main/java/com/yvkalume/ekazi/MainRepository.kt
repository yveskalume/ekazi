package com.yvkalume.ekazi

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yvkalume.ekazi.api.RemoteJobApi
import com.yvkalume.ekazi.model.Job
import com.yvkalume.ekazi.utils.getJSONFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val JOBS_FILE = "jobs.json"

class JobRepository(context: Context) {
    private val gson = Gson()
    private val file = getJSONFile(context, JOBS_FILE)

    var retrofit = Retrofit.Builder()
        .baseUrl("https://remotive.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val remoteJobApi: RemoteJobApi = retrofit.create(RemoteJobApi::class.java)

    fun fetchLocalJobs() = flow {
            val typeToken = object : TypeToken<List<Job>>() {}.type
            val jobJSON = gson.fromJson<List<Job>>(file, typeToken)
            emit(parseResult(jobJSON))
    }

    fun fetchRemoteJobs() = flow {
        emit(remoteJobApi.getAllJobs())
    }

    private fun parseResult(jobResponse: List<Job>): MutableList<Job> {
        val jobList = mutableListOf<Job>()

        for (job in jobResponse) {
            jobList.add(job)
        }

        return jobList
    }
}