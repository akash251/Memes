package com.kamatiaakash.memes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kamatiaakash.memes.presentation.NavGraphs
import com.kamatiaakash.memes.ui.theme.MemesTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemesTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}

