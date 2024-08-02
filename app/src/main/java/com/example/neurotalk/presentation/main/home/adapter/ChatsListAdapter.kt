package com.example.neurotalk.presentation.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.ChatInfo
import com.example.neurotalk.databinding.ChatItemBinding

class ChatsListAdapter(
    private val onItemCLick: OnItemClick
) : ListAdapter<ChatInfo, ChatsListAdapter.ChatViewHolder>(DiffCallBack) {

    class ChatViewHolder(binding: ChatItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChatItemBinding.inflate(inflater, parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        // TODO: Implement code here after realizing ui part
    }

    interface OnItemClick {
        fun onChatClick(chatId: String)
    }

    companion object {
        val DiffCallBack = object : DiffUtil.ItemCallback<ChatInfo>() {

            override fun areItemsTheSame(oldItem: ChatInfo, newItem: ChatInfo): Boolean {
                return oldItem.chatId === newItem.chatId
            }

            override fun areContentsTheSame(oldItem: ChatInfo, newItem: ChatInfo): Boolean {
                return oldItem == newItem
            }

        }
    }

}