package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.AbilityXResponse

data class AbilityX(
    val name: String?
) {

    companion object {
        fun transform(abilityXResponse: AbilityXResponse): AbilityX {
            return AbilityX(
                name = abilityXResponse.name
            )
        }
        fun getEmpty(): AbilityX {
            return AbilityX(name = "")
        }
    }
}