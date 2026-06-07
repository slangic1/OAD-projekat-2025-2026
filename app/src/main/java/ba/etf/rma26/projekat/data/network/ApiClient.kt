package ba.etf.rma26.projekat.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var retrofit: Retrofit? = null
    private var lastBaseUrl: String? = null

    fun getService(): ApiService {
        val currentBaseUrl = ApiConfig.getBaseURL()

        if (retrofit == null || lastBaseUrl != currentBaseUrl) {
            val client = OkHttpClient.Builder().build()

            retrofit = Retrofit.Builder()
                .baseUrl(currentBaseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            lastBaseUrl = currentBaseUrl
        }

        return retrofit!!.create(ApiService::class.java)
    }
}