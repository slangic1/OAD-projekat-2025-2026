package com.example.spirala_1.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.spirala_1.data.GrupaStaticData
import com.example.spirala_1.data.PredmetStaticData
import com.example.spirala_1.model.Grupa
import com.example.spirala_1.model.Predmet

@Composable
fun UpisSection(
    onUpisCompleted: () -> Unit
) {
    var odabranaGodina by remember { mutableIntStateOf(1) }
    var odabraniPredmet by remember { mutableStateOf<Predmet?>(null) }
    var odabranaGrupa by remember { mutableStateOf<Grupa?>(null) }

    var expandedGodina by remember { mutableStateOf(false) }
    var expandedPredmet by remember { mutableStateOf(false) }
    var expandedGrupa by remember { mutableStateOf(false) }

    val godine = listOf(1, 2, 3, 4, 5)
    val predmeti = PredmetStaticData.getNeupisaniSaGodine(odabranaGodina)
    val grupe = if (odabraniPredmet != null) {
        GrupaStaticData.getGrupaFromPredmet(odabraniPredmet!!.naziv)
    } else {
        emptyList()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text("Godina")
        androidx.compose.foundation.layout.Box {
            OutlinedButton(
                onClick = { expandedGodina = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("odabirGodina")
            ) {
                Text(odabranaGodina.toString())
            }

            DropdownMenu(
                expanded = expandedGodina,
                onDismissRequest = { expandedGodina = false }
            ) {
                godine.forEach { godina ->
                    DropdownMenuItem(
                        text = { Text(godina.toString()) },
                        onClick = {
                            odabranaGodina = godina
                            odabraniPredmet = null
                            odabranaGrupa = null
                            expandedGodina = false
                        }
                    )
                }
            }
        }

        Text("Predmet")
        androidx.compose.foundation.layout.Box {
            OutlinedButton(
                onClick = { expandedPredmet = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("odabirPredmet")
            ) {
                Text(odabraniPredmet?.naziv ?: "Odaberi predmet")
            }

            DropdownMenu(
                expanded = expandedPredmet,
                onDismissRequest = { expandedPredmet = false }
            ) {
                predmeti.forEach { predmet ->
                    DropdownMenuItem(
                        text = { Text(predmet.naziv) },
                        onClick = {
                            odabraniPredmet = predmet
                            odabranaGrupa = null
                            expandedPredmet = false
                        }
                    )
                }
            }
        }

        Text("Grupa")
        androidx.compose.foundation.layout.Box {
            OutlinedButton(
                onClick = { expandedGrupa = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("odabirGrupa")
            ) {
                Text(odabranaGrupa?.naziv ?: "Odaberi grupu")
            }

            DropdownMenu(
                expanded = expandedGrupa,
                onDismissRequest = { expandedGrupa = false }
            ) {
                grupe.forEach { grupa ->
                    DropdownMenuItem(
                        text = { Text(grupa.naziv) },
                        onClick = {
                            odabranaGrupa = grupa
                            expandedGrupa = false
                        }
                    )
                }
            }
        }

        Button(
            onClick = {
                if (odabraniPredmet != null && odabranaGrupa != null) {
                    PredmetStaticData.dodajUpisaniPredmet(odabraniPredmet!!)
                    GrupaStaticData.dodajUpisanuGrupu(odabranaGrupa!!)
                    onUpisCompleted()

                    odabraniPredmet = null
                    odabranaGrupa = null
                }
            },
            enabled = odabraniPredmet != null && odabranaGrupa != null,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("dodajPredmetDugme")
        ) {
            Text("Upiši me")
        }
    }
}