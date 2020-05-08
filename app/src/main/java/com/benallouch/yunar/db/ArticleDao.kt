package com.benallouch.yunar.db

import androidx.room.*
import com.benallouch.data.entity.Article

@Dao
interface ArticleDao {
    @Query("SELECT * FROM ArticleDbEntity")
    fun getAll(): List<Article>

    @Query("SELECT * FROM ArticleDbEntity WHERE id = :id")
    fun findById(id: String): Article

    @Query("SELECT COUNT(id) FROM ArticleDbEntity")
    fun articleCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<ArticleDbEntity>)

    @Update
    fun updateArticle(article: ArticleDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTotalItems(totalItems: Int)

    @Query("SELECT totalResults FROM TotalResultsEntity")
    fun getTotalItems(): Int
}