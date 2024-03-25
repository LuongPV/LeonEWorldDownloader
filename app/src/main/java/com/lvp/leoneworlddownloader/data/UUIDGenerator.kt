package com.lvp.leoneworlddownloader.data

import java.util.UUID
import javax.inject.Inject

class UUIDGenerator @Inject constructor() : IdGenerator {

    override fun generateString(): String {
        return UUID.randomUUID().toString()
    }

}