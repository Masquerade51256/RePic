package com.rpgroup.bn.View
import android.support.v7.widget.RecyclerView
import android.view.View
import com.rpgroup.bn.Model.MessageItem
import com.rpgroup.bn.R

class MessageItemAdapter(
    val messageItem: MessageItem
):ItemAdapter<MessageItemAdapter.ViewHolder>(R.layout.message_info) {

    override fun onCreateViewHolder(itemView: View)= ViewHolder(itemView)


    override fun ViewHolder.onBindViewHolder(){
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

    }

}

