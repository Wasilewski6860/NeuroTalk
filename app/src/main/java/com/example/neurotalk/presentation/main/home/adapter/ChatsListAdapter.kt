package com.example.neurotalk.presentation.main.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.ChatInfo
import com.example.neurotalk.R
import com.example.neurotalk.databinding.ChatItemBinding

class ChatsListAdapter(
    private val listener: OnItemClick
) : RecyclerView.Adapter<ChatsListAdapter.ChatItemHolder>() {

    /** Test values for now **/
    private val chatsList: MutableList<ChatInfo> = mutableListOf(
        ChatInfo("1", "Some chat name", "url"),
        ChatInfo("1", "Some chat name", "url"),
        ChatInfo("1", "Some chat name", "url"),
        ChatInfo("1", "Some chat name", "url"),
        ChatInfo("1", "Some chat name", "url"),
        ChatInfo("1", "Some chat name", "url"),
        ChatInfo("1", "Some chat name", "url"),
        ChatInfo("1", "Some chat name", "url"),
        ChatInfo("1", "Some chat name", "url"),
        ChatInfo("1", "Some chat name", "url"),
        ChatInfo("1", "Some chat name", "url"),
        ChatInfo("1", "Some chat name", "url"),
        ChatInfo("1", "Some chat name", "url"),
        ChatInfo("1", "Some chat name", "url"),
        ChatInfo("1", "Some chat name", "url")
    )

    inner class ChatItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ChatItemBinding.bind(view)

        fun bind(chat: ChatInfo) = with(binding) {
            chatNameTextView.text = chat.chatName
            // TODO: implement method which will set image on binding.chatIcon
            chatItemRootView.setOnClickListener {
                listener.onChatClick(adapterPosition)
            }
        }
    }

   interface OnItemClick {
       fun onChatClick(adapterPosition: Int)
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.chat_item, parent, false
        )
        return ChatItemHolder(view)
    }

    override fun getItemCount(): Int = chatsList.size

    override fun onBindViewHolder(holder: ChatItemHolder, position: Int) {
        holder.bind(chatsList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addChatToRecycler(item: ChatInfo) {
        chatsList.add(item)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addChatsListToRecycler(itemsList: List<ChatInfo>) {
        itemsList.forEach {
            chatsList.add(it)
            notifyDataSetChanged()
        }
    }

    fun deleteChatFromRecycler(position: Int) {
        chatsList.removeAt(position)
        notifyItemRemoved(position)
    }

}