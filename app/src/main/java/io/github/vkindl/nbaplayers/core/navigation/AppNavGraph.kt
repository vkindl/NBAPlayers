package io.github.vkindl.nbaplayers.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.vkindl.nbaplayers.core.navigation.Destination.PlayerDetail
import io.github.vkindl.nbaplayers.core.navigation.Destination.Players
import io.github.vkindl.nbaplayers.core.navigation.Destination.Team
import io.github.vkindl.nbaplayers.feature.playerdetail.ui.PlayerDetailScreen
import io.github.vkindl.nbaplayers.feature.players.ui.PlayersScreen
import io.github.vkindl.nbaplayers.feature.team.ui.TeamScreen

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Players) {
        composable<Players> {
            PlayersScreen(
                onPlayerClick = {
                    navController.navigate(PlayerDetail(id = it))
                }
            )
        }
        composable<PlayerDetail> {
            PlayerDetailScreen(
                onBackClick = {
                    navController.navigateUp()
                },
                onTeamClick = {
                    navController.navigate(Team(id = it))
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
