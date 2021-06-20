package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.AbilityResponse

data class Ability(
    val ability: AbilityX,
    val isHidden: Boolean,
    val slot: Int
) {

    companion object {
        fun transform(abilityResponse: AbilityResponse): Ability {
            return Ability(
                ability = AbilityX.transform(abilityResponse.ability),
                isHidden = abilityResponse.is_hidden,
                slot = abilityResponse.slot
            )
        }
        fun getEmpty(): Ability {
            return Ability(
                ability = AbilityX.getEmpty(),
                isHidden = false,
                slot = 0
            )
        }
    }
}