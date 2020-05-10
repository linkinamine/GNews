package com.benallouch.yunar.db

import androidx.room.*
import com.benallouch.yunar.db.entity.ArticleDbEntity
import com.benallouch.yunar.db.entity.TotalResultsEntity

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