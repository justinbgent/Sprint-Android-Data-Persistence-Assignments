package com.lambdaschool.sharedprefs.database

import android.content.Context
import androidx.room.Room
import com.lambdaschool.sharedprefs.JournalRepoInterface
import com.lambdaschool.sharedprefs.model.JournalEntry

// TODO 5: Create the Database repo and implement the methods
class JournalDBRepo(context: Context): JournalRepoInterface{

    private val context = context.applicationContext

    override fun createEntry(entry: JournalEntry) {
//        if (hasInternet){
//            retrofit.createEntry(entry).enqueue(object : Callback<>){
//                onResponse(response: Response<JournalEntry>){
//                database.entriesDao().createEntry(response.body())
//            }
//            }
//        }else{
//            database.entriesDao().createEntry(entry)
//        }
        database.entriesDao().createEntry(entry)
    }

    override fun readAllEntries(): MutableList<JournalEntry> {
        return database.entriesDao().readAllEntries()
    }

    override fun updateEntry(entry: JournalEntry) {
        database.entriesDao().updateEntry(entry)
    }

    override fun deleteEntry(entry: JournalEntry) {
        database.entriesDao().deleteEntry(entry)
    }


    // TODO 15: Build the Room database
    private val database by lazy {
        Room.databaseBuilder(
            context,
            JournalEntryDB::class.java, "entry_database"
        ).fallbackToDestructiveMigration().build() //this is probably something you don't want to do
    }

//    private val retrofit: Retrofit
}

