package com.example.composepokedex.model.response

data class PokemonDetailResponse(
    val abilities: List<AbilityResponse>,
    val base_experience: Int,
    val forms: List<FormResponse>,
    val game_indices: List<GameIndiceResponse>,
    val height: Float,
    val held_items: List<HeldItemResponse>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String?,
    val moves: List<MoveResponse>,
    val name: String?,
    val order: Int,
    val species: SpeciesResponse,
    val sprites: SpritesResponse,
    val stats: List<StatResponse>,
    val types: List<TypeResponse>,
    val weight: Float
)