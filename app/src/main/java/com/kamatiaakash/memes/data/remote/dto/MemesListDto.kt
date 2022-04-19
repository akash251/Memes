package com.kamatiaakash.memes.data.remote.dto

data class MemesListDto(
    val count: Int,
    val memes: List<MemeDto>
)