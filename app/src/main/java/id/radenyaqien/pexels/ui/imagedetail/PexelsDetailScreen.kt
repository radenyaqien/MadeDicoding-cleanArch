package id.radenyaqien.pexels.ui.imagedetail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.rememberImagePainter
import id.radenyaqien.core.domain.PexelsImage
import id.radenyaqien.pexels.R

@Composable
fun PexelsDetail(
    pexels: PexelsImage?,
    onFabClick: (Boolean) -> Unit
) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onFabClick(pexels?.isfavorite == true) }) {
            Icon(
                painter = painterResource(id = if (pexels?.isfavorite == true) R.drawable.ic_heart else R.drawable.ic_baseline_favorite_border_24),
                contentDescription = null // decorative element
            )
        }
    }, floatingActionButtonPosition = FabPosition.End) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp)
        ) {
            // Advanced
            Card(
                elevation = 6.dp
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = pexels?.src
                    ),
                    contentDescription = pexels?.alt,

                    )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = buildAnnotatedString {
                    append("Photo by ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                        append(pexels?.photographer ?: "-")
                    }
                    append(" on Pexels.com")
                },
                fontSize = MaterialTheme.typography.caption.fontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = pexels?.alt ?: "-",
                fontSize = MaterialTheme.typography.h5.fontSize,
            )

            Text(text = "Visit for More : ")

            Spacer(modifier = Modifier.height(8.dp))
            val context = LocalContext.current
            val str = "Photografer"
            ClickableText(text = buildAnnotatedString {
                append(str)
                addStyle(
                    style = SpanStyle(
                        color = Color(0xff64B5F6),
                        textDecoration = TextDecoration.Underline
                    ), start = 0, end = str.length
                )
            }, onClick = {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(pexels?.photographerUrl)
                )
                startActivity(context, browserIntent, null)
            })
            Spacer(modifier = Modifier.height(8.dp))
            val url = "Image Url"
            ClickableText(text = buildAnnotatedString {
                append(url)
                addStyle(
                    style = SpanStyle(
                        color = Color(0xff64B5F6),
                        textDecoration = TextDecoration.Underline
                    ), start = 0, end = url.length
                )
            }, onClick = {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(pexels?.url)
                )
                startActivity(context, browserIntent, null)
            })
        }

    }

}