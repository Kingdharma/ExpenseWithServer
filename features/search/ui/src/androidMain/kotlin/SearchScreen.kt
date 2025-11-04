import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.SubcomposeAsyncImage
import org.koin.compose.viewmodel.koinViewModel
import ui.SearchUiState
import ui.SearchViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.layout.ContentScale

@Composable
fun SearchScreen(modifier: Modifier= Modifier,onClick:(String) -> Unit){
    val viewModel = koinViewModel<SearchViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()
  SearchScreenContent(
      modifier = modifier.fillMaxSize(),
      uiState = uiState,
      query = query,
      onQueryChanged =viewModel::updateQuery,
      onClick=onClick
  )
}
@Composable
fun SearchScreenContent(modifier: Modifier= Modifier,uiState: SearchUiState,query: String,onQueryChanged: (String)-> Unit,
                        onClick:(String) -> Unit){
    Scaffold( modifier=modifier.fillMaxSize(),
        topBar = {
            TextField(value = query,
                onValueChange = onQueryChanged,

                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                Text(text = "Search Movies")
            },
                maxLines = 1
                )
        }) {
       innerPadding ->
        when{
            uiState.isLoading ->{
                Box(modifier= Modifier.padding(innerPadding).fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            uiState.error.isNotEmpty() ->{
                Box(modifier= Modifier.padding(innerPadding).fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                  Text(uiState.error)
                }
            }
            uiState.data != null ->{
                uiState.data?.let { data->
                    LazyColumn(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                        items(count = data.count(),
                            key = {data.get(it).id},
                            contentType ={data.get(it).id} ) {  index ->
                            val item = data.get(index)
                            if (item.imageUrl.isEmpty()){
                                Box(modifier= Modifier.  fillMaxWidth().
                                padding(horizontal = 12.dp).
                                padding(top = 12.dp),


                                    contentAlignment = Alignment.Center)
                                {
                                   Text("No Image Available")
                                }
                            }
                            else{
                                SubcomposeAsyncImage(model = item.imageUrl,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier=Modifier.
                                        padding(horizontal = 12.dp).
                                        clickable{onClick(data[index].id.toString())}.
                                    padding(vertical = 12.dp).
                                    fillMaxWidth().height(400.dp).clip(
                                        RoundedCornerShape(12.dp)
                                    ),

                                    loading = {
                                        Box(modifier= Modifier.
                                        fillMaxWidth().
                                        height(400.dp),
                                            contentAlignment = Alignment.Center)
                                        {
                                            CircularProgressIndicator()
                                        }
                                }, error = {
                                    Box(modifier= Modifier.fillMaxWidth().height(400.dp),
                                        contentAlignment = Alignment.Center){
                                        Text(text ="oops Something went Wrong")
                                    }
                                })
                            }

                        }
                    }
                }
            }
        }
    }
}