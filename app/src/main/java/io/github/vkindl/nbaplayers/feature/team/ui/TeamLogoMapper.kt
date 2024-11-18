package io.github.vkindl.nbaplayers.feature.team.ui

import io.github.vkindl.nbaplayers.R
import io.github.vkindl.nbaplayers.core.domain.model.Team

fun Team.toLogoResId(): Int {
    when (abbreviation) {
        "ATL" -> return R.drawable.ic_atlanta_hawks
        "BOS" -> return R.drawable.ic_boston_celtics
        "BKN" -> return R.drawable.ic_brooklyn_nets
        "CHA" -> return R.drawable.ic_charlotte_hornets
        "CHI" -> return R.drawable.ic_chicago_bulls
        "CLE" -> return R.drawable.ic_cleveland_cavaliers
        "DAL" -> return R.drawable.ic_dallas_mavericks
        "DEN" -> return R.drawable.ic_denver_nuggets
        "DET" -> return R.drawable.ic_detroit_pistons
        "GSW" -> return R.drawable.ic_golden_state_warriors
        "HOU" -> return R.drawable.ic_houston_rockets
        "IND" -> return R.drawable.ic_indiana_pacers
        "LAC" -> return R.drawable.ic_la_clippers
        "LAL" -> return R.drawable.ic_la_lakers
        "MEM" -> return R.drawable.ic_memphis_grizzlies
        "MIA" -> return R.drawable.ic_miami_heat
        "MIL" -> return R.drawable.ic_milwaukee_bucks
        "MIN" -> return R.drawable.ic_minnesota_timberwolves
        "NOP" -> return R.drawable.ic_new_orleans_pelicans
        "NYK" -> return R.drawable.ic_new_york_knicks
        "OKC" -> return R.drawable.ic_oklahoma_city_thunder
        "ORL" -> return R.drawable.ic_orlando_magic
        "PHI" -> return R.drawable.ic_philadelphia_76ers
        "PHX" -> return R.drawable.ic_phoenix_suns
        "POR" -> return R.drawable.ic_portland_trail_blazers
        "SAC" -> return R.drawable.ic_sacramento_kings
        "SAS" -> return R.drawable.ic_san_antonio_spurs
        "TOR" -> return R.drawable.ic_toronto_raptors
        "UTA" -> return R.drawable.ic_utah_jazz
        "WAS" -> return R.drawable.ic_washington_wizards
        else -> throw IllegalArgumentException("Unknown team abbreviation: $abbreviation")
    }
}
