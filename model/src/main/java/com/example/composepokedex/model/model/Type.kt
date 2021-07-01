package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.TypeResponse

sealed class Type(
    open val slot: Int,
    open val name: String
) {

    data class Normal(override val slot: Int, override val name: String) : Type(slot, name)
    data class Fire(override val slot: Int, override val name: String) : Type(slot, name)
    data class Water(override val slot: Int, override val name: String) : Type(slot, name)
    data class Electric(override val slot: Int, override val name: String) : Type(slot, name)
    data class Grass(override val slot: Int, override val name: String) : Type(slot, name)
    data class Ice(override val slot: Int, override val name: String) : Type(slot, name)
    data class Fighting(override val slot: Int, override val name: String) : Type(slot, name)
    data class Poison(override val slot: Int, override val name: String) : Type(slot, name)
    data class Ground(override val slot: Int, override val name: String) : Type(slot, name)
    data class Flying(override val slot: Int, override val name: String) : Type(slot, name)
    data class Psychic(override val slot: Int, override val name: String) : Type(slot, name)
    data class Bug(override val slot: Int, override val name: String) : Type(slot, name)
    data class Rock(override val slot: Int, override val name: String) : Type(slot, name)
    data class Ghost(override val slot: Int, override val name: String) : Type(slot, name)
    data class Dragon(override val slot: Int, override val name: String) : Type(slot, name)
    data class Dark(override val slot: Int, override val name: String) : Type(slot, name)
    data class Steel(override val slot: Int, override val name: String) : Type(slot, name)
    data class Fairy(override val slot: Int, override val name: String) : Type(slot, name)

    companion object {
        fun transform(typeResponse: TypeResponse): Type {
            return when (typeResponse.type.name) {
                "normal" -> {
                    Normal(slot = typeResponse.slot, name = typeResponse.type.name)
                }
                "fire" -> {
                    Fire(slot = typeResponse.slot, name = typeResponse.type.name)
                }
                "water" -> {
                    Water(slot = typeResponse.slot, name = typeResponse.type.name)
                }
                "electric" -> {
                    Electric(slot = typeResponse.slot, name = typeResponse.type.name)
                }
                "grass" -> {
                    Grass(slot = typeResponse.slot, name = typeResponse.type.name)
                }
                "ice" -> {
                    Ice(slot = typeResponse.slot, name = typeResponse.type.name)
                }
                "fighting" -> {
                    Fighting(slot = typeResponse.slot, name = typeResponse.type.name)
                }
                "poison" -> {
                    Poison(slot = typeResponse.slot, name = typeResponse.type.name)
                }
                "ground" -> {
                    Ground(slot = typeResponse.slot, name = typeResponse.type.name)
                }
                "flying" -> {
                    Flying(slot = typeResponse.slot, name = typeResponse.type.name)
                }
                "psychic" -> {
                    Psychic(slot = typeResponse.slot, name = typeResponse.type.name)
                }
                "bug" -> {
                    Bug(slot = typeResponse.slot, name = typeResponse.type.name)
                }
                "rock" -> {
                    Rock(slot = typeResponse.slot, name = typeResponse.type.name)
                }
                "ghost" -> {
                    Ghost(slot = typeResponse.slot, name = typeResponse.type.name)
                }
                "dragon" -> {
                    Dragon(slot = typeResponse.slot, name = typeResponse.type.name)
                }
                "dark" -> {
                    Dark(slot = typeResponse.slot, name = typeResponse.type.name)
                }
                "steel" -> {
                    Steel(slot = typeResponse.slot, name = typeResponse.type.name)
                }
                "fairy" -> {
                    Fairy(slot = typeResponse.slot, name = typeResponse.type.name)
                }
                else -> {
                    Normal(slot = 0, name = "normal")
                }
            }
        }

        fun getEmpty(): Type {
            return Normal(0, "normal")
        }
    }
}