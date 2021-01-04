package io.github.pengdst.githubpage.components.ui.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

abstract class BaseActivity<VM: ViewModel>: AppCompatActivity() {

    fun shortToast(message: String?){
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    fun longToast(message: String?){
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}