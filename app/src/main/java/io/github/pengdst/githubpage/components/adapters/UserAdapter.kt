package io.github.pengdst.githubpage.components.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import io.github.pengdst.githubpage.datas.domain.models.UserDetail
import io.github.pengdst.githubpage.R
import io.github.pengdst.githubpage.databinding.ItemUserGithubBinding
import javax.inject.Inject

class UserAdapter @Inject constructor() : BaseAdapter<UserAdapter.ViewHolder, UserDetail>(
    object : DiffUtil.ItemCallback<UserDetail>(){
        override fun areItemsTheSame(oldItem: UserDetail, newItem: UserDetail) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserDetail, newItem: UserDetail) = oldItem.hashCode() == newItem.hashCode()
    }
) {

    @Inject lateinit var glide: RequestManager
    lateinit var binding: ItemUserGithubBinding

    override fun getLayout() = R.layout.item_user_github

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userModel = currentList[position]

        with(holder.binding) {
            textviewName.text = userModel.name
            textviewUsername.text = userModel.username

            glide
                .load(userModel.avatarUrl)
                .into(holder.binding.imagePhoto)
        }

        holder.itemView.setOnClickListener {
            listener?.invoke(it, userModel, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = ItemUserGithubBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)

    }

    class ViewHolder(val binding: ItemUserGithubBinding) : RecyclerView.ViewHolder(binding.root)
}