package com.example.spirala_1.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.spirala_1.model.Kviz
import com.example.spirala_1.util.KvizStatusHelper
import java.time.format.DateTimeFormatter

@Composable
fun KvizItem(kviz: Kviz) {
    val status = KvizStatusHelper.getStatus(kviz)

    val boja = when (status) {
        KvizStatusHelper.StatusKviz.PLAVA -> Color.Blue
        KvizStatusHelper.StatusKviz.ZELENA -> Color.Green
        KvizStatusHelper.StatusKviz.ZUTA -> Color.Yellow
        KvizStatusHelper.StatusKviz.CRVENA -> Color.Red
    }

    val opisBoje = when (status) {
        KvizStatusHelper.StatusKviz.PLAVA -> "Plava"
        KvizStatusHelper.StatusKviz.ZELENA -> "Zelena"
        KvizStatusHelper.StatusKviz.ZUTA -> "Žuta"
        KvizStatusHelper.StatusKviz.CRVENA -> "Crvena"
    }

    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    val datumZaPrikaz = when (status) {
        KvizStatusHelper.StatusKviz.PLAVA -> kviz.datumRada
        KvizStatusHelper.StatusKviz.ZELENA,
        KvizStatusHelper.StatusKviz.CRVENA -> kviz.datumKraj
        KvizStatusHelper.StatusKviz.ZUTA -> kviz.datumPocetak
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .testTag("kviz_item_${kviz.naziv}"),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFB0BEC5)
        )
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = kviz.nazivPredmeta)

                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(boja, CircleShape)
                        .semantics { contentDescription = opisBoje }
                        .testTag("kviz_status_icon")
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = kviz.naziv)
                        Text(text = datumZaPrikaz?.format(formatter) ?: "")
                        Text(text = "${kviz.trajanje} min")
                    }

                    if (kviz.osvojeniBodovi != null) {
                        Text(text = kviz.osvojeniBodovi.toString())
                    }
                }
            }
        }
    }
}