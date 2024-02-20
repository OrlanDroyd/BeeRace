package com.gmail.orlandroyd.beerace.feature_race.data.remote.dto


import com.squareup.moshi.Json

data class CaptchaResponse(
    @Json(name = "captchaUrl")
    val captchaUrl: String?
)