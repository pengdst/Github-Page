package io.github.pengdst.githubpage.components.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class BindingFragment<Binding : ViewBinding> : BaseFragment() {

    protected val binding: Binding by lazy { getViewBinding(layoutInflater) }

    abstract fun getViewBinding(inflater: LayoutInflater): Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}