package com.example.roomdatabasee.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.roomdatabasee.model.Mahasiswa
import com.example.roomdatabasee.ui.navigasi.DestinasiNavigasi
import com.example.roomdatabasee.ui.viewmodel.HomeUiState

object DestinasiHome: DestinasiNavigasi{
    override val route = "home"
    override val titleRes = "Home Mhs"
}

@Composable
fun HomeStatus(
    homeUiState: HomeUiState,
    retryAction: ()-> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Mahasiswa)-> Unit = {},
    onDetailClick: (String) -> Unit
){

    when(homeUiState){
        is HomeUiState.Loading-> OnLoading(modifier = modifier.fillMaxSize())

        is HomeUiState.Success->
            if (homeUiState.mahasiswa.isEmpty()){
                return Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(text = "Tidak ada data Kontak")
                }
            }else{
                MhsLayout(
                    mahasiswa = homeUiState.mahasiswa, modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.nim)
                    },
                    onDeleteClick ={
                        onDeleteClick(it)
                    }
                )
            }
        is HomeUiState.Error-> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

/**
 * The home screen displaying the loading message
 */

@Composable
fun OnLoading(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier.size(100.dp),
        painter = painterResource(com.example.roomdatabasee.R.drawable.iconloading),
        contentDescription = stringResource(com.example.roomdatabasee.R.string.loading)
    )
}

/**
 * The home screen displaying error message with re-attempt button
 */

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = com.example.roomdatabasee.R.drawable.erroricon), contentDescription = ""
        )
        Text(
            text = stringResource(com.example.roomdatabasee.R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = retryAction) {
            Text(stringResource(com.example.roomdatabasee.R.string.retry))
        }
    }
}

@Composable
fun MhsLayout(
    mahasiswa: List<Mahasiswa>,
    modifier: Modifier = Modifier,
    onDetailClick: (Mahasiswa) -> Unit,
    onDeleteClick: (Mahasiswa) -> Unit = {},
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(mahasiswa) {mhs ->
            MhsCard(
                mahasiswa = mhs,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(mhs) },
                onDeleteClick = {
                    onDeleteClick(mhs)
                }
            )
        }
    }
}

@Composable
fun MhsCard(
    mahasiswa: Mahasiswa,
    modifier: Modifier = Modifier,
    onDeleteClick: (Mahasiswa) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        Column (
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = mahasiswa.nama,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {onDeleteClick(mahasiswa)}) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }
                Text(
                    text = mahasiswa.nim,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Text(
                text = mahasiswa.kelas,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = mahasiswa.alamat,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}