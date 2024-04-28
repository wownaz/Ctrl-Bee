package com.example.ctrlbee.presentation.state

import com.example.ctrlbee.domain.model.Room

sealed class RoomAddState {
    data object Loading: RoomAddState()
    data class Failed(
        val message: String
    ): RoomAddState()
    data class Success(
        val result: Room
    ): RoomAddState()
}