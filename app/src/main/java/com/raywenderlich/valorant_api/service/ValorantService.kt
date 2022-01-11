package com.raywenderlich.valorant_api.service

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ValorantService {

    @GET("v1/agents/{uuid}")
    suspend fun getCustomAgent(@Path("uuid") id: String): Response<AgentResponse>

    @GET("v1/agents")
    suspend fun getAllAgents(): Response<AgentsList>

    @GET("v1/maps/{uuid}")
    suspend fun getCustomMap(@Path("uuid") id: String): Response<MapsResponse>

    @GET("v1/maps")
    suspend fun getAllMaps(): Response<MapsList>

    companion object {
        val instance: ValorantService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://valorant-api.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(ValorantService::class.java)
        }
    }
}