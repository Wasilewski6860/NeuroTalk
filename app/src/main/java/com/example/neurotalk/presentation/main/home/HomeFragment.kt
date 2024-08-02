package com.example.neurotalk.presentation.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.base.BaseMviFragment
import com.example.base.BaseMviViewModel
import com.example.neurotalk.app.NeuroTalkApp
import com.example.neurotalk.databinding.MainScreenBinding
import com.example.neurotalk.presentation.main.home.feature.HomeDependencies
import com.example.neurotalk.presentation.main.home.feature.HomeMessage
import com.example.neurotalk.presentation.main.home.feature.HomeState
import com.example.neurotalk.presentation.main.home.viewmodel.HomeViewModel
import com.example.neurotalk.presentation.main.home.viewmodel.HomeViewModelFactory
import javax.inject.Inject

class HomeFragment : BaseMviFragment<HomeState, HomeMessage, HomeDependencies>() {

    private lateinit var binding: MainScreenBinding

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory
    override lateinit var viewModel: BaseMviViewModel<HomeState, HomeMessage, HomeDependencies>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as NeuroTalkApp).applicationComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun initDispatchers() {
//        dispatch(HomeMessage.HomeTest)
    }

    override fun render(state: HomeState) {
        binding.apply {
            when (state) {
                is HomeState.UserInfoLoaded -> {
//                    greetingTextView.visibility = View.VISIBLE
//                    greetingTextView.text = "Hi, ${state.userInfo.userName}"
                    Log.d("abs", "user loaded ${state.userInfo}")
                }
                is HomeState.ChatsLoaded -> {
                    chatsRecyclerView.visibility = View.VISIBLE
                    chatLoadingPb.visibility = View.GONE
                    Log.d("abs", "chats loaded ${state.chatsList}")
                }
                is HomeState.Loading -> {
                    chatsRecyclerView.visibility = View.INVISIBLE
                    chatLoadingPb.visibility = View.VISIBLE
//                    greetingTextView.visibility = View.INVISIBLE
                    Log.d("abs", "loading")
                }
                is HomeState.ChatsLoadingError -> {
                    chatsRecyclerView.visibility = View.INVISIBLE
                    Toast.makeText(
                        requireContext(), "Error loading chats", Toast.LENGTH_SHORT
                    ).show() // TODO: Replace that later
                }
                is HomeState.UserInfoLoadingError -> {
//                    greetingTextView.text = "ERROR"
//                    greetingTextView.visibility = View.VISIBLE
                }
                is HomeState.Error -> Unit
            }
        }
    }

}