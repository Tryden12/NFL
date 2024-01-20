package com.tryden.simplenfl.domain

import android.util.Log


/**
 * Parse through the html and add <br> tags after every closing </p>
 * to add a gap between paragraphs.
 */
fun addBrTags(html: String) : String {
    val builder = StringBuilder()
    builder.append(html)
    for (i in builder.indices) {
        if(builder[i] == 'p' && builder[i-1] == '/') {
            builder.insert(i+2, "<br>")
        }
    }
    return builder.toString()
}

/**
 * To navigate on click from headline to view the article,
 * we must grab the article id in the given url.
 *
 */
fun getArticleIdFromUrl(url: String): String {
    val id = url.split("sports/news/")[1]
    Log.d("getArticleIdFromUrl()", "URL: $url , articleID: $id")
    return id
}
