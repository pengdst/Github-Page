package io.github.pengdst.githubpage.utils.number

import java.util.*

object NumberFormatUtil {

    private val suffixes = TreeMap<Long, String>().apply {
        put(1_000L, "k")
        put(1_000_000L, "M")
        put(1_000_000_000L, "G")
        put(1_000_000_000_000L, "T")
        put(1_000_000_000_000_000L, "P")
        put(1_000_000_000_000_000_000L, "E")
    }

    fun format(value: Long): String? {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1)
        if (value < 0) return "-" + format(-value)
        if (value < 1000) return value.toString() //deal with easy case
        val e = suffixes.floorEntry(value)

        return e?.let {
            val divideBy = e.key
            val suffix = e.value
            val truncated = value / (divideBy / 10) //the number part of the output times 10
            val hasDecimal = truncated < 100 && truncated / 10.0 != (truncated / 10).toDouble()
            if (hasDecimal) (truncated / 10.0).toString() + suffix
            else (truncated / 10).toString() + suffix
        }
    }

}