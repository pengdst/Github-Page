package io.github.pengdst.githubpage.components.adapters

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.pengdst.githubpage.components.listeners.RecyclerViewClickListener

abstract class BaseAdapter<ViewHolder : RecyclerView.ViewHolder, Model>(
    callback: DiffUtil.ItemCallback<Model>
) : ListAdapter<Model, ViewHolder>(callback) {

    protected var listener: ((view: View, Model, position: Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (view: View, Model, position: Int) -> Unit) {
        this.listener = listener
    }

    override fun getItemCount() = currentList.size

    abstract fun getLayout(): Int

}