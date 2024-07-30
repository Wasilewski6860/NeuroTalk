package com.example.neurotalk.presentation.auth.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.base.BaseMviFragment
import com.example.base.BaseMviViewModel
import com.example.neurotalk.app.NeuroTalkApp
import com.example.neurotalk.databinding.RegistrationFragmentBinding
import com.example.neurotalk.presentation.auth.sign_up.feature.SignUpDependencies
import com.example.neurotalk.presentation.auth.sign_up.feature.SignUpMessage
import com.example.neurotalk.presentation.auth.sign_up.feature.SignUpState
import com.example.neurotalk.presentation.auth.sign_up.viewmodel.SignUpViewModel
import com.example.neurotalk.presentation.auth.sign_up.viewmodel.SignUpViewModelFactory
import javax.inject.Inject

class SignUpFragment : BaseMviFragment<SignUpState, SignUpMessage, SignUpDependencies>() {

    private var _binding: RegistrationFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: SignUpViewModelFactory
    override lateinit var viewModel: BaseMviViewModel<SignUpState, SignUpMessage, SignUpDependencies>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as NeuroTalkApp).applicationComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[SignUpViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegistrationFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun initDispatchers() {
        binding.signInButton.setOnClickListener {
            dispatch(
                SignUpMessage.SignUpButtonClicked(
                    name = binding.nameTextInputEditText.text.toString(),
                    email = binding.emailTextInputEditText.text.toString(),
                    password = binding.passwordTextInputEditText.text.toString(),
                    confirmPassword = binding.confirmPasswordTextInputEditText.text.toString()
                )
            )
        }
    }

    override fun render(state: SignUpState) {
        when(state) {
            SignUpState.Error -> {
                binding.loadingPb.isVisible = false
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
            }
            SignUpState.Loading -> binding.loadingPb.isVisible = true
            SignUpState.NotStarted ->  binding.loadingPb.isVisible = false
            SignUpState.Success -> {
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
//                val intent = Intent(requireContext(), MainActivity::class.java)
//                startActivity(intent)
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}