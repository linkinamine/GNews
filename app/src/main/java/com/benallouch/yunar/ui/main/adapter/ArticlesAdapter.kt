package com.benallouch.yunar.ui.main.adapter

import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.benallouch.data.entity.Article
import com.benallouch.yunar.R
import com.benallouch.yunar.ui.*
import com.benallouch.yunar.ui.detail.DetailActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.article_item.view.*
import kotlinx.android.synthetic.main.progress_loading.view.*
import java.util.*

class ArticlesAdapter(private val currentOffset: Int) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var articles: ArrayList<Article?> by basicDiffUtil(
            arrayListOf(),
            areItemsTheSame = { old, new -> old?.id == new?.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == VIEW_TYPE_ITEM || viewType == VIEW_TYPE_ITEM_HEADER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
            ItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.progress_loading, parent, false)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                view.progressbar.indeterminateDrawable.colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
            } else {
                view.progressbar.indeterminateDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY)
            }
            LoadingViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (articles[position] == null) {
            VIEW_TYPE_LOADING
        } else {
            when (position) {
                0 -> VIEW_TYPE_ITEM_HEADER
                else -> VIEW_TYPE_ITEM
            }
        }
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == VIEW_TYPE_ITEM || holder.itemViewType == VIEW_TYPE_ITEM_HEADER) {
            val article = articles[position]
            article?.let { article ->
                (holder as ItemViewHolder).bind(article, currentOffset)
                holder.itemView.setOnClickListener { navigateToDetailsActivity(it, article) }
            }
        }
    }

    private fun navigateToDetailsActivity(it: View, article: Article) {
        val intent = Intent(it.context, DetailActivity::class.java).apply {
            putExtra(EXTRA_ARTICLE_URL, article.url)
        }
        it.context.startActivity(intent)
    }


    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(article: Article, currentOffset: Int) {
            with(itemView) {
                article_title.text = article.title
                article_description.text = article.description
                article_source.text = article.source.name
                article_published.text = article.publishedAt.parseDate(currentOffset)
                if (article.urlToImage != null) {
                    Glide.with(context).load(article.urlToImage).into(article_iv)
                } else {
                    article_iv.setImageDrawable(article.source.name.toTextDrawable(40, context))
                }
            }
        }
    }

    fun addLoadingView() {
        articles.add(null)
        notifyItemInserted(articles.size - 1)
    }

    fun removeLoadingView() {
        if (articles.isNotEmpty()) {
            articles.removeAt(articles.size - 1)
            notifyItemRemoved(articles.size)
        }
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
