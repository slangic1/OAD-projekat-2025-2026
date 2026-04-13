package com.example.spirala_1.util

import com.example.spirala_1.model.Kviz
import com.example.spirala_1.data.KvizStaticData

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