package com.rpgroup.bn.Model


data class MessageItem(
    var image_url:String,
    var user_name:String,
    var publish_time:String,
    var num_fav:Int,
    var num_com:Int) {
}