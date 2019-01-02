package com.rpgroup.bn.view
import android.support.v7.widget.RecyclerView
import android.view.View
import com.rpgroup.bn.model.MessageItem
import com.rpgroup.bn.R
import com.rpgroup.bn.view.common.ItemAdapter

class MessageItemAdapter(
    val messageItem: MessageItem
): ItemAdapter<MessageItemAdapter.ViewHolder>(R.layout.message_info) {

    override fun onCreateViewHolder(itemView: View)= ViewHolder(itemView)


    override fun ViewHolder.onBindViewHolder(){
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

    }

}

