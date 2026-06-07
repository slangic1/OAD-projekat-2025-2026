package ba.etf.rma26.projekat

import ba.etf.rma26.projekat.data.models.Kviz
import java.time.LocalDateTime

object KvizStaticData {

    private val referentnoVrijeme = LocalDateTime.of(2021, 4, 10, 12, 0)

    private val sviKvizovi = listOf(
        Kviz(
            naziv = "Kviz 1",
            nazivPredmeta = "RMA",
            datumPocetak = LocalDateTime.of(2021, 4, 8, 9, 0),
            datumKraj = LocalDateTime.of(2021, 4, 12, 23, 59),
            datumRada = null,
            trajanje = 2,
            nazivGrupe = "G1",
            osvojeniBodovi = null
        ),
        Kviz(
            naziv = "Kviz 2",
            nazivPredmeta = "RMA",
            datumPocetak = LocalDateTime.of(2021, 4, 15, 9, 0),
            datumKraj = LocalDateTime.of(2021, 4, 15, 23, 59),
            datumRada = null,
            trajanje = 2,
            nazivGrupe = "G2",
            osvojeniBodovi = null
        ),
        Kviz(
            naziv = "Kviz 3",
            nazivPredmeta = "RMA",
            datumPocetak = LocalDateTime.of(2021, 3, 10, 9, 0),
            datumKraj = LocalDateTime.of(2021, 3, 10, 23, 59),
            datumRada = LocalDateTime.of(2021, 3, 10, 10, 0),
            trajanje = 2,
            nazivGrupe = "G1",
            osvojeniBodovi = 1.5f
        ),
        Kviz(
            naziv = "Kviz 4",
            nazivPredmeta = "RMA",
            datumPocetak = LocalDateTime.of(2021, 3, 5, 9, 0),
            datumKraj = LocalDateTime.of(2021, 3, 5, 23, 59),
            datumRada = null,
            trajanje = 2,
            nazivGrupe = "G2",
            osvojeniBodovi = null
        ),
        Kviz(
            naziv = "Kviz 5",
            nazivPredmeta = "DM",
            datumPocetak = LocalDateTime.of(2021, 3, 20, 9, 0),
            datumKraj = LocalDateTime.of(2021, 3, 20, 23, 59),
            datumRada = null,
            trajanje = 5,
            nazivGrupe = "G1",
            osvojeniBodovi = null
        ),
        Kviz(
            naziv = "Kviz 6",
            nazivPredmeta = "IM",
            datumPocetak = LocalDateTime.of(2021, 5, 10, 9, 0),
            datumKraj = LocalDateTime.of(2021, 5, 10, 23, 59),
            datumRada = null,
            trajanje = 5,
            nazivGrupe = "G2",
            osvojeniBodovi = null
        ),
        Kviz(
            naziv = "Kviz 7",
            nazivPredmeta = "TP",
            datumPocetak = LocalDateTime.of(2021, 4, 20, 9, 0),
            datumKraj = LocalDateTime.of(2021, 4, 20, 23, 59),
            datumRada = null,
            trajanje = 3,
            nazivGrupe = "G1",
            osvojeniBodovi = null
        )
    )

    fun getAll(): List<Kviz> {
        return sviKvizovi.sortedBy { it.datumPocetak }
    }

    fun getUpisani(): List<Kviz> {
        val upisaniNaziviPredmeta = PredmetStaticData.getUpisani().map { it.naziv }
        return sviKvizovi
            .filter { it.nazivPredmeta in upisaniNaziviPredmeta }
            .sortedBy { it.datumPocetak }
    }

    fun getDone(): List<Kviz> {
        return getUpisani()
            .filter { it.datumRada != null }
            .sortedBy { it.datumPocetak }
    }

    fun getFuture(): List<Kviz> {
        return getUpisani()
            .filter { referentnoVrijeme.isBefore(it.datumPocetak) }
            .sortedBy { it.datumPocetak }
    }

    fun getNotTaken(): List<Kviz> {
        return getUpisani()
            .filter { referentnoVrijeme.isAfter(it.datumKraj) && it.datumRada == null }
            .sortedBy { it.datumPocetak }
    }

    fun getReferentDate(): LocalDateTime {
        return referentnoVrijeme
    }
}