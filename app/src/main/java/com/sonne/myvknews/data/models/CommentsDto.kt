package com.sonne.myvknews.data.models

import com.google.gson.annotations.SerializedName

data class CommentsDto(
    @SerializedName("count")
    val count: Int
)
