package com.rpgroup.bn.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.apple.marvelgallery.view.common.bindView
import com.example.apple.marvelgallery.view.common.loadImage
import com.rpgroup.bn.R
import com.rpgroup.bn.model.PersonalPic
import com.rpgroup.bn.view.common.ItemAdapter
import com.rpgroup.bn.view.listener.favoriteListener.getFavoriteListener

class MessageAdapter (
        val character: PersonalPic
): ItemAdapter<MessageAdapter.ViewHolder>(R.layout.item_msg){
    override fun onCreateViewHolder(itemView: View): ViewHolder {
        return ViewHolder(itemView)
    }
    override fun ViewHolder.onBindViewHolder(){
        imgView.loadImage(character.url)
        name.text = character.name
        btnLike.setOnClickListener(getFavoriteListener(btnLike))
    }



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgView by bindView<ImageView>(R.id.imgView_pic)
        val name by bindView<TextView>(R.id.tv_userName)
        val btnLike by bindView<ImageButton>(R.id.btn_favorite);
    }

}