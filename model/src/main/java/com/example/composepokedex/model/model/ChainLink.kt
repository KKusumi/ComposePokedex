package com.example.composepokedex.model.model

import com.example.composepokedex.model.response.ChainLinkResponse

data class ChainLink(
    val species: Species,
    val evolvesTo: List<ChainLink>,
) {

    data class Species(
        val name: String,
        val url: String
    ) {

        val id: Int get() {
            return if (url.isBlank()) {
                0
            } else {
                url.removeSuffix("/").split("/").last().toInt()
            }
        }

        val thumbnailImageUrl get() = generateThumbnailImageUrl(id.toString())

        companion object {
            fun transform(species: ChainLinkResponse.Species): Species {
                return Species(
                    name = species.name,
                    url = species.url
                )
            }

            fun getEmpty(): Species {
                return Species(
                    name = "",
                    url = ""
                )
            }
        }

        private fun generateThumbnailImageUrl(id: String) =
            "https://github.com/fanzeyi/pokemon.json/blob/master/thumbnails/${
                id.padStart(
                    3,
                    '0'
                )
            }.png?raw=true"
    }

    companion object {
        fun transform(chainLink: ChainLinkResponse): ChainLink {
            return ChainLink(
                evolvesTo = chainLink.evolves_to.map { transform(it) },
                species = Species.transform(chainLink.species)
            )
        }

        fun getEmpty(): ChainLink {
            return ChainLink(
                species = Species.getEmpty(),
                evolvesTo = listOf()
            )
        }
    }
}