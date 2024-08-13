package com.example.neurotalk.presentation.main.mistake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.neurotalk.databinding.MistakeScreenBinding

class MistakeFragment : Fragment() {

    private lateinit var binding: MistakeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MistakeScreenBinding.inflate(layoutInflater, container, false)



        return binding.root
    }

}