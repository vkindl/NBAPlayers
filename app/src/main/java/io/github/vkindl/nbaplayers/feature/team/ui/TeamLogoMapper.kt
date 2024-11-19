package io.github.vkindl.nbaplayers.feature.team.ui

import io.github.vkindl.nbaplayers.R
import io.github.vkindl.nbaplayers.core.domain.model.Team

object TeamLogoMapper {

    fun Team.toLogoResId(): Int {
        return teamLogoMap[abbreviation]
            ?: throw IllegalArgumentException("Unknown team abbreviation: $abbreviation")
    }

    private val teamLogoMap = mapOf(
        "ATL" to R.drawable.ic_atlanta_hawks,
        "BOS" to R.drawable.ic_boston_celtics,
        "BKN" to R.drawable.ic_brooklyn_nets,
        "CHA" to R.drawable.ic_charlotte_hornets,
        "CHI" to R.drawable.ic_chicago_bulls,
        "CLE" to R.drawable.ic_cleveland_cavaliers,
        "DAL" to R.drawable.ic_dallas_mavericks,
        "DEN" to R.drawable.ic_denver_nuggets,
        "DET" to R.drawable.ic_detroit_pistons,
        "GSW" to R.drawable.ic_golden_state_warriors,
        "HOU" to R.drawable.ic_houston_rockets,
        "IND" to R.drawable.ic_indiana_pacers,
        "LAC" to R.drawable.ic_la_clippers,
        "LAL" to R.drawable.ic_la_lakers,
        "MEM" to R.drawable.ic_memphis_grizzlies,
        "MIA" to R.drawable.ic_miami_heat,
        "MIL" to R.drawable.ic_milwaukee_bucks,
        "MIN" to R.drawable.ic_minnesota_timberwolves,
        "NOP" to R.drawable.ic_new_orleans_pelicans,
        "NYK" to R.drawable.ic_new_york_knicks,
        "OKC" to R.drawable.ic_oklahoma_city_thunder,
        "ORL" to R.drawable.ic_orlando_magic,
        "PHI" to R.drawable.ic_philadelphia_76ers,
        "PHX" to R.drawable.ic_phoenix_suns,
        "POR" to R.drawable.ic_portland_trail_blazers,
        "SAC" to R.drawable.ic_sacramento_kings,
        "SAS" to R.drawable.ic_san_antonio_spurs,
        "TOR" to R.drawable.ic_toronto_raptors,
        "UTA" to R.drawable.ic_utah_jazz,
        "WAS" to R.drawable.ic_washington_wizards
    )
}
