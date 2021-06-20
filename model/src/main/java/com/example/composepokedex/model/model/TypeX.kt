package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.TypeXResponse

data class TypeX(
    val name: String
) {

    companion object {
        fun transform(typeXResponse: TypeXResponse): TypeX {
            return TypeX(
                name = typeXResponse.name ?: ""
            )
        }
        fun getEmpty(): TypeX {
            return TypeX(name = "")
        }
    }
}