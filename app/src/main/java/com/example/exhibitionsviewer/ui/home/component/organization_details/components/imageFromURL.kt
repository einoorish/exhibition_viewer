package com.example.exhibitionsviewer.ui.home.component.organization_details.components
import android.graphics.Bitmap
import android.graphics.BitmapFactory

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.size.Size
import coil.transform.Transformation
import java.io.ByteArrayOutputStream

@Composable
fun imageFromURL(url: String, modifier: Modifier) {
    val imageLoader = LocalImageLoader.current
    return Image(
        painter = rememberImagePainter(
            data = url,
            imageLoader,
            builder = {
                scale(Scale.FIT)
                allowHardware(false)
                transformations(
                    CustomImageTransformation()
                )
            }
        ),
        contentDescription = null,
        modifier = modifier
            .clip(RoundedCornerShape(5.dp)),
        contentScale = ContentScale.Crop
    )
}


@Composable
fun gradientImageFromURL(url: String, text: String, modifier: Modifier) {
    val imageLoader = LocalImageLoader.current
    return Box() {
        Image(
            painter = rememberImagePainter(
                data = url,
                imageLoader,
                builder = {
                    scale(Scale.FIT)
                    allowHardware(false)
                    transformations(
                        CustomImageTransformation()
                    )
                }
            ),
            contentDescription = null,
            modifier = modifier.drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawRect(Color.LightGray, blendMode = BlendMode.Lighten)
                }
            },
            contentScale = ContentScale.Crop
        )
        Text(
            text = text,
            color=Color.Black,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(top=30.dp)
        )
    }
}

class CustomImageTransformation(override val cacheKey: String = "customImageTransformation") : Transformation {

    override suspend fun transform(input: Bitmap, size: Size): Bitmap {
        return input.compress(quality = 20)
    }
}

// Extension function to compress bitmap with a specific quality ratio
fun Bitmap.compress(quality: Int): Bitmap {
    val stream = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.JPEG, quality, stream)
    val byteArray = stream.toByteArray()
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}