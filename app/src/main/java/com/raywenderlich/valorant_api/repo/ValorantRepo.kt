package com.raywenderlich.valorant_api.repo

import com.raywenderlich.valorant_api.service.ValorantService

class ValorantRepo(private val valorantService: ValorantService) {
    suspend fun getCustomAgent(id: String) = valorantService.getCustomAgent(id)

    suspend fun getAllAgents() = valorantService.getAllAgents()

    suspend fun getCustomMap(id: String) = valorantService.getCustomMap(id)

    suspend fun getAllMaps() = valorantService.getAllMaps()
}