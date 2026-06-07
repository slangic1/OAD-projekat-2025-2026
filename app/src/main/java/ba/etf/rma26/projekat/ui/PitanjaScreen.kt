package ba.etf.rma26.projekat.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ba.etf.rma26.projekat.viewmodel.QuizViewModel

@Composable
fun PitanjaScreen(
    viewModel: QuizViewModel,
    idKviza: Int
) {
    val pitanja = viewModel.pitanja.value
    val rezultat = viewModel.rezultatOdgovora.value

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {
            viewModel.zapocniKviz(idKviza)
        }) {
            Text("Započni kviz")
        }

        pitanja.forEach { pitanje ->
            Text(text = pitanje.tekstPitanja)

            pitanje.opcije.forEachIndexed { index, opcija ->
                Button(onClick = {
                    viewModel.postaviOdgovor(pitanje.id, index)
                }) {
                    Text(opcija)
                }
            }
        }

        Text(text = "Rezultat: $rezultat")
    }
}