package io.github.pengdst.githubpage.datas.domain.models

import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import io.github.pengdst.githubpage.datas.constants.ApiConstant
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
	@JvmField val name: String,
	val username: String,
	val avatar: String,
	val location: String,
	val company: String,
	val follower: Int,
	val following: Int,
	val repository: Int
) : Parcelable {

	fun getAvatarPhoto(): String {
		return ApiConstant.BASE_PHOTO_URL+avatar
	}

	companion object {
		@BindingAdapter("imageUrl")
		fun setImageUrl(imageView: ImageView, url: String) {
			Glide.with(imageView.context).load(url).into(imageView)
		}
	}

}