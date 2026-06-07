package ba.etf.rma26.projekat.data.repositories

import ba.etf.rma26.projekat.data.models.Pitanje
import ba.etf.rma26.projekat.data.network.ApiClient

object PitanjeKvizRepository {

    suspend fun getPitanja(idKviza: Int): List<Pitanje> {
        return try {
            ApiClient.getService().getPitanja(idKviza)
        } catch (e: Exception) {
            emptyList()
        }
    }
}