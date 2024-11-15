package io.github.vkindl.nbaplayers.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.vkindl.nbaplayers.navigation.Destination.PlayerDetail
import io.github.vkindl.nbaplayers.navigation.Destination.Players
import io.github.vkindl.nbaplayers.navigation.Destination.Team
import io.github.vkindl.nbaplayers.ui.playerdetail.PlayerDetailScreen
import io.github.vkindl.nbaplayers.ui.players.PlayersScreen
import io.github.vkindl.nbaplayers.ui.team.TeamScreen

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Players) {
        composable<Players> {
            PlayersScreen(
                onPlayerClick = {
                    navController.navigate(PlayerDetail)
                }
            )
        }
        composable<PlayerDetail> {
            PlayerDetailScreen(
                onBackClick = {
                    navController.navigateUp()
                },
                onTeamClick = {
                    navController.navigate(Team)
                }
            )
        }
        composable<Team> {
            TeamScreen(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}
