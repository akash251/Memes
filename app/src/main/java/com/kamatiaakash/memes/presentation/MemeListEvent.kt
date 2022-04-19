package com.kamatiaakash.memes.presentation

sealed class MemeListEvent {
    object Refresh:MemeListEvent()
}