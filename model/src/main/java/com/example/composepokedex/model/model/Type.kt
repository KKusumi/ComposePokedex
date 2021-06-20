package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.TypeResponse

data class Type(
    val slot: Int,
    val type: TypeX
) {

    companion object {
        fun transform(typeResponse: TypeResponse): Type {
            return Type(
                slot = typeResponse.slot,
                type = TypeX.transform(typeResponse.type)
            )
        }
        fun getEmpty(): Type {
            return Type(
                slot = 0,
                type = TypeX.getEmpty()
            )
        }
    }
}