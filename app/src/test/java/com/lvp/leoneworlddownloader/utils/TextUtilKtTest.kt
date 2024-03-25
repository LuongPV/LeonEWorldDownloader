package com.lvp.leoneworlddownloader.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TextUtilKtTest {

    @Test
    fun testGetHumanReadableFileSize() {
        assertEquals("2.9 kB", getHumanReadableFileSize(3000))
        assertEquals("2.9 MB", getHumanReadableFileSize(3000000))
        assertEquals("2.8 GB", getHumanReadableFileSize(3000000000))
        assertEquals("2.7 TB", getHumanReadableFileSize(3000000000000))
    }

}