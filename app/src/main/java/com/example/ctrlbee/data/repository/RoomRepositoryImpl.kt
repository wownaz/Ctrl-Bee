package com.example.ctrlbee.data.repository

import com.example.ctrlbee.data.remote.RoomApiService
import com.example.ctrlbee.domain.model.Room
import com.example.ctrlbee.domain.repository.RoomRepository
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val api: RoomApiService
) : RoomRepository {

    override suspend fun getAllRooms(token: String): List<Room> {
        return api.getAllRooms(token)
    }

    override suspend fun addRoom(token: String, room: Room): Room {
        return api.addRoom(token, room)
    }
}