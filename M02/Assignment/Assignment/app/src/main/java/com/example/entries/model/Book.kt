package com.example.entries.model

class Book {
    var title: String? = null
    var reasonToRead: String? = null
    var hasBeenRead: Boolean = false
    var id: String? = null

    constructor(title: String, reasonToRead: String, hasBeenRead: Boolean, id: String){
        this.title = title
        this.reasonToRead = reasonToRead
        this.hasBeenRead = hasBeenRead
        this.id = id
    }

    constructor(csvString: String){
        val parameters = csvString.split(",")

        if (parameters.size == 4){
            this.title = parameters[0]
            this.reasonToRead = parameters[1].replace("~@~", ",")
            this.hasBeenRead = parameters[2].toBoolean()
            this.id = parameters[3]
        }
    }

    fun toCsvString(): String{
        val reasonToReadWithoutCommas = reasonToRead?.replace(",", "~@~")
        return "$title,$reasonToReadWithoutCommas,$hasBeenRead,$id"
    }
}