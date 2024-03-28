package com.lvp.leoneworlddownloader.data.db.converters

import androidx.room.TypeConverter
import com.lvp.leoneworlddownloader.utils.formatDateTime
import com.lvp.leoneworlddownloader.utils.parseDateTime
import java.time.LocalDateTime

class DateTimeConverter {

    @TypeConverter
    fun fromDateTime(dateTime: LocalDateTime): String {
        return formatDateTime(dateTime)
    }

    @TypeConverter
    fun toDateTime(value: String): LocalDateTime {
        return parseDateTime(value)
    }

}