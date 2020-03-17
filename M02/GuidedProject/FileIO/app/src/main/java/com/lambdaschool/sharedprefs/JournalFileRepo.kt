package com.lambdaschool.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Environment
import com.lambdaschool.sharedprefs.model.JournalEntry
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.lang.StringBuilder

// TODO 3: Implement the interface here
class JournalFileRepo(var context: Context) : JournalRepoInterface {
    override fun updateEntry(entry: JournalEntry) {
    }

    override fun deleteEntry(entry: JournalEntry) {
    }
    // Basic structure: We will save each object to its own json file

    // TODO 6: createEntry implementation
    override fun createEntry(entry: JournalEntry) {
        val entryString = entry.toJsonObject()
        val filename = "jornalEntry${entry.date}.json"
        writeToFile(filename, entryString.toString())
    }

    // TODO 8: writeToFile helper
    private fun writeToFile(filename: String, entryString: String) {
        val dir = storageDirectory
        val outputFile = File(dir, filename)

        //open filewriter
        var writer: FileWriter? = null
        try {
            //write
            writer = FileWriter(outputFile)
            writer.write(entryString)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (writer != null) {
                try {
                    //close
                    writer.close()
                } catch (e2: IOException) {
                    e2.printStackTrace()
                }
            }
        }
    }

    // TODO 9: Save storage directory as a member variable
    private val storageDirectory: File
        get() {
            if (isExternalStorageWriteable) {
                val directory = context.filesDir
                return if (!directory.exists() && !directory.mkdirs()) {
                    context.cacheDir
                } else {
                    directory
                }
            } else {
                return context.cacheDir
            }

//            context?.filesDir
//            context.getExternalFilesDir()
//            context.cacheDir
        }

    // TODO 10: Check for external storage is writeable
    private val isExternalStorageWriteable: Boolean
        get(){
            val state = Environment.getExternalStorageState()
            return state == Environment.MEDIA_MOUNTED
        }

    override fun readAllEntries(): MutableList<JournalEntry> {
        // get filelist
        val entries = ArrayList<JournalEntry>()

        //setup ArrayList
        //read in files and convert to objects
        for (filename in filelist) {
            val json = readFromFile(filename)
            try {
                entries.add(JournalEntry(JSONObject(json)))
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return entries
    }

    // TODO 12: Save fileList as a member variable
    val filelist: ArrayList<String>
        get() {
            val fileNames = arrayListOf<String>()
            val dir = storageDirectory

            val list = dir.list()
            if (list != null) {
                for (name in list) {
                    if (name.contains(".json")) {
                        fileNames.add(name)
                    }
                }
            }
            return fileNames
        }

    // TODO 13: readFromFile helper
    private fun readFromFile(filename: String): String {
        val inputFile = File(storageDirectory, filename)
        var readString: String? = null
        var reader: FileReader? = null
        try {
            reader = FileReader(inputFile)
            readString = reader.readText()
//            while (next != -1){
//                readData.append(next)
//                next = reader.read()
//            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return readString ?: ""
    }

    // TODO 14: updateEntry implementation

    // TODO 15: deleteEntry implementation


}