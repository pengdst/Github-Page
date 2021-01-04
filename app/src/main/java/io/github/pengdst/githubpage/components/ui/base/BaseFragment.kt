package io.github.pengdst.githubpage.components.ui.base

import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import io.github.pengdst.githubpage.R

abstract class BaseFragment: Fragment() {

    fun shortToast(message: String?){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun longToast(message: String?){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    protected fun <T : SearchView> T.createSearch(
        hint: String? = null,
        onTextChange: ((query: String) -> Any)? = null,
        onQuerySubmit: (query: String) -> Any
    ): T {
        findViewById<EditText>(R.id.search_src_text).apply {
            this.hint = hint
        }

        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    onQuerySubmit(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { onTextChange?.invoke(it) }
                return false
            }
        })

        return this
    }

    protected fun requireAppCompatActivity(): AppCompatActivity {
        if (requireActivity() is AppCompatActivity) return requireActivity() as AppCompatActivity

        throw ClassCastException("Class ${this.javaClass.simpleName} is not hosted to AppCompatActivity")
    }

}