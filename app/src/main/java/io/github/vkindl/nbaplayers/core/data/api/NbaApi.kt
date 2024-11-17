package io.github.vkindl.nbaplayers.core.data.api

import io.github.vkindl.nbaplayers.core.data.model.player.PlayerResponseDto
import io.github.vkindl.nbaplayers.core.data.model.player.PlayersResponseDto
import io.github.vkindl.nbaplayers.core.data.model.team.TeamResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NbaApi {

    @GET("players")
    suspend fun getPlayers(
        @Query("cursor") cursor: Int,
        @Query("per_page") perPage: Int,
    ): PlayersResponseDto

    @GET("teams/{id}")
    suspend fun getTeamById(
        @Path("id") id: Int
    ): TeamResponseDto

    @GET("players/{id}")
    suspend fun getPlayerById(
        @Path("id") id: Int
    ): PlayerResponseDto
}
