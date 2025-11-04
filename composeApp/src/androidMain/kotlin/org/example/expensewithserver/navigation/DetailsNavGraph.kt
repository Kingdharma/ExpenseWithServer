package org.example.expensewithserver.navigation

import android.util.Log
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import ui.DetailsScreen



object DetailsNavGraph : BaseNavGraph {

    sealed interface Dest {
        @Serializable
        data object Root : Dest

        @Serializable
        data class MovieDetails(val movieId: String) : Dest
    }

    override fun build(
        modifier: Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<Dest.Root>(startDestination = Dest.MovieDetails::class) {
            composable<Dest.MovieDetails> {
                val movieId = it.toRoute<Dest.MovieDetails>().movieId
                Log.d("here We Go",movieId)
                DetailsScreen(modifier=modifier, id = movieId)
            }
        }

    }
}