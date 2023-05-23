package com.example.exhibitionsviewer.ui.home.component.collection.components

import androidx.compose.animation.core.KeyframesSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.exhibitionsviewer.R
import com.example.exhibitionsviewer.data.model.Publication
import com.example.exhibitionsviewer.ui.home.component.organization_details.components.imageFromURL

@Composable
fun TimelineItem(item: Publication, onItemClick: () -> Unit){
    val height = if(item.current) 300.dp else 120.dp
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height(height + (20 * (item.title.length/60 - 1)).dp)
            .clickable { onItemClick() }
    ) {
        ConstraintLayout(
            constraintSet = decoupledConstraints(
                lineStartMargin = 30.dp,
                lineEndMargin = 30.dp
            )
        ) {
            Canvas(
                modifier = Modifier
                    .height(height + (20 * (item.title.length/60 - 1)).dp)
                    .layoutId("line")
                    .background(color = Color.Cyan)
            ) {
                drawLine(
                    cap = StrokeCap.Round,
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = 0f, y = size.height),
                    color = Color.DarkGray,
                    strokeWidth = 6f
                )
            }

            val iconImage = if(item.wasViewed) {
                ImageVector.vectorResource(id = R.drawable.icon_check)
            } else {
                if(item.current) {
                    ImageVector.vectorResource(id = R.drawable.icon_filled)
                } else {
                    ImageVector.vectorResource(id = R.drawable.icon_empty)
                }
            }

            var finalAlpha = 1f
            if(item.current){
                IconAnimation().let { safeIconAnimation ->
                    val infiniteTransition = rememberInfiniteTransition()
                    val alpha by infiniteTransition.animateFloat(
                        initialValue = safeIconAnimation.initialValue,
                        targetValue = safeIconAnimation.targetValue,
                        animationSpec = infiniteRepeatable(
                            animation = safeIconAnimation.keySpecs,
                            repeatMode = RepeatMode.Reverse
                        )
                    )
                    finalAlpha = alpha
                }
            }

            Image(
                imageVector = iconImage,
                contentDescription = "Indicator",
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(
                    color = Color(0xFF2D4869)
                ),
                modifier = Modifier
                    .size(26.dp)
                    .scale(finalAlpha)
                    .clip(CircleShape)
                    .border(width = 2.dp, color = Color.White, shape = CircleShape)
                    .background(
                        color = Color.White,
                        shape = CircleShape
                    )
                    .layoutId("indicator")
            )

            Text(
                text = item.title,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                maxLines = 2, overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .layoutId("title")
                    .padding(end = 100.dp)
            )


            Box(modifier = Modifier.layoutId("content")
                .padding(end = 100.dp, top = 8.dp)
                .padding(bottom = if(item.current) 8.dp else 0.dp)
                .height(if(item.current) 160.dp else 0.dp)
            ) {
                if(item.current){
                    imageFromURL(url = item.thumbnailUrl, modifier = Modifier
                        .fillMaxWidth().fillMaxHeight())
                }
            }

            Text(text = item.description, maxLines = 3, overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .layoutId("description")
                    .padding(end = 100.dp))
        }
    }
}

private fun decoupledConstraints(
    lineStartMargin: Dp,
    lineEndMargin: Dp
): ConstraintSet {
    return ConstraintSet {
        val lineC = createRefFor("line")
        val indicatorC = createRefFor("indicator")
        val titleC = createRefFor("title")
        val descriptionC = createRefFor("description")
        val contentC = createRefFor("content")

        constrain(lineC) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start, margin = lineStartMargin)
        }
        constrain(indicatorC) {
            start.linkTo(lineC.start)
            top.linkTo(titleC.top, margin = 0.dp)
            end.linkTo(lineC.end)
        }
        constrain(titleC) {
            top.linkTo(
                anchor = parent.top,
                margin = 16.dp
            )
            start.linkTo(
                anchor = lineC.end,
                margin = lineEndMargin
            )
        }
        constrain(contentC) {
            top.linkTo(titleC.bottom)
            start.linkTo(
                anchor = lineC.end,
                margin = lineEndMargin
            )
        }
        constrain(descriptionC) {
            top.linkTo(contentC.bottom)
            start.linkTo(
                anchor = lineC.end,
                margin = lineEndMargin
            )
        }
    }
}


data class IconAnimation(
    val initialValue: Float = 0.5f,
    val targetValue: Float = 1.5f,
    val keySpecs: KeyframesSpec<Float> = keyframes {
        durationMillis = 500
        0.6f at 0
        0.7f at 100
        0.8f at 200
        0.9f at 300
        1f at 500
    }
)