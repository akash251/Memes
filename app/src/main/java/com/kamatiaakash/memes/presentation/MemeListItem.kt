package com.kamatiaakash.memes.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kamatiaakash.memes.domain.model.Meme

@Composable
fun MemeListItem(meme: Meme) {

    androidx.compose.material.Surface(color = Color.Black) {
        Card(
            modifier = Modifier
                .padding(horizontal = 3.dp, vertical = 5.dp)
                .fillMaxWidth(),
            backgroundColor = Color.DarkGray,
            shape = RoundedCornerShape(10.dp)


        ) {

            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Top) {

                Text(
                    text = meme.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally)
                )
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(meme.url)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "~${meme.author}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.End)
                )

            }

        }
    }


}