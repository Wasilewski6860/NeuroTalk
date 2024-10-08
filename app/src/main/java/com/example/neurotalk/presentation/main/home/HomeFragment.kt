package com.example.neurotalk.presentation.main.home

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.BaseMviFragment
import com.example.base.BaseMviViewModel
import com.example.neurotalk.app.NeuroTalkApp
import com.example.neurotalk.databinding.MainScreenBinding
import com.example.neurotalk.navigation.NavigationAnimations
import com.example.neurotalk.navigation.NavigationManager
import com.example.neurotalk.presentation.main.chat.ChatFragment
import com.example.neurotalk.presentation.main.home.adapter.ChatItemDivider
import com.example.neurotalk.presentation.main.home.adapter.ChatsListAdapter
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

    private lateinit var adapter: ChatsListAdapter

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

        initFoldingOffsetChangingListener()
        setupRecycler()

        binding.chatWithBotCardView.setOnClickListener {
            NavigationManager(parentFragmentManager).navigateTo(
                fragment = ChatFragment(),
                backStack = "backStack",
                animation = NavigationAnimations.APPEAR_FROM_RIGHT,
            )
        }

        return binding.root
    }

    override fun initDispatchers() {
        /** STUB! **/
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

    private fun setupRecycler() = binding.apply {
        adapter = ChatsListAdapter(object : ChatsListAdapter.OnItemClick {
            override fun onChatClick(adapterPosition: Int) {
                Log.d("Home fragment recycler", "clicked on $adapterPosition pos")
            }
        })
        chatsRecyclerView.adapter = adapter
        chatsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        chatsRecyclerView.addItemDecoration(ChatItemDivider(requireContext()))
    }

    private fun initFoldingOffsetChangingListener() = binding.apply {
        var isAppBarExpanded = true

        appToolbar.addOnOffsetChangedListener { appBarLayout, _ ->
            val offsetPixels = appBarLayout.top
            val expanded = offsetPixels <= -appBarLayout.height / 2

            if (expanded != isAppBarExpanded) {
                isAppBarExpanded = expanded

                when (isAppBarExpanded) {
                    /** Recycler folded **/
                    true -> animateChatsTextView(true)
                    /** Recycler unfolded **/
                    false -> animateChatsTextView(false)
                }
            }
        }
    }

    private fun animateChatsTextView(isRecyclerFolded: Boolean) = binding.apply {
        val scaleXAnimator = ObjectAnimator.ofFloat(
            allChatsTextView,
            View.SCALE_X,
            if (isRecyclerFolded) 1f else 1.2f,
            if (isRecyclerFolded) 1.2f else 1f
        )
        val scaleYAnimator = ObjectAnimator.ofFloat(
            allChatsTextView,
            View.SCALE_Y,
            if (isRecyclerFolded) 1f else 1.2f,
            if (isRecyclerFolded) 1.2f else 1f
        )
        val alphaAnimator = ObjectAnimator.ofFloat(
            seeAllChatsButton,
            View.ALPHA,
            if (isRecyclerFolded) 1f else 0f,
            if (isRecyclerFolded) 0f else 1f
        )
        AnimatorSet().apply {
            playTogether(scaleXAnimator, scaleYAnimator, alphaAnimator)
            duration = 250
            start()
            doOnEnd {
                if (isRecyclerFolded)
                    allChatsTextView.typeface =
                        Typeface.createFromAsset(context?.assets, "font/poppins_medium.ttf")
                else
                    allChatsTextView.typeface =
                        Typeface.createFromAsset(context?.assets, "font/poppins_light.ttf")
            }
        }
    }

}