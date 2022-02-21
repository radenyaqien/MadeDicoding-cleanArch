package id.radenyaqien.pexels.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import id.radenyaqien.core.domain.PexelsImage
import id.radenyaqien.pexels.R

@Composable
fun ListContent(
    list: LazyPagingItems<PexelsImage>,
    onclickItem: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = list
        ) { pexels ->
            pexels?.let {
                PexelsItem(pexelsImage = it) { id ->
                    onclickItem(id)
                }
            }
        }
        list.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = list.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = list.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PexelsItem(
    pexelsImage: PexelsImage,
    onclickItem: (String) -> Unit
) {

    val painter = rememberImagePainter(
        data = pexelsImage.src,


        ) {
        crossfade(durationMillis = 1000)
        error(R.drawable.placeholder)
        transformations(CircleCropTransformation())
        placeholder(R.drawable.placeholder)

    }


    Card(
        modifier = Modifier
            .fillMaxWidth()

            .clickable {
                onclickItem(pexelsImage.id)
            },
        elevation = 10.dp
    ) {
        Row(

            modifier = Modifier
                .padding(4.dp)
                .height(200.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painter,
                contentDescription = pexelsImage.alt,
                contentScale = ContentScale.Fit,
                modifier = Modifier.weight(1f),
                alignment = Alignment.Center

            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = pexelsImage.alt ?: "-",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = pexelsImage.photographer ?: "-",
                    fontStyle = FontStyle.Italic
                )
            }

        }
    }
}