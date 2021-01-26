package io.github.pengdst.githubpage.utils.text

fun String.trimLength(length: Int): String {
    return this.substring(0, kotlin.math.min(this.length, length))
}