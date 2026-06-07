package ba.etf.rma26.projekat

import ba.etf.rma26.projekat.data.network.ApiConfig
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ba.etf.rma26.projekat.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        ApiConfig.postaviBaseURL("http://192.168.1.14:3000/")
        ApiConfig.postaviApiKey(null)
        setContent {
            AppNavigation()
        }
    }
}