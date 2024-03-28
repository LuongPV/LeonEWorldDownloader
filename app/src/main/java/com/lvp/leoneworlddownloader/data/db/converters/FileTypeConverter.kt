package com.lvp.leoneworlddownloader.data.db.converters

import androidx.room.TypeConverter
import com.lvp.leoneworlddownloader.data.models.FileType

class FileTypeConverter {

    @TypeConverter
    fun fromFileType(fileType: FileType): String {
        return fileType.name
    }

    @TypeConverter
    fun toFileType(value: String): FileType {
        return FileType.valueOf(value)
    }

}