package com.raywenderlich.valorant_api.service

data class AgentReponse(
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
    val role: RoleData
)

data class RoleData(
    val uuid: String,
    val displayName: String,
    val description: String,
)

