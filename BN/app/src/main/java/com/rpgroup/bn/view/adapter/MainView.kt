package com.rpgroup.bn.view.adapter

import com.rpgroup.bn.model.PersonalPic


interface MainView {
    var refresh:Boolean
    fun show(items: List<PersonalPic>)
    fun showError(error:Throwable)
}