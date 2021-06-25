package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.StatResponse

data class Stat(
    val baseStat: Int,
    val stat: StatX
) {

    companion object {
        fun transform(statResponse: StatResponse): Stat {
            return Stat(
                baseStat = statResponse.base_stat,
                stat = StatX.transform(statResponse.stat)
            )
        }
    }
}