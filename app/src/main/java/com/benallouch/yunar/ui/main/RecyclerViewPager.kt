package com.benallouch.yunar.ui.main

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewPager(
        private val recyclerView: RecyclerView,
        private val loadMore: (Int) -> Unit
) : RecyclerView.OnScrollListener() {

    var isLoading = false
    var lastPage: Int = 1

    init {
        recyclerView.addOnScrollListener(this)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy <= 0) return

        val layoutManager = recyclerView.layoutManager
        layoutManager?.let {
            val totalItemCount = it.itemCount
            val lastVisibleItemPosition = when (layoutManager) {
                is GridLayoutManager -> layoutManager.findFirstCompletelyVisibleItemPosition()
                else -> return
            }

            if (isLoading) return

            if (!isLoading && ((lastVisibleItemPosition + 4) >= totalItemCount)) {
                isLoading = true
                ++lastPage
                loadMore(lastPage)
            }
        }
    }

    fun setDataLoaded() {
        isLoading = false
    }
}