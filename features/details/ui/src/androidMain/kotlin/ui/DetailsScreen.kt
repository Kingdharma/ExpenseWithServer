package ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.SubcomposeAsyncImage
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.getValue

@Composable
fun DetailsScreen(modifier: Modifier= Modifier,id: String){
  val viewModel = koinViewModel<DetailsUiViewModel>()
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.getMovies(id)
    }

    DetailsScreenContent(
        modifier =modifier,
        uiState = uiState
    )
}

@Composable
fun DetailsScreenContent(modifier: Modifier,uiState: DetailsUIState){
    Scaffold {
        innerPadding ->
        when{
            uiState.isLoading ->{
                Box(modifier = modifier.padding(innerPadding).fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            uiState.error.isNotEmpty() ->{
                Box(modifier = modifier.padding(innerPadding).fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(uiState.error)
                }
            }
            uiState.data!=null ->{
                val movieDetails = uiState.data
                Column(modifier= Modifier.padding(innerPadding).fillMaxSize().verticalScroll(
                    rememberScrollState()
                )) {
                    SubcomposeAsyncImage(modifier = Modifier.fillMaxWidth().height(600.dp),
                        model = movieDetails.imageUrl,
                        loading = {
                            Box(modifier= Modifier.
                            fillMaxWidth().
                            height(600.dp),
                                contentAlignment = Alignment.Center)
                            {
                                CircularProgressIndicator()
                            }
                        },
                        error={
                            Box(modifier= Modifier.
                            fillMaxWidth().
                            height(600.dp),
                                contentAlignment = Alignment.Center)
                            {
                                Text("No image")
                            }
                        },
                        contentDescription = null,
                        contentScale = ContentScale.Crop ,

                    )
                    Spacer(Modifier.height(16.dp))
                    Text(text = movieDetails.title, style = MaterialTheme.typography.headlineLarge, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center )
                    Spacer(Modifier.height(16.dp))
                    Text(text = movieDetails.overView,modifier= Modifier.padding(horizontal = 12.dp).fillMaxWidth(), textAlign = TextAlign.Start)
                    Spacer(Modifier.height(64.dp))
                }
                }
            }
        }
    }
