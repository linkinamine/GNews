package com.benallouch.yunar.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.benallouch.data.entity.Article
import com.benallouch.yunar.R
import com.benallouch.yunar.ui.basicDiffUtil
import com.benallouch.yunar.ui.inflate
import com.benallouch.yunar.ui.parseDate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.article_item.view.*


class ArticlesAdapter(private val listener: (Article) -> Unit) :
        RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    var articles: List<Article> by basicDiffUtil(
            emptyList(),
            areItemsTheSame = { old, new -> old.source.id == new.source.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.article_item, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
        holder.itemView.setOnClickListener { listener(article) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(article: Article) {
            with(itemView) {
                article_title.text = article.title
                article_description.text = article.description
                article_source.text = article.source.name
                article_published.text = article.publishedAt.parseDate()
                Glide.with(context).load(article.urlToImage).into(article_iv)
            }
        }
    }
}
