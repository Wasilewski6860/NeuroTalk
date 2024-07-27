package com.example.neurotalk.registration_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.neurotalk.databinding.RegistrationFragmentBinding

class RegistrationFragment : Fragment() {

    private lateinit var binding: RegistrationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegistrationFragmentBinding.inflate(layoutInflater, container, false)

        binding.apply {

        }

        return binding.root
    }

}