package com.sonne.myvknews.data.models

import com.google.gson.annotations.SerializedName

data class LikesDto(
    @SerializedName("count") val count: Int,
    @SerializedName("uer_likes") val userLikes: Int
    )
