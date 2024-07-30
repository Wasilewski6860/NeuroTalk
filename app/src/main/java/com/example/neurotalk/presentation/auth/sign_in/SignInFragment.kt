package com.example.neurotalk.presentation.auth.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.base.BaseMviFragment
import com.example.base.BaseMviViewModel
import com.example.neurotalk.app.NeuroTalkApp
import com.example.neurotalk.databinding.RegistrationFragmentBinding
import com.example.neurotalk.databinding.SignInFragmentBinding
import com.example.neurotalk.presentation.auth.sign_in.feature.SignInDependencies
import com.example.neurotalk.presentation.auth.sign_in.feature.SignInMessage
import com.example.neurotalk.presentation.auth.sign_in.feature.SignInState
import com.example.neurotalk.presentation.auth.sign_in.viewmodel.SignInViewModel
import com.example.neurotalk.presentation.auth.sign_in.viewmodel.SignInViewModelFactory
import com.example.neurotalk.presentation.auth.sign_up.viewmodel.SignUpViewModel
import com.example.neurotalk.presentation.auth.sign_up.viewmodel.SignUpViewModelFactory
import javax.inject.Inject

class SignInFragment : BaseMviFragment<SignInState, SignInMessage, SignInDependencies>() {

    private var _binding: SignInFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: SignInViewModelFactory
    override lateinit var viewModel: BaseMviViewModel<SignInState, SignInMessage, SignInDependencies>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as NeuroTalkApp).applicationComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[SignInViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SignInFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun initDispatchers() {
        binding.signInButton.setOnClickListener {
            dispatch(
                SignInMessage.SignInButtonClicked(
                    email = binding.emailTextInputEditText.text.toString(),
                    password = binding.passwordTextInputEditText.text.toString(),
                )
            )
        }
    }

    override fun render(state: SignInState) {
        binding.loadingPb.isVisible = state.isLoading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}