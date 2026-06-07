package ba.etf.rma26.projekat

import ba.etf.rma26.projekat.data.models.Predmet

object PredmetStaticData {

    private val sviPredmeti = listOf(
        Predmet(id = 1, naziv = "RMA", godina = 3),
        Predmet(id = 2, naziv = "DM", godina = 1),
        Predmet(id = 3, naziv = "IM", godina = 2),
        Predmet(id = 4, naziv = "TP", godina = 1)
    )

    private val upisaniPredmeti = mutableListOf(
        Predmet(id = 1, naziv = "RMA", godina = 3)
    )

    fun getAll(): List<Predmet> {
        return sviPredmeti
    }

    fun getUpisani(): List<Predmet> {
        return upisaniPredmeti.toList()
    }

    fun dodajUpisaniPredmet(predmet: Predmet) {
        if (upisaniPredmeti.none { it.naziv == predmet.naziv }) {
            upisaniPredmeti.add(predmet)
        }
    }

    fun getNeupisaniSaGodine(godina: Int): List<Predmet> {
        return sviPredmeti.filter { predmet ->
            predmet.godina == godina &&
                    upisaniPredmeti.none { it.naziv == predmet.naziv }
        }
    }
}