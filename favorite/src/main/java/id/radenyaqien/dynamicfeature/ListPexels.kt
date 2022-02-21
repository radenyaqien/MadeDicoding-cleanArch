package id.radenyaqien.dynamicfeature

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import coil.compose.rememberImagePainter
import id.radenyaqien.core.domain.PexelsImage
import id.radenyaqien.pexels.R
import id.radenyaqien.pexels.ui.home.PexelsItem


@Composable
fun FavoriteScreen(
    list: List<PexelsImage>,
    onclickItem: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(list, key = {
            it.id
        }) { item ->

            PexelsItem(item) {
                onclickItem(it)
            }
        }

    }
}

