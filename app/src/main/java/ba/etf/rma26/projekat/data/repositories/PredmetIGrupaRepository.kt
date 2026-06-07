package ba.etf.rma26.projekat.data.repositories

import ba.etf.rma26.projekat.data.models.Grupa
import ba.etf.rma26.projekat.data.models.Predmet
import ba.etf.rma26.projekat.data.network.ApiClient

object PredmetIGrupaRepository {

    suspend fun getPredmeti(): List<Predmet> {
        return try {
            ApiClient.getService().getPredmeti()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getGrupe(): List<Grupa> {
        return try {
            ApiClient.getService().getGrupe()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getGrupeZaPredmet(idPredmeta: Int): List<Grupa> {
        return try {
            ApiClient.getService().getGrupeZaPredmet(idPredmeta)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun upisiUGrupu(idGrupa: Int): Boolean {
        return try {
            val hash = AccountRepository.getHash()
            ApiClient.getService().upisiUGrupu(hash, idGrupa)
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getUpisaneGrupe(): List<Grupa> {
        return try {
            val hash = AccountRepository.getHash()
            ApiClient.getService().getUpisaneGrupe(hash)
        } catch (e: Exception) {
            emptyList()
        }
    }
}