package com.kamatiaakash.memes.data.mapper

import com.kamatiaakash.memes.data.local.MemeEntity
import com.kamatiaakash.memes.data.remote.dto.MemeDto
import com.kamatiaakash.memes.data.remote.dto.MemesListDto
import com.kamatiaakash.memes.domain.model.Meme
import com.kamatiaakash.memes.domain.model.MemesList


fun MemesListDto.toMemesList(): MemesList {
    return MemesList(
        memes = memes.map { it.toMeme() }
    )
}

fun MemeDto.toMeme(): Meme {
    return Meme(
        author = author,
        title = title,
        postLink = postLink,
        url = url
    )
}

fun Meme.toMemeEntity(): MemeEntity {
    return MemeEntity(
        url = url,
        author = author,
        title = title,
        postLink = postLink
    )
}

fun MemeEntity.toMeme(): Meme {
    return Meme(
        url = url,
        author = author,
        title = title,
        postLink = postLink
    )
}

