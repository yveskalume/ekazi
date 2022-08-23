package com.yvkalume.ekazi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yvkalume.ekazi.model.Job
import com.yvkalume.ekazi.model.toJobs
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var _jobList = MutableLiveData<List<Job>>()
    val jobList: LiveData<List<Job>>
        get() = _jobList

    private val repository = JobRepository(application)

    init {
        getJobs()
    }

    private fun getJobs() {
        viewModelScope.launch {
            try {
                repository.fetchRemoteJobs().collect {
                    _jobList.value = it.jobs.toJobs()
                }
            } catch (e: Exception) {
                repository.fetchLocalJobs().collect {
                    _jobList.value = it
                }
            }
        }
    }
}