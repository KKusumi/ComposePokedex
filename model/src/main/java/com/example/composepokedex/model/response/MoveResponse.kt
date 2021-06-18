package com.example.composepokedex.model.response

data class MoveResponse(
    val move: MoveXResponse,
    val version_group_details: List<VersionGroupDetailResponse>
)