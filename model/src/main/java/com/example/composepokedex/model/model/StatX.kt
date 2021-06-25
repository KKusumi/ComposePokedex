package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.StatXResponse

data class StatX(
    val name: String
) {

    companion object {
        fun transform(statXResponse: StatXResponse): StatX {
            return StatX(name = statXResponse.name ?: "")
        }
    }
}