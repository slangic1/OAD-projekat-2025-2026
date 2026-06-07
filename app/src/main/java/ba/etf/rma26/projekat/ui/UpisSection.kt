package ba.etf.rma26.projekat.ui

import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import ba.etf.rma26.projekat.data.models.Grupa
import ba.etf.rma26.projekat.data.models.Predmet
import ba.etf.rma26.projekat.viewmodel.QuizViewModel

@Composable
fun UpisSection(
    viewModel: QuizViewModel
) {
    var odabranaGodina by rememberSaveable { mutableStateOf(1) }
    var odabraniPredmet by remember { mutableStateOf<Predmet?>(null) }
    var odabranaGrupa by remember { mutableStateOf<Grupa?>(null) }

    var expandedGodina by remember { mutableStateOf(false) }
    var expandedPredmet by remember { mutableStateOf(false) }
    var expandedGrupa by remember { mutableStateOf(false) }

    val godine = listOf(1, 2, 3, 4, 5)
    val predmeti = viewModel.getPredmetiZaGodinu(odabranaGodina)

    val grupe = odabraniPredmet?.let {
        viewModel.getGrupeZaPredmet(it.id)
    } ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("odabirGodina")
        ) {
            OutlinedButton(
                onClick = { expandedGodina = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Godina: $odabranaGodina ▼")
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .testTag("odabirPredmet")
        ) {
            OutlinedButton(
                onClick = { expandedPredmet = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Predmet: ${odabraniPredmet?.naziv ?: ""} ▼")
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .testTag("odabirGrupa")
        ) {
            OutlinedButton(
                onClick = { expandedGrupa = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Grupa: ${odabranaGrupa?.naziv ?: ""} ▼")
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
                    viewModel.upisiPredmet(odabraniPredmet!!, odabranaGrupa!!)
                    odabraniPredmet = null
                    odabranaGrupa = null
                }
            },
            enabled = odabraniPredmet != null && odabranaGrupa != null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .testTag("dodajPredmetDugme")
        ) {
            Text("Upiši me")
        }
    }
}