package com.example.spirala_1.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.spirala_1.data.KvizStaticData
import com.example.spirala_1.model.KvizFilter

@Composable
fun MainScreen() {
    var odabraniFilter by remember { mutableStateOf(KvizFilter.SVI_MOJI) }
    var refreshTrigger by remember { mutableIntStateOf(0) }

    refreshTrigger

    val kvizovi = when (odabraniFilter) {
        KvizFilter.SVI_MOJI -> KvizStaticData.getUpisani()
        KvizFilter.SVI -> KvizStaticData.getAll()
        KvizFilter.URADJENI -> KvizStaticData.getDone()
        KvizFilter.BUDUCI -> KvizStaticData.getFuture()
        KvizFilter.PROSLI -> KvizStaticData.getNotTaken()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        UpisSection(
            onUpisCompleted = {
                refreshTrigger++
            }
        )

        KvizFilterDropdown(
            odabraniFilter = odabraniFilter,
            onFilterChange = { odabraniFilter = it }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .testTag("listaKvizova")
        ) {
            items(kvizovi) { kviz ->
                KvizItem(kviz)
            }
        }
    }
}