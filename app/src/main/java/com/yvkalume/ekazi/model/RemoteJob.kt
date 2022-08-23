package com.yvkalume.ekazi.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteJob(
    val candidate_required_location: String = "",
    val category: String = "",
    val company_logo: String = "",
    val company_name: String = "",
    val description: String = "",
    val id: Int = 0,
    val job_type: String = "",
    val publication_date: String = "",
    val salary: String = "unknow",
    val tags: List<String> = emptyList(),
    val title: String = "",
    val url: String = ""
) : Parcelable {
    fun toJob(): Job {
        return Job(
            id = id.toString(),
            position = title,
            location = candidate_required_location,
            salary = salary,
            company = company_name,
            companyLogo = company_logo,
            description = description,
            url = url,
            workingHours = if (job_type == "full_time") Job.WorkingHours.FULLTIME else Job.WorkingHours.PART_TIME
        )
    }
}

fun List<RemoteJob>.toJobs() : List<Job> = this.map { it.toJob() }

