package com.example.composepokedex.model.response

data class VersionGroupDetailResponse(
    val level_learned_at: Int,
    val move_learn_method: MoveLearnMethodResponse,
    val version_group: VersionGroupResponse
)