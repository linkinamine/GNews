package com.benallouch.yunar.ui.main.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @param loadMore lambda function to trigger a new api call with the next page
 * @param recyclerView our Recyclerview to which we attach the pagination
 */
class RecyclerViewPager(
        private val recyclerView: RecyclerView,
        private val loadMore: (Int) -> Unit
) : RecyclerView.OnScrollListener() {

    var isLoading = false
    var lastPage: Int = 1
    var allItemsSize = 0

    init {
        recyclerView.addOnScrollListener(this)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy <= 0) return

        val layoutManager = recyclerView.layoutManager
        layoutManager?.let {
            val totalItemCountInLayout = it.itemCount
            val lastVisibleItemPosition = when (layoutManager) {
                is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
                else -> return
            }

            if (isLoading) return

            if (lastVisibleItemPosition + 4 >= allItemsSize) return

            if (!isLoading && ((lastVisibleItemPosition + 4) >= totalItemCountInLayout)) {
                isLoading = true
                ++lastPage
                loadMore(lastPage)
            }
        }
    }

    fun setDataLoaded(totalItems: Int) {
        allItemsSize = totalItems
        isLoading = false
    }
}