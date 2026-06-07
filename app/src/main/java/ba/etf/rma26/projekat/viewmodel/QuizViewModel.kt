package ba.etf.rma26.projekat.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ba.etf.rma26.projekat.KvizStaticData
import ba.etf.rma26.projekat.data.models.Grupa
import ba.etf.rma26.projekat.data.models.Kviz
import ba.etf.rma26.projekat.data.models.KvizFilter
import ba.etf.rma26.projekat.data.models.Predmet
import ba.etf.rma26.projekat.data.repositories.KvizRepository
import ba.etf.rma26.projekat.data.repositories.PredmetIGrupaRepository
import kotlinx.coroutines.launch
import ba.etf.rma26.projekat.data.models.Pitanje
import ba.etf.rma26.projekat.data.models.KvizTaken
import ba.etf.rma26.projekat.data.repositories.PitanjeKvizRepository
import ba.etf.rma26.projekat.data.repositories.TakeKvizRepository
import ba.etf.rma26.projekat.data.repositories.OdgovorRepository

class QuizViewModel : ViewModel() {

    var odabraniFilter = mutableStateOf(KvizFilter.SVI_MOJI)
        private set

    var kvizovi = mutableStateOf<List<Kviz>>(emptyList())
        private set

    var predmeti = mutableStateOf<List<Predmet>>(emptyList())
        private set

    var grupe = mutableStateOf<List<Grupa>>(emptyList())
        private set

    var poruka = mutableStateOf("")
        private set
    var pitanja = mutableStateOf<List<Pitanje>>(emptyList())
        private set

    var trenutniKvizTaken = mutableStateOf<KvizTaken?>(null)
        private set

    var rezultatOdgovora = mutableStateOf(-1)
        private set

    init {
        ucitajPocetnePodatke()
    }

    fun promijeniFilter(filter: KvizFilter) {
        odabraniFilter.value = filter
        ucitajKvizove()
    }

    fun ucitajPocetnePodatke() {
        viewModelScope.launch {
            predmeti.value = PredmetIGrupaRepository.getPredmeti()
            grupe.value = PredmetIGrupaRepository.getGrupe()

            println("PREDMETI = ${predmeti.value.size}")
            println("GRUPE = ${grupe.value.size}")

            ucitajKvizove()
        }
    }

    fun ucitajKvizove() {
        viewModelScope.launch {
            kvizovi.value = when (odabraniFilter.value) {
                KvizFilter.SVI_MOJI -> KvizRepository.getUpisani()
                KvizFilter.SVI -> KvizRepository.getAll()
                KvizFilter.URADJENI -> KvizRepository.getUpisani().filter { it.datumRada != null }
                KvizFilter.BUDUCI -> KvizRepository.getUpisani().filter {
                    it.datumPocetak != null && KvizStaticData.getReferentDate().isBefore(it.datumPocetak)
                }

                KvizFilter.PROSLI -> KvizRepository.getUpisani().filter {
                    it.datumKraj != null && KvizStaticData.getReferentDate().isAfter(it.datumKraj) && it.datumRada == null
                }
            }
        }
    }

    fun upisiPredmet(predmet: Predmet, grupa: Grupa) {
        viewModelScope.launch {
            val uspjesno = PredmetIGrupaRepository.upisiUGrupu(grupa.id)

            if (uspjesno) {
                poruka.value = "Uspješno ste upisani u grupu."
                ucitajPocetnePodatke()
            } else {
                poruka.value = "Upis nije uspio."
            }
        }
    }

    fun getKvizoviZaTrenutniFilter(): List<Kviz> {
        return kvizovi.value
    }

    fun getBrojKvizova(): Int {
        return kvizovi.value.size
    }

    fun getPredmetiZaGodinu(godina: Int): List<Predmet> {
        return predmeti.value.filter { it.godina == godina }
    }

    fun getGrupeZaPredmet(idPredmeta: Int): List<Grupa> {
        return grupe.value.filter { it.idPredmeta == idPredmeta }
    }
    fun zapocniKviz(idKviza: Int) {
        viewModelScope.launch {
            val pokusaj = TakeKvizRepository.zapocniKviz(idKviza)
            trenutniKvizTaken.value = pokusaj
            pitanja.value = PitanjeKvizRepository.getPitanja(idKviza)
        }
    }

    fun postaviOdgovor(idPitanje: Int, odgovor: Int) {
        viewModelScope.launch {
            val idTaken = trenutniKvizTaken.value?.id ?: return@launch

            rezultatOdgovora.value = OdgovorRepository.postaviOdgovorKviz(
                idKvizTaken = idTaken,
                idPitanje = idPitanje,
                odgovor = odgovor
            )
        }
    }
}