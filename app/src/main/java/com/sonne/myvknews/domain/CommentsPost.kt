package com.sonne.myvknews.domain


data class CommentsPost(
    val id: Long,
    val authorName: String,
    val avatarAuthorUrl: String,
    val commentText: String,
    val publicationDate: String,
)
