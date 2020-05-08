package com.benallouch.yunar.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.benallouch.yunar.R
import com.benallouch.yunar.ui.ITEMS_PER_FETCH
import com.benallouch.yunar.ui.VIEW_TYPE_ITEM
import com.benallouch.yunar.ui.VIEW_TYPE_LOADING
import com.benallouch.yunar.ui.getCurrentOffset
import com.benallouch.yunar.ui.main.MainViewModel.UiModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ArticlesAdapter
    private val viewModel: MainViewModel by lifecycleScope.viewModel(this)
    private lateinit var recyclerViewPager: RecyclerViewPager
    private var page = 1
    private var totalItems = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initAdapter()
        setupScrollListener()
    }

    private fun setupScrollListener() {
        recyclerViewPager = RecyclerViewPager(
                recyclerView = recycler_articles,
                loadMore = { lastPage ->
                    //Don't fire a request if we reach the last items
                    if (shouldLoadMore(lastPage)) {
                        page = lastPage
                        viewModel.onDataRequested(page)
                    }
                })
        recyclerViewPager.isLoading = false
    }

    private fun initViewModel() {
        viewModel.articleModel.observe(this, Observer(::updateUi))
    }

    private fun initAdapter() {
        adapter = ArticlesAdapter(viewModel::onMovieClicked, getCurrentOffset())
        recycler_articles.setHasFixedSize(true)
        recycler_articles.adapter = adapter
        (recycler_articles.layoutManager as GridLayoutManager).spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    VIEW_TYPE_ITEM -> 1
                    VIEW_TYPE_LOADING -> 2 //number of columns of the grid
                    else -> -1
                }
            }
        }
    }

    private fun updateUi(model: UiModel) {
        progress.visibility = if (model is UiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            UiModel.LoadingMore -> adapter.addLoadingView()
            is UiModel.Content -> onDataAvailable(model)
            UiModel.RequestData -> viewModel.onDataRequested(page)
        }
    }

    private fun onDataAvailable(model: UiModel.Content) {
        adapter.removeLoadingView()
        recyclerViewPager.setDataLoaded()
        adapter.articles.addAll(model.newsResponse.articles)
        totalItems = model.newsResponse.totalResults
        adapter.notifyDataSetChanged()
    }

    private fun shouldLoadMore(lastPage: Int): Boolean {
        var maxPages = totalItems / ITEMS_PER_FETCH
        if (totalItems % ITEMS_PER_FETCH > 0)
            ++maxPages
        return lastPage <= maxPages
    }
}

