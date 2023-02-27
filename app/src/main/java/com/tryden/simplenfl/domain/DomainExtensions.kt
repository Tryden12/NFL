package com.tryden.simplenfl.domain


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
