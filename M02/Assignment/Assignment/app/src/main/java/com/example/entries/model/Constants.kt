package com.example.entries.model

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import com.example.entries.BookRepoInterface
import com.example.entries.SharedPrefOperations
import com.example.entries.SharedPrefsDao
import com.example.entries.activity.EditBookActivity
import com.example.entries.activity.MainActivity

object Constants {
    const val STRING_KEY = "STRING_KEY"
    const val ID_KEY = "ID_KEY"
    const val ADD_BOOK = 345
    const val EDIT_BOOK = 545
    const val USER_PREFERENCES = "USER_PREFERENCES"

    var bookList = mutableListOf<Book>()
}