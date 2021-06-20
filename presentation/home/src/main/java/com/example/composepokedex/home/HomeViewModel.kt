package com.example.composepokedex.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composepokedex.domain.GetPokemonListViewUseCase
import com.example.composepokedex.model.model.PokeDexException
import com.example.composepokedex.model.view.PokemonListView
import com.example.composepokedex.model.model.UiState
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

fun getFakeHomeViewModel(): HomeViewModel {
    val getPokemonListViewUseCase = object : GetPokemonListViewUseCase {
        override suspend fun execute(): Result<PokemonListView, PokeDexException> {
            TODO("Not yet implemented")
        }
    }
    return HomeViewModel(getPokemonListViewUseCase = getPokemonListViewUseCase)
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonListViewUseCase: GetPokemonListViewUseCase
) : ViewModel() {

    private val _pokemonListView: MutableLiveData<PokemonListView> = MutableLiveData()
    val pokemonListView get() = _pokemonListView

    private val _uiState: MutableLiveData<UiState> = MutableLiveData()
    val uiState get() = _uiState

    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        _uiState.value = UiState.Loading
        getPokemonListViewUseCase.execute().mapBoth(
            success = {
                _uiState.value = UiState.Loaded
                _pokemonListView.value = it
            },
            failure = {
                Log.d("fetchData failure: ", "${it.message}")
                _uiState.value = UiState.Retry
            }
        )
    }
}
