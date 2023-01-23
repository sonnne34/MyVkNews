package com.sonne.myvknews.data.models

import com.google.gson.annotations.SerializedName

data class NewsFeesResponseDto(
    @SerializedName("response") val newsFeedContent: NewsFeedContentDto
)