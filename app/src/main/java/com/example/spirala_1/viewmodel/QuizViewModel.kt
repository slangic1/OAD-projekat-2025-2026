package com.example.spirala_1.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.spirala_1.data.KvizStaticData
import com.example.spirala_1.data.PredmetStaticData
import com.example.spirala_1.data.GrupaStaticData
import com.example.spirala_1.model.Grupa
import com.example.spirala_1.model.Kviz
import com.example.spirala_1.model.KvizFilter
import com.example.spirala_1.model.Predmet

class QuizViewModel : ViewModel() {

    var odabraniFilter = mutableStateOf(KvizFilter.SVI_MOJI)
        private set

    var refresh = mutableStateOf(0)
        private set

    fun promijeniFilter(filter: KvizFilter) {
        odabraniFilter.value = filter
    }

    fun upisiPredmet(predmet: Predmet, grupa: Grupa) {
        PredmetStaticData.dodajUpisaniPredmet(predmet)
        GrupaStaticData.dodajUpisanuGrupu(grupa)
        refresh.value++
    }

    fun getKvizoviZaTrenutniFilter(): List<Kviz> {
        refresh.value

        return when (odabraniFilter.value) {
            KvizFilter.SVI_MOJI -> KvizStaticData.getUpisani()
            KvizFilter.SVI -> KvizStaticData.getAll()
            KvizFilter.URADJENI -> KvizStaticData.getDone()
            KvizFilter.BUDUCI -> KvizStaticData.getFuture()
            KvizFilter.PROSLI -> KvizStaticData.getNotTaken()
        }
    }

    fun getBrojKvizova(): Int {
        return getKvizoviZaTrenutniFilter().size
    }
}