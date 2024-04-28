package com.example.ctrlbee.presentation.fragment.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ctrlbee.R
import com.example.ctrlbee.core.Constants
import com.example.ctrlbee.databinding.FragmentRegisterBinding
import com.example.ctrlbee.databinding.FragmentResetBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ResetFragment : Fragment(R.layout.fragment_reset) {

    private val viewBinding: FragmentResetBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initActions()
    }

    private fun initActions() =
        with(viewBinding) {
            buttonSendCode.setOnClickListener {
                findNavController().navigate(R.id.action_resetFragment_to_verificationFragment)
            }
        }

    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResetBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.buttonSendCode.setOnClickListener {
            findNavController().navigate(R.id.action_resetFragment_to_verificationFragment)
        }


    }*/

}