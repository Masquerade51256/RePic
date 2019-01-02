package com.rpgroup.bn.view

import com.rpgroup.bn.view.common.AnyItemAdapter
import com.rpgroup.bn.view.common.RecyclerListAdapter

class MainListAdapter (items:List<AnyItemAdapter>): RecyclerListAdapter(items){
    fun add(itemAdapter: AnyItemAdapter){
        items+=itemAdapter
        val index=items.indexOf(itemAdapter)
        if(index==-1) return
        notifyItemInserted(index)
    }
    fun delete(itemAdapter: AnyItemAdapter){
        val index=items.indexOf(itemAdapter)
        if(index==-1) return
        items-=itemAdapter
        notifyItemRemoved(index)
    }
}