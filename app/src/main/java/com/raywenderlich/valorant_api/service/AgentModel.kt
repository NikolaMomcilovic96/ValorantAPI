package com.raywenderlich.valorant_api.service

data class AgentResponse(
    val status: Int,
    val data: Agent
)

data class AgentsList(
    val status: Int,
    val data: List<Agent>
)

data class Agent(
    val uuid: String,
    val displayName: String,
    val description: String,
    val displayIcon: String,
    val role: RoleData
)

data class RoleData(
    val uuid: String,
    val displayName: String,
    val description: String,
    val displayIcon: String
)

data class MapsList(
    val status: Int,
    val data: List<Map>
)

data class MapsResponse(
    val status: Int,
    val data: Map
)

data class Map(
    val uuid: String,
    val displayName: String,
    val coordinates: String,
    val displayIcon: String,
    val splash: String
)