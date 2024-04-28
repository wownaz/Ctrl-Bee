package com.example.ctrlbee.presentation.fragment.room

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import com.example.ctrlbee.data.repository.SharedPreferencesRepo
import com.example.ctrlbee.databinding.FragmentBottomSheetRoomBinding
import com.example.ctrlbee.domain.model.Room
import com.example.ctrlbee.presentation.state.RoomAddState
import com.example.ctrlbee.presentation.state.RoomState
import com.example.ctrlbee.presentation.viewmodel.RoomViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BottomSheetRoomFragment(private var expanded: Boolean = false): BottomSheetDialogFragment() {

    @Inject
    lateinit var sharedPreferencesRepo: SharedPreferencesRepo
    private lateinit var binding: FragmentBottomSheetRoomBinding
    private val viewModel: RoomViewModel by viewModels()

    private fun initObservers() {
        viewModel.roomAddState.observe(viewLifecycleOwner, ::handleRoomAddState)
    }

    private fun handleRoomAddState(state: RoomAddState) = with(binding) {
        when (state) {
            is RoomAddState.Failed -> {
                Log.e("ToDoListFragmentTag", state.message)
            }
            is RoomAddState.Loading -> {}
            is RoomAddState.Success -> {
                val room = state.result
            }
        }
    }

    private lateinit var behavior: BottomSheetBehavior<FrameLayout>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetRoomBinding.inflate(inflater, container, false)

        initObservers()


        binding.saveRoomBtn.setOnClickListener {

            viewModel.saveRooms(
                token = sharedPreferencesRepo.getUserRefreshToken(),
                Room(binding.fieldRoomName.text.toString(), binding.isRoomPrivate.isActivated, 1)
            )
            val bottomSheet = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
            bottomSheet.behavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheet = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        behavior = bottomSheet.behavior

        if (expanded) behavior.state = BottomSheetBehavior.STATE_EXPANDED
        return bottomSheet
    }
}