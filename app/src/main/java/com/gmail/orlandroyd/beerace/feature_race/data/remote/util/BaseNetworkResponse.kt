package com.gmail.orlandroyd.beerace.feature_race.data.remote.util

import android.util.Log
import com.gmail.orlandroyd.beerace.feature_race.data.remote.dto.CaptchaResponse
import com.gmail.orlandroyd.beerace.feature_race.data.remote.dto.ErrorResponse
import com.gmail.orlandroyd.beerace.feature_race.util.Resource
import com.google.gson.Gson
import retrofit2.Response

abstract class BaseNetworkResponse {

    companion object {
        private const val TAG = "CONNECTION"
    }

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Resource.Success(data = body)
                }
            } else {
                // Authentication
                if (response.code() == 403) {
                    val result: CaptchaResponse =
                        Gson().fromJson(
                            response.errorBody()!!.charStream(),
                            CaptchaResponse::class.java
                        )
                    return Resource.Error(
                        message = result.captchaUrl.orEmpty(),
                        code = 403
                    )
                }
                // Default Error
                val result: ErrorResponse =
                    Gson().fromJson(
                        response.errorBody()!!.charStream(),
                        ErrorResponse::class.java
                    )
                Log.e(TAG, "CODE: ${result.error?.code} MSG: ${result.error.toString()}")
                return Resource.Error(
                    message = result.error.toString(),
                    code = result.error?.code
                )
            }
            return Resource.Error(
                message = "Oops, something went wrong!",
                code = 498
            )
        } catch (e: Exception) {
            Log.e(TAG, e.stackTraceToString())
            return Resource.Error(
                "Couldn't reach server, check your internet connection.",
                code = 499
            )
        }
    }
}