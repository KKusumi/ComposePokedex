package com.example.composepokedex.model.response

data class AbilityResponse(
    val ability: AbilityXResponse,
    val is_hidden: Boolean,
    val slot: Int
)