package ba.etf.rma26.projekat.data.models

import java.time.LocalDateTime

data class Kviz(
    val id: Int = 0,
    val naziv: String,
    val idPredmeta: Int? = null,
    val idGrupe: Int? = null,
    val nazivPredmeta: String? = null,
    val datumPocetka: String? = null,
    val datumPocetak: LocalDateTime? = null,
    val datumKraj: LocalDateTime? = null,
    val datumRada: LocalDateTime? = null,
    val trajanje: Int,
    val nazivGrupe: String? = null,
    val osvojeniBodovi: Float? = null
)