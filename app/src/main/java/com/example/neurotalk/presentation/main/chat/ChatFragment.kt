package com.example.neurotalk.presentation.main.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.BaseMviFragment
import com.example.base.BaseMviViewModel
import com.example.neurotalk.app.NeuroTalkApp
import com.example.neurotalk.databinding.ChatScreenBinding
import com.example.neurotalk.presentation.main.chat.adapter.ChatMessagesAdapter
import com.example.neurotalk.presentation.main.chat.feature.ChatDependencies
import com.example.neurotalk.presentation.main.chat.feature.ChatMessage
import com.example.neurotalk.presentation.main.chat.feature.ChatState
import com.example.neurotalk.presentation.main.chat.viewmodel.ChatViewModel
import com.example.neurotalk.presentation.main.chat.viewmodel.ChatViewModelFactory
import javax.inject.Inject

class ChatFragment : BaseMviFragment<ChatState, ChatMessage, ChatDependencies>() {

    private lateinit var binding: ChatScreenBinding

    @Inject
    lateinit var viewModelFactory: ChatViewModelFactory
    override lateinit var viewModel: BaseMviViewModel<ChatState, ChatMessage, ChatDependencies>

    private lateinit var adapter: ChatMessagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as NeuroTalkApp).applicationComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[ChatViewModel::class]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChatScreenBinding.inflate(layoutInflater, container, false)

        setupRecycler()

        return binding.root
    }

    override fun initDispatchers() {
        /** STUB! **/
    }

    override fun render(state: ChatState) {
        /** STUB! **/
    }

    private fun setupRecycler() = binding.apply {
        adapter = ChatMessagesAdapter(object : ChatMessagesAdapter.OnMessageClick {
            override fun onMessageClick(adapterPosition: Int) {
                Log.d("Chat fragment recycler", "clicked on $adapterPosition message")
            }
        }, requireContext())
        chatRecyclerView.adapter = adapter
        chatRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}