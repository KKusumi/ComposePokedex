package com.example.composepokedex.pokemon_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composepokedex.domain.GetEvolutionChainUseCase
import com.example.composepokedex.domain.GetPokemonDetailViewUseCase
import com.example.composepokedex.domain.GetPokemonSpeciesUseCase
import com.example.composepokedex.model.model.EvolutionChain
import com.example.composepokedex.model.model.PokeDexException
import com.example.composepokedex.model.model.PokemonSpecies
import com.example.composepokedex.model.model.UiState
import com.example.composepokedex.model.view.PokemonDetailView
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

fun getFakePokemonDetailViewModel(): PokemonDetailViewModel {
    val getPokemonDetailViewUseCase = object : GetPokemonDetailViewUseCase {
        override suspend fun execute(id: Int): Result<PokemonDetailView, PokeDexException> {
            TODO("Not yet implemented")
        }
    }
    val getPokemonSpeciesUseCase = object : GetPokemonSpeciesUseCase {
        override suspend fun execute(id: Int): Result<PokemonSpecies, PokeDexException> {
            TODO("Not yet implemented")
        }
    }
    val getEvolutionChainUseCase = object : GetEvolutionChainUseCase {
        override suspend fun execute(id: Int): Result<EvolutionChain, PokeDexException> {
            TODO("Not yet implemented")
        }
    }
    return PokemonDetailViewModel(
        getPokemonDetailViewUseCase = getPokemonDetailViewUseCase,
        getPokemonSpeciesUseCase = getPokemonSpeciesUseCase,
        getEvolutionChainUseCase = getEvolutionChainUseCase
    )
}

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailViewUseCase: GetPokemonDetailViewUseCase,
    private val getPokemonSpeciesUseCase: GetPokemonSpeciesUseCase,
    private val getEvolutionChainUseCase: GetEvolutionChainUseCase
) : ViewModel() {

    private val _pokemonDetailView: MutableLiveData<PokemonDetailView> = MutableLiveData()
    val pokemonDetailView: LiveData<PokemonDetailView> get() = _pokemonDetailView

    private val _uiState: MutableLiveData<UiState> = MutableLiveData()
    val uiState: LiveData<UiState> get() = _uiState

    private var _evolutionChain: MutableLiveData<EvolutionChain> = MutableLiveData()
    val evolutionChain: LiveData<EvolutionChain> get() = _evolutionChain


    fun fetchPokemonDetail(number: Int) = viewModelScope.launch {
        Timber.d("fetchPokemonDetail number: $number")
        _uiState.value = UiState.Loading
        getPokemonDetailViewUseCase.execute(number).mapBoth(
            success = {
                Timber.d("fetchPokemonDetail success: $it")
                _uiState.value = UiState.Loaded
                _pokemonDetailView.value = it
            },
            failure = {
                Timber.e("fetchPokemonDetail failure: $it")
                _uiState.value = UiState.Retry
            }
        )
    }

    fun fetchEvolutionData(number: Int) = viewModelScope.launch {
        getPokemonSpeciesUseCase.execute(number).mapBoth(
            success = {
                Timber.d("fetchPokemonSpecies success: $it")
                fetchEvolutionChain(it.evolutionChainId)
            },
            failure = {
                Timber.e("fetchPokemonSpecies failure: $it")
            }
        )
    }

    private fun fetchEvolutionChain(id: Int) = viewModelScope.launch {
        getEvolutionChainUseCase.execute(id).mapBoth(
            success = {
                Timber.d("fetchEvolutionChain success: $it")
                _evolutionChain.value = it
            },
            failure = {
                Timber.e("fetchEvolutionChain failure: $it")
            }
        )
    }
}