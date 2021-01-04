package io.github.pengdst.githubpage.util.extensions

import io.github.pengdst.githubpage.util.NumberFormatUtil

fun Long.asFormattedDecimals() = NumberFormatUtil.format(this)

fun Int.asFormattedDecimals() = (this.toLong()).asFormattedDecimals()