package ba.etf.rma26.projekat

import ba.etf.rma26.projekat.data.models.Grupa

object GrupaStaticData {

    private val sveGrupe = listOf(
        Grupa(id = 1, naziv = "G1", idPredmeta = 1),
        Grupa(id = 2, naziv = "G2", idPredmeta = 1),
        Grupa(id = 3, naziv = "G1", idPredmeta = 2),
        Grupa(id = 4, naziv = "G2", idPredmeta = 2),
        Grupa(id = 5, naziv = "G1", idPredmeta = 3),
        Grupa(id = 6, naziv = "G2", idPredmeta = 3),
        Grupa(id = 7, naziv = "G1", idPredmeta = 4),
        Grupa(id = 8, naziv = "G2", idPredmeta = 4)
    )

    private val upisaneGrupe = mutableListOf(
        Grupa(id = 1, naziv = "G1", idPredmeta = 1)
    )

    fun getAll(): List<Grupa> {
        return sveGrupe
    }

    fun getGrupaFromPredmet(idPredmeta: Int): List<Grupa> {
        return sveGrupe.filter { it.idPredmeta == idPredmeta }
    }

    fun getUpisane(): List<Grupa> {
        return upisaneGrupe.toList()
    }

    fun dodajUpisanuGrupu(grupa: Grupa) {
        if (upisaneGrupe.none { it.id == grupa.id }) {
            upisaneGrupe.add(grupa)
        }
    }
}