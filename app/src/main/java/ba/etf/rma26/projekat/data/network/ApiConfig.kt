package ba.etf.rma26.projekat.data.network

object ApiConfig {
    private var baseUrl: String = "http://192.168.1.14:3000/"
    private var apiKey: String? = null

    fun postaviBaseURL(baseUrl: String) {
        this.baseUrl = if (baseUrl.endsWith("/")) baseUrl else "$baseUrl/"
    }

    fun postaviApiKey(apiKey: String?) {
        this.apiKey = apiKey
    }

    fun getBaseURL(): String = baseUrl
    fun getApiKey(): String? = apiKey
}