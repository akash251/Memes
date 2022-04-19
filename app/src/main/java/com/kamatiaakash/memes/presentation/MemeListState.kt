package com.kamatiaakash.memes.presentation

import com.kamatiaakash.memes.domain.model.Meme

data class MemeListState(
    val memes:List<Meme> = emptyList(),
    val isLoading:Boolean = false,
    val isRefreshing:Boolean = false,
    val error:String = ""
)