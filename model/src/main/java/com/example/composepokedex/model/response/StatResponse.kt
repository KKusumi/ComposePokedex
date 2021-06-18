package com.example.composepokedex.model.response

data class StatResponse(
    val base_stat: Int,
    val effort: Int,
    val stat: StatXResponse
)