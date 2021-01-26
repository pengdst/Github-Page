package io.github.pengdst.githubpage.utils.number

fun Long.asFormattedDecimals() = NumberFormatUtil.format(this)

fun Int.asFormattedDecimals() = (this.toLong()).asFormattedDecimals()