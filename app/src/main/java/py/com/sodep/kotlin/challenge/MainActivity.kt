package py.com.sodep.kotlin.challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.desafiodesodep.presentacion.theme.KotlinchallengeTheme
import dagger.hilt.android.AndroidEntryPoint
import py.com.sodep.kotlin.challenge.presentacion.navigation.AppNavigation

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinchallengeTheme {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}
