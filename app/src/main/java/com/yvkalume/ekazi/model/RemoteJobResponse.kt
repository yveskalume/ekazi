package com.yvkalume.ekazi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteJobResponse(
    @SerializedName("job-count")
    val jobCount: Int = 0,
    val jobs : List<RemoteJob> = emptyList()
) : Parcelable