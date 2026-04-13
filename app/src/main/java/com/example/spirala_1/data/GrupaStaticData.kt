package com.example.spirala_1.data

import com.example.spirala_1.model.Grupa

object GrupaStaticData {

    private val sveGrupe = listOf(
        Grupa("G1", "RMA"),
        Grupa("G2", "RMA"),
        Grupa("G1", "DM"),
        Grupa("G2", "DM"),
        Grupa("G1", "IM"),
        Grupa("G2", "IM"),
        Grupa("G1", "TP"),
        Grupa("G2", "TP")
    )

    private val upisaneGrupe = mutableListOf(
        Grupa("G1", "RMA")
    )

    fun getGrupaFromPredmet(nazivPredmeta: String): List<Grupa> {
        return sveGrupe.filter { it.nazivPredmeta == nazivPredmeta }
    }

    fun getUpisane(): List<Grupa> {
        return upisaneGrupe.toList()
    }

    fun dodajUpisanuGrupu(grupa: Grupa) {
        if (upisaneGrupe.none {
                it.naziv == grupa.naziv && it.nazivPredmeta == grupa.nazivPredmeta
            }) {
            upisaneGrupe.add(grupa)
        }
    }
}