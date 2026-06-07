package ba.etf.rma26.projekat.data.repositories

import ba.etf.rma26.projekat.data.models.Kviz
import ba.etf.rma26.projekat.data.network.ApiClient

object KvizRepository {

    suspend fun getAll(): List<Kviz> {
        return try {
            ApiClient.getService().getKvizovi()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getById(id: Int): Kviz? {
        return try {
            val response = ApiClient.getService().getKvizById(id)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getUpisani(): List<Kviz> {
        return try {
            val hash = AccountRepository.getHash()
            ApiClient.getService().getUpisaniKvizovi(hash)
        } catch (e: Exception) {
            emptyList()
        }
    }
}