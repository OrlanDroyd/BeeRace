package com.gmail.orlandroyd.beerace.feature_race.data.remote.dto

import com.squareup.moshi.Json

data class ErrorResponse(
    @Json(name = "error")
    val error: Error?
)

data class Error(
    @Json(name = "code")
    val code: Int?,
    @Json(name = "message")
    val message: String?
)