package com.example.ctrlbee.domain.repository

import com.example.ctrlbee.domain.model.Room
import com.example.ctrlbee.domain.model.ToDoListItem

interface RoomRepository {

    suspend fun getAllRooms(
        token: String,
    ): List<Room>

    suspend fun addRoom(
        token: String,
        room: Room
    ): Room
}