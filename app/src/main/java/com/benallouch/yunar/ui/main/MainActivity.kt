package com.benallouch.yunar.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.benallouch.yunar.R
import com.benallouch.yunar.ui.getCurrentOffset
import com.benallouch.yunar.ui.main.MainViewModel.UiModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ArticlesAdapter
    private val viewModel: MainViewModel by lifecycleScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdapter()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.articleModel.observe(this, Observer(::updateUi))
    }

    private fun initAdapter() {
        adapter = ArticlesAdapter(viewModel::onMovieClicked, getCurrentOffset())
        recycler_articles.adapter = adapter
    }

    private fun updateUi(model: UiModel) {
        progress.visibility = if (model is UiModel.Loading) View.VISIBLE else View.GONE
        when (model) {
            is UiModel.Content -> adapter.articles = model.articles
            UiModel.RequestData -> viewModel.onDataRequested()
        }
    }

}

