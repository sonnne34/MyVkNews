package com.sonne.myvknews.domain

import com.sonne.myvknews.R

data class CommentsPost(
    val id: Int,
    val authorName: String = "Иванищев Понтелеймон",
    val avatarAuthorId: Int = R.drawable.loser,
    val commentText: String = "Комментировал-комментировал да не выкомментировал",
    val publicationDate: String = "16:55"
)
