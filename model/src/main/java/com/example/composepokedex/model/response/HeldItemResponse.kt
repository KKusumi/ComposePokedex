package com.example.composepokedex.model.response

data class HeldItemResponse(
    val item: ItemResponse,
    val version_details: List<VersionDetailResponse>
)