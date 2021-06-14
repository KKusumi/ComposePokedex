package com.example.composepokedex.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composepokedex.domain.GetPokemonListViewUseCase
import com.example.composepokedex.model.model.PokemonListView
import com.example.composepokedex.model.model.UiState
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonListViewUseCase: GetPokemonListViewUseCase
) : ViewModel() {

    private val _pokemonListView: MutableLiveData<PokemonListView> = MutableLiveData()
    val pokemonListView get() = _pokemonListView

    private val _uiState: MutableLiveData<UiState> = MutableLiveData()
    val uiState get() = _uiState

    fun fetchData() = viewModelScope.launch {
        _uiState.value = UiState.Loading
        getPokemonListViewUseCase.execute().mapBoth(
            success = {
                _uiState.value = UiState.Loaded
                _pokemonListView.value = it
            },
            failure = {
                _uiState.value = UiState.Retry
            }
        )
    }
}