package com.example.entries.db

import androidx.room.*
import com.example.entries.model.Book

@Dao
interface InterfaceDAO {
    @Query("SELECT * FROM book")
    fun updateBookList(): MutableList<Book>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun createBook(book: Book)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateBook(book: Book)

    @Delete
    fun deleteBook(book: Book)
}