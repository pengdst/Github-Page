package io.github.pengdst.githubpage.components.listeners

import android.view.View

interface RecyclerViewClickListener {

    interface onClick

    fun onClick(view: View, obj: Any, position: Int)

}