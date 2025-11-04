package org.example.expensewithserver.navigation

import SearchScreen
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import domain.model.Movie
import kotlinx.serialization.Serializable
import android.util.Log

object SearchNavGraph : BaseNavGraph {
    override fun build(
        modifier: Modifier,
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<Dest.Root>(startDestination = Dest.Search){
            composable<Dest.Search> {
                SearchScreen(modifier) { movieId->
                    Log.d("SearchNavGraph", "movieId = $movieId")

               navController.navigate(DetailsNavGraph.Dest.MovieDetails(movieId))
                }
            }
        }
    }

    sealed interface Dest{
        @Serializable
        data object Root : Dest
        @Serializable
        data object Search : Dest
    }
}