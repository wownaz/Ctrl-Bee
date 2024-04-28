package com.example.ctrlbee.data.remote

import com.example.ctrlbee.domain.model.Room
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RoomApiService {

    @GET("/api/rooms")
    suspend fun getAllRooms(
        @Header("Authorization") token: String,
    ): List<Room>

    @POST("/api/rooms")
    suspend fun addRoom(
        @Header("Authorization") token: String,
        @Body room: Room,
    ): Room
}