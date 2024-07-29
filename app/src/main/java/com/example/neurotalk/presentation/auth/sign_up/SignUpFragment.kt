package com.example.neurotalk.presentation.auth.sign_up

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.base.BaseMviFragment
import com.example.base.BaseMviViewModel
import com.example.neurotalk.app.NeuroTalkApp
import com.example.neurotalk.databinding.RegistrationFragmentBinding
import com.example.neurotalk.presentation.auth.sign_in.ViewModelFactory
import com.example.neurotalk.presentation.main.MainActivity
import com.example.neurotalk.utils.appComponent
import com.example.neurotalk.utils.lazyViewModel
import java.time.LocalTime
import javax.inject.Inject

class SignUpFragment : BaseMviFragment<SignUpState, SignUpMessage, SignUpDependencies>() {

    private var _binding: RegistrationFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: SignUpViewModelFactory
    override lateinit var viewModel: BaseMviViewModel<SignUpState, SignUpMessage, SignUpDependencies>
//
//    override val viewModel: SignUpViewModel by viewModels { viewModelFactory }

//    override val viewModel: SignUpViewModel by lazyViewModel {
//        requireContext().appComponent().signUpVmFactory().create()
//    }

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
            SignUpState.Error -> Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
            SignUpState.Loading -> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_LONG).show()
            SignUpState.NotStarted -> Toast.makeText(requireContext(), "NotStarted", Toast.LENGTH_LONG).show()
            SignUpState.Success -> {
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
//                val intent = Intent(requireContext(), MainActivity::class.java)
//                startActivity(intent)
            }
        }
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        (requireActivity().application as NeuroTalkApp).applicationComponent.inject(this)
//    }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}