package ba.etf.rma26.projekat.data.repositories

object AccountRepository {
    private var hash: String = "demo"

    suspend fun postaviHash(acHash: String): Boolean {
        if (acHash.isBlank()) return false
        hash = acHash
        return true
    }

    suspend fun getHash(): String {
        return hash
    }
}