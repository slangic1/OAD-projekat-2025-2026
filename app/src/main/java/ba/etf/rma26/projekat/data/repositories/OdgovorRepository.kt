package ba.etf.rma26.projekat.data.repositories

import ba.etf.rma26.projekat.data.models.Odgovor
import ba.etf.rma26.projekat.data.models.OdgovorRequest
import ba.etf.rma26.projekat.data.network.ApiClient

object OdgovorRepository {

    suspend fun getOdgovoriKviz(idKviza: Int): List<Odgovor> {
        return try {
            val hash = AccountRepository.getHash()
            ApiClient.getService().getOdgovoriKviz(hash, idKviza)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun postaviOdgovorKviz(
        idKvizTaken: Int,
        idPitanje: Int,
        odgovor: Int
    ): Int {
        return try {
            val hash = AccountRepository.getHash()
            ApiClient.getService().postaviOdgovor(
                hash = hash,
                idKvizTaken = idKvizTaken,
                odgovor = OdgovorRequest(
                    idPitanje = idPitanje,
                    odgovor = odgovor
                )
            )
        } catch (e: Exception) {
            -1
        }
    }
}