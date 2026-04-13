package com.example.spirala_1.util

import com.example.spirala_1.model.Kviz
import java.time.LocalDateTime

enum class StatusKviz {
    PLAVA, ZELENA, ZUTA, CRVENA
}

fun odrediStatus(kviz: Kviz, referentnoVrijeme: LocalDateTime): StatusKviz {
    return when {
        kviz.datumRada != null -> StatusKviz.PLAVA
        referentnoVrijeme.isBefore(kviz.datumPocetak) -> StatusKviz.ZUTA
        referentnoVrijeme.isAfter(kviz.datumKraj) -> StatusKviz.CRVENA
        else -> StatusKviz.ZELENA
    }
}