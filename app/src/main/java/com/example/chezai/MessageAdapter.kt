package com.example.chezai

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter(
    private val messages: MutableList<MessageItem>,
    private val onItemClick: (MessageItem) -> Unit,
    private val onSelectionChanged: () -> Unit
) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    var isEditMode = false
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val selectedItems = mutableSetOf<Int>()

    fun toggleSelection(position: Int) {
        if (selectedItems.contains(position)) {
            selectedItems.remove(position)
        } else {
            selectedItems.add(position)
        }
        notifyItemChanged(position)
        onSelectionChanged()
    }

    fun selectAll() {
        selectedItems.clear()
        selectedItems.addAll(messages.indices)
        notifyDataSetChanged()
        onSelectionChanged()
    }

    fun clearSelection() {
        selectedItems.clear()
        notifyDataSetChanged()
        onSelectionChanged()
    }

    fun getSelectedCount(): Int = selectedItems.size

    fun getSelectedMessages(): List<MessageItem> {
        return selectedItems.map { messages[it] }
    }

    fun deleteSelected() {
        val sortedIndices = selectedItems.sortedDescending()
        for (index in sortedIndices) {
            messages.removeAt(index)
        }
        selectedItems.clear()
        notifyDataSetChanged()
        onSelectionChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.bind(message, position)
    }

    override fun getItemCount(): Int = messages.size

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cbSelect: CheckBox = itemView.findViewById(R.id.cbSelect)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvMessageTitle)
        private val tvTime: TextView = itemView.findViewById(R.id.tvMessageTime)
        private val tvStatus: TextView = itemView.findViewById(R.id.tvMessageStatus)
        private val btnDetail: TextView = itemView.findViewById(R.id.btnDetail)

        fun bind(message: MessageItem, position: Int) {
            tvTitle.text = message.title
            tvTime.text = message.time
            
            if (message.isRead) {
                tvStatus.text = "已读"
                tvStatus.background = ContextCompat.getDrawable(itemView.context, R.drawable.bg_message_status_read)
            } else {
                tvStatus.text = "未读"
                tvStatus.background = ContextCompat.getDrawable(itemView.context, R.drawable.bg_message_status)
            }

            // 编辑模式下的复选框
            if (isEditMode) {
                cbSelect.visibility = View.VISIBLE
                cbSelect.isChecked = selectedItems.contains(position)
            } else {
                cbSelect.visibility = View.GONE
            }

            // 点击事件
            itemView.setOnClickListener {
                if (isEditMode) {
                    toggleSelection(position)
                } else {
                    onItemClick(message)
                }
            }

            btnDetail.setOnClickListener {
                if (!isEditMode) {
                    onItemClick(message)
                }
            }
        }
    }
}
