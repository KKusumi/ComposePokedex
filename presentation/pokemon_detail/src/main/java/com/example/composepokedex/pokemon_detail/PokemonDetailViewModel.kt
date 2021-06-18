package com.example.composepokedex.pokemon_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composepokedex.domain.GetPokemonDetailViewUseCase
import com.example.composepokedex.model.model.UiState
import com.example.composepokedex.model.view.PokemonDetailView
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailViewUseCase: GetPokemonDetailViewUseCase
) : ViewModel() {

    private val _pokemonDetailView: MutableLiveData<PokemonDetailView> = MutableLiveData()
    val pokemonDetailView get() = _pokemonDetailView

    private val _uiState: MutableLiveData<UiState> = MutableLiveData()
    val uiState get() = _uiState

    fun fetchData(number: Int) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        getPokemonDetailViewUseCase.execute(number).mapBoth(
            success = {
                _uiState.value = UiState.Loaded
                _pokemonDetailView.value = it
            },
            failure = {
                _uiState.value = UiState.Retry
            }
        )
    }
}