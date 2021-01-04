package io.github.pengdst.githubpage.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.pengdst.githubpage.datas.network.retrofit.ServiceGenerator
import io.github.pengdst.githubpage.R
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserRoute() = ServiceGenerator.createUserRoute()

    @Provides
    @Singleton
    fun provideGlideRequestOptions() = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.DATA)
        error(R.drawable.ic_launcher_background)
        placeholder(R.drawable.ic_launcher_foreground)
    }

    @Provides
    @Singleton
    fun provideGlideRequestManager(
        @ApplicationContext context: Context,
        requestOptions: RequestOptions
    ) = Glide.with(context)
        .applyDefaultRequestOptions(requestOptions)
}