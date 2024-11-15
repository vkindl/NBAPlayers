package io.github.vkindl.nbaplayers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.github.vkindl.nbaplayers.navigation.AppNavGraph
import io.github.vkindl.nbaplayers.ui.theme.NBATheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NBATheme {
                AppNavGraph()
            }
        }
    }
}
