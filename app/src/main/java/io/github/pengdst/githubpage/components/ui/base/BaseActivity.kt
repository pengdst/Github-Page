package io.github.pengdst.githubpage.components.ui.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    fun shortToast(message: String?){
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    fun longToast(message: String?){
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}