package com.example.composepokedex.pokemon_detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composepokedex.domain.GetPokemonDetailViewUseCase
import com.example.composepokedex.model.model.PokeDexException
import com.example.composepokedex.model.model.UiState
import com.example.composepokedex.model.view.PokemonDetailView
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

fun getFakePokemonDetailViewModel(): PokemonDetailViewModel {
    val getPokemonDetailViewUseCase = object : GetPokemonDetailViewUseCase {
        override suspend fun execute(id: Int): Result<PokemonDetailView, PokeDexException> {
            TODO("Not yet implemented")
        }
    }
    return PokemonDetailViewModel(getPokemonDetailViewUseCase = getPokemonDetailViewUseCase)
}

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailViewUseCase: GetPokemonDetailViewUseCase
) : ViewModel() {

    private val _pokemonDetailView: MutableLiveData<PokemonDetailView> = MutableLiveData()
    val pokemonDetailView get() = _pokemonDetailView

    private val _uiState: MutableLiveData<UiState> = MutableLiveData()
    val uiState get() = _uiState

    fun fetchData(number: Int) = viewModelScope.launch {
        Log.d("fetchData number: ", "$number")
        _uiState.value = UiState.Loading
        getPokemonDetailViewUseCase.execute(number).mapBoth(
            success = {
                Log.d("fetchData success: ", "$it")
                _uiState.value = UiState.Loaded
                _pokemonDetailView.value = it
            },
            failure = {
                Log.d("fetchData failure: ", "${it.message}")
                _uiState.value = UiState.Retry
            }
        )
    }
}