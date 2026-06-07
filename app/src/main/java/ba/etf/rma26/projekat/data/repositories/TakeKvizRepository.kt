package ba.etf.rma26.projekat.data.repositories

import ba.etf.rma26.projekat.data.models.KvizTaken
import ba.etf.rma26.projekat.data.network.ApiClient

object TakeKvizRepository {

    suspend fun zapocniKviz(idKviza: Int): KvizTaken? {
        return try {
            val hash = AccountRepository.getHash()
            val response = ApiClient.getService().zapocniKviz(hash, idKviza)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getPocetiKvizovi(): List<KvizTaken>? {
        return try {
            val hash = AccountRepository.getHash()
            ApiClient.getService().getPocetiKvizovi(hash)
        } catch (e: Exception) {
            null
        }
    }
}