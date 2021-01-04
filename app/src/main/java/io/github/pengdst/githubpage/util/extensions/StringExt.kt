package io.github.pengdst.githubpage.util.extensions

fun String.trimLength(length: Int): String {
    return this.substring(0, kotlin.math.min(this.length, length))
}