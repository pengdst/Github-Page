package io.github.pengdst.githubpage.util.extensions

import android.view.MenuItem
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@Suppress("UNCHECKED_CAST")
fun <T : View> MenuItem.asActionView(): T = this.actionView as T

var DrawerLayout.isLocked: Boolean
    get() {
        return when (getDrawerLockMode(this)) {
            DrawerLayout.LOCK_MODE_LOCKED_CLOSED -> true
            else -> false
        }
    }
    set(isLocked) {
        when {
            isLocked -> setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            else -> setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED)
        }
    }

fun DrawerLayout.setDrawerLockModeWhen(destinationId: Int, vararg unlockedId: Int) {
    setDrawerLockMode(
        when (destinationId) {
            in unlockedId -> {
                DrawerLayout.LOCK_MODE_UNDEFINED
            }
            else -> {
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED
            }
        }
    )
}

fun <Adapter : RecyclerView.Adapter<*>> RecyclerView.addOnLastPositionScrollListener(
    layoutManager: LinearLayoutManager,
    adapter: Adapter?,
    onScrollAction: () -> Unit
){
    this.addOnScrollListener(
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) {
                    val itemShowedCount = layoutManager.childCount
                    val fixedItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition()
                    val lastPosition = itemShowedCount + fixedItemPosition
                    val totalItemCount = adapter?.itemCount

                    totalItemCount?.let {
                        if (lastPosition >= it ){
                            onScrollAction()
                        }
                    }
                }
            }
        }
    )
}