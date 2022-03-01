package com.truecaller.truefoo.common

import javax.inject.Inject

// facade class to hide complexity from ViewModel
class BlogContentParser @Inject constructor(
    private val tenthCharacterOfContent: TenthCharacterOfContent,
    private val everyTenthCharacterOfContent: EveryTenthCharacterOfContent,
    private val wordCounterOfContent: WordCounterOfContent,
){

    fun parse(content: String, contentType: BlogContentType): String {
        return when(contentType){
            BlogContentType.TENTH_CHAR -> tenthCharacterOfContent.parse(content)
            BlogContentType.EVERY_TENTH_CHAR -> everyTenthCharacterOfContent.parse(content)
            BlogContentType.WORD_COUNTER -> wordCounterOfContent.parse(content)
        }
    }
}

interface ContentParser {
    fun parse(content: String): String
}

// Find the 10th character and display it on the screen
class TenthCharacterOfContent @Inject constructor() : ContentParser {
    override fun parse(content: String): String {
        val charIndex = 9
        return if (content.length > charIndex)
            content[charIndex].toString()
        else ""
    }
}

// Find every 10th character (i.e. 10th, 20th, 30th, etc.) and display the array on the screen
class EveryTenthCharacterOfContent @Inject constructor() : ContentParser {
    override fun parse(content: String): String {
        val firstCharIndex = 9
        val sliceSize = 10
        val stringBuilder = StringBuilder()
        var counter = firstCharIndex
        val charList = content.toList()
        while (charList.size > counter) {
            stringBuilder.append(charList[counter])
            counter += sliceSize
        }
        return stringBuilder.toString()
    }
}

/*
* The whitespace characters consist of ' ', '\t', '\n', '\r', 'f', etc.
* We can use the split() function to split a char sequence around matches of a regular expression.
* To split on whitespace characters, we can use the regex '\s+' that denotes a whitespace character.
*/
class WordCounterOfContent @Inject constructor() : ContentParser {
    override fun parse(content: String): String {

        // split and count
        val mutableMap = mutableMapOf<String, Int>()
        content.trim().split("\\s+".toRegex()).map { key ->
            val count = mutableMap.getOrElse(key) { 0 }
            mutableMap[key] = count + 1
        }

        // collect and concat result
        val stringBuilder = StringBuilder()
        mutableMap.forEach { map ->
            stringBuilder.append(map.key.replace("\n", "") + " = \t" + map.value + "\n")
        }
        return stringBuilder.toString()
    }
}
