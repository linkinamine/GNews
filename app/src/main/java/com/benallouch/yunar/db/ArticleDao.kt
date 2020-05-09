package com.benallouch.yunar.db

import androidx.room.*

@Dao
interface ArticleDao {
    @Query("SELECT * FROM ArticleDbEntity")
    fun getAll(): List<ArticleDbEntity>

    @Query("SELECT COUNT(articleId) FROM ArticleDbEntity")
    fun articleCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<ArticleDbEntity>)

    @Update
    fun updateArticle(article: ArticleDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTotalItems(totalItems: TotalResultsEntity)

    @Query("SELECT totalResults FROM TotalResultsEntity")
    fun getTotalItems(): Int
}