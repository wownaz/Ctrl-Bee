package com.example.ctrlbee.presentation.fragment.room

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ctrlbee.core.Constants
import com.example.ctrlbee.data.repository.SharedPreferencesRepo
import com.example.ctrlbee.databinding.FragmentRoomsBinding
import com.example.ctrlbee.domain.model.Room
import com.example.ctrlbee.presentation.fragment.home.ToDoBottomSheetDialog
import com.example.ctrlbee.presentation.state.RoomState
import com.example.ctrlbee.presentation.state.ToDoListState
import com.example.ctrlbee.presentation.viewmodel.RoomViewModel
import com.example.ctrlbee.presentation.viewmodel.ToDoListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoomsFragment : Fragment() {

    @Inject
    lateinit var sharedPreferencesRepo: SharedPreferencesRepo
    private lateinit var binding: FragmentRoomsBinding
    private val viewModel: RoomViewModel by viewModels()

    private val continentDialog = BottomSheetRoomFragment(true)

    private fun initObservers() {
        viewModel.roomState.observe(viewLifecycleOwner, ::handleRoomListState)
    }

    private fun initActions(rooms: List<Room>) = with(binding) {
        roomsRecyclerView.adapter = RoomsAdapter(rooms)
        roomsRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun handleRoomListState(state: RoomState) = with(binding) {
        when (state) {
            is RoomState.Failed -> {
                Log.e("ToDoListFragmentTag", state.message)
            }
            is RoomState.Loading -> {}
            is RoomState.Success -> {
                val rooms = state.result
                initActions(rooms)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRoomsBinding.inflate(layoutInflater)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        viewModel.fetchAllRooms(
            token = sharedPreferencesRepo.getUserRefreshToken(),
        )

        binding.floatingActionButton.setOnClickListener{
            continentDialog.show(childFragmentManager,"BottomSheetFragment")
        }


//        binding.cardviewButton.setOnClickListener {
//            childFragmentManager.beginTransaction().apply {
//
//            }
//        }

    }
}