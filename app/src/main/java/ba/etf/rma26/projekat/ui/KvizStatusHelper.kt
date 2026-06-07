package ba.etf.rma26.projekat.util

import ba.etf.rma26.projekat.data.models.Kviz
import ba.etf.rma26.projekat.KvizStaticData

object KvizStatusHelper {

    enum class StatusKviz {
        PLAVA, ZELENA, ZUTA, CRVENA
    }

    fun getStatus(kviz: Kviz): StatusKviz {
        val now = KvizStaticData.getReferentDate()

        return when {
            kviz.datumRada != null -> StatusKviz.PLAVA
            now.isBefore(kviz.datumPocetak) -> StatusKviz.ZUTA
            now.isAfter(kviz.datumKraj) -> StatusKviz.CRVENA
            else -> StatusKviz.ZELENA
        }
    }
}