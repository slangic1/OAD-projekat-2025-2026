package ba.etf.rma26.projekat.util

import ba.etf.rma26.projekat.data.models.Kviz
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