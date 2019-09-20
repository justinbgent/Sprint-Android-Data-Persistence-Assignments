package com.example.entries.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.json.JSONException
import org.json.JSONObject

@Entity
class Book {
    var title: String? = null
    var reasonToRead: String? = null
    var hasBeenRead: Boolean = false
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor(title: String, reasonToRead: String, hasBeenRead: Boolean, id: String){
        this.title = title
        this.reasonToRead = reasonToRead
        this.hasBeenRead = hasBeenRead
        this.id = id.toInt()
    }

    constructor(csvString: String){
        val parameters = csvString.split(",")

        if (parameters.size == 4){
            this.title = parameters[0]
            this.reasonToRead = parameters[1].replace("~@~", ",")
            this.hasBeenRead = parameters[2].toBoolean()
            this.id = parameters[3].toInt()
        }
    }

    constructor(jsonObject: JSONObject){
        try {
            this.title = jsonObject.getString("title")
        }catch (e: JSONException){
            this.title = ""
        }
        try {
            this.reasonToRead = jsonObject.getString("reason")
        }catch (e: JSONException){
            this.reasonToRead = ""
        }
        try {
            this.hasBeenRead = jsonObject.getBoolean("has_been_read")
        }catch (e: JSONException){
            this.hasBeenRead = false
        }
        try {
            this.id = jsonObject.getString("id").toInt()
        }catch (e: JSONException){
            this.id = "0".toInt()
        }
    }

    fun toCsvString(): String{
        val reasonToReadWithoutCommas = reasonToRead?.replace(",", "~@~")
        return "$title,$reasonToReadWithoutCommas,$hasBeenRead,$id"
    }

    fun toJsonObject(): JSONObject? {
        try {
            return JSONObject().apply {
                put("title", title)
                put("reason", reasonToRead)
                put("has_been_read", hasBeenRead)
                put("id", id)
            }
        } catch (e: JSONException) {
            return try {
                JSONObject("{\"title\" : \"$title\", \"reason\" : \"$reasonToRead\", \"has_been_read\": \"$hasBeenRead\", \"id\" : \"$id\"}")
            }catch (e2: JSONException){
                e2.printStackTrace()
                return null
            }
        }

    }
}