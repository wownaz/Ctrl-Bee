package com.example.ctrlbee.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ctrlbee.domain.model.Room
import com.example.ctrlbee.domain.repository.RoomRepository
import com.example.ctrlbee.presentation.state.RoomAddState
import com.example.ctrlbee.presentation.state.RoomState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val roomRepo: RoomRepository
) : ViewModel() {

    private val _roomStateLiveData = MutableLiveData<RoomState>()
    val roomState: LiveData<RoomState> get() = _roomStateLiveData

    fun fetchAllRooms(
        token: String,
    ) {
        _roomStateLiveData.value = RoomState.Loading

        viewModelScope.launch {
            try {
                val result = roomRepo.getAllRooms("Bearer $token")
                _roomStateLiveData.value = RoomState.Success(result)
            } catch (ex: Exception) {
                _roomStateLiveData.value =
                    RoomState.Failed("An error occurred during fetching rooms..")
            }
        }
    }

    private val _roomAddStateLiveData = MutableLiveData<RoomAddState>()
    val roomAddState: LiveData<RoomAddState> get() = _roomAddStateLiveData

    fun saveRooms(
        token: String,
        room: Room
    ) {
        _roomAddStateLiveData.value = RoomAddState.Loading

        viewModelScope.launch {
            try {
                val result = roomRepo.addRoom("Bearer $token", room)
                _roomAddStateLiveData.value = RoomAddState.Success(result)
            } catch (ex: Exception) {
                _roomAddStateLiveData.value =
                    RoomAddState.Failed("An error occurred during fetching rooms..")
            }
        }
    }
}