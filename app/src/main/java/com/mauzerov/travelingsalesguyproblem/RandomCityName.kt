package com.mauzerov.travelingsalesguyproblem

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.net.URL

object RandomCityName {
    operator fun invoke(): Flow<String> = flow<String> {
        URL("https://random-data-api.com/api/v2/addresses").openConnection()
            .apply { connect() }.let {
                val result = it.getInputStream().bufferedReader().readText()
                // Parse To Json and return city
                emit(
                    Json.parseToJsonElement(result)
                        .jsonObject["city"]!!.jsonPrimitive.content
                )
            }
    }.flowOn(Dispatchers.IO)
}