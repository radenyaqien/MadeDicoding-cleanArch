package id.radenyaqien.dynamicfeature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.radenyaqien.core.domain.PexelsImage
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

