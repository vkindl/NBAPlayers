package io.github.vkindl.nbaplayers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.github.vkindl.nbaplayers.core.navigation.AppNavGraph
import io.github.vkindl.nbaplayers.core.designsystem.theme.NbaTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NbaTheme {
                KoinContext {
                    AppNavGraph()
                }
            }
        }
    }
}
