package com.example.countriesgraphql.utils

fun getFlagEmoji(unicode: String): String {
    return try {
        val codes = unicode.split(" ")

        val codePoint1 = codes[0].removePrefix("U+").toInt(16)
        val codePoint2 = codes[1].removePrefix("U+").toInt(16)

        // Mengonversi dari integer ke karakter Unicode
        val char1 = codePoint1.toChar()
        val char2 = codePoint2.toChar()

        "$char1$char2"
    }catch (e: Exception){
        "-"
    }
}