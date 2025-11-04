package org.example.expensewithserver

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.example.expensewithserver.navigation.BaseNavGraph
import org.example.expensewithserver.navigation.DetailsNavGraph
import org.example.expensewithserver.navigation.SearchNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App1() {
    MaterialTheme {
        Scaffold (
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)

                .fillMaxSize(),
        ) { innerPadding->
            val navController = rememberNavController()
          NavHost(navController = navController, startDestination = SearchNavGraph.Dest.Root,){
              listOf<BaseNavGraph>(
                  SearchNavGraph,
                  DetailsNavGraph
              ).forEach {
                  it.build(modifier = Modifier.fillMaxSize().padding(innerPadding),
                      navController = navController,
                      navGraphBuilder = this)

              }
          }
        }
    }
}