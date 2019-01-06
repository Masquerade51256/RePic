package com.rpgroup.bn.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.example.apple.marvelgallery.view.common.bindView
import com.example.apple.marvelgallery.view.common.loadImage
import com.rpgroup.bn.R
import com.rpgroup.bn.view.common.ItemAdapter
import com.rpgroup.bn.model.PersonalPic

class PersonalPicAdapter (
        val character: PersonalPic
): ItemAdapter<PersonalPicAdapter.ViewHolder>(R.layout.item_pic){
    override fun onCreateViewHolder(itemView: View): ViewHolder {
        return ViewHolder(itemView)
    }
    override fun ViewHolder.onBindViewHolder(){
        imgView.loadImage(character.url)
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //by:属性值不是在类中直接进行定义，而是将其托付给一个代理类
        val imgView by bindView<ImageView>(R.id.imgView_pic_personal)
    }
}