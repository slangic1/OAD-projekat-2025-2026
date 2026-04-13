package com.example.spirala_1.data

import com.example.spirala_1.model.Predmet

object PredmetStaticData {

    private val sviPredmeti = listOf(
        Predmet("RMA", 3),
        Predmet("DM", 1),
        Predmet("IM", 2),
        Predmet("TP", 1)
    )

    private val upisaniPredmeti = mutableListOf(
        Predmet("RMA", 3)
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