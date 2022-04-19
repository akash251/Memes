package com.kamatiaakash.memes.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamatiaakash.memes.domain.repository.MemeRepository
import com.kamatiaakash.memes.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MemeListViewModel @Inject constructor(
    private val repository: MemeRepository
) : ViewModel() {

    var state by mutableStateOf(MemeListState())

    init {
        getMemeList()
    }

    fun onEvent(event: MemeListEvent){
        when(event){
            is MemeListEvent.Refresh ->{
                getMemeList(true)
            }
        }
    }

    fun getMemeList(fetchFromRemote:Boolean = false){
        viewModelScope.launch {
            repository.getMemeList(fetchFromRemote)
                .collect{ result ->
                    when(result){
                        is Resource.Success ->{
                            result.data?.let{memes->
                                state = state.copy(
                                    memes = memes
                                )
                            }
                        }
                        is Resource.Error ->{
                            state = state.copy(error = result.message.toString())
                        }
                        is Resource.Loading ->{
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }

                }
        }
    }
}