package com.raywenderlich.valorant_api.service

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ValorantService {

    @GET("v1/agents/{agentId}")
    suspend fun getCustomAgent(@Path("agentId") id: String): Response<Agent>

    @GET("v1/agents")
    suspend fun getAllAgents(): Response<AgentsList>

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