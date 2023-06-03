package com.example.exhibitionsviewer.ui.home.component.exhibits.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exhibitionsviewer.R
import com.example.exhibitionsviewer.data.model.Epoch
import com.example.exhibitionsviewer.data.model.ExhibitSubjects
import com.example.exhibitionsviewer.data.model.ExhibitType
import com.example.exhibitionsviewer.data.model.FilterData

@Composable
fun FilterScreen(
    onApply: (FilterData) -> Unit
) {
    val subjectOptions = ExhibitSubjects.values().map { it.name }
    val typeOptions = ExhibitType.values().map { it.name}
    val epochOptions = Epoch.values().map { it.name }

    var selectedSubjectOption by remember { mutableStateOf("") }
    var selectedTypeOption by remember { mutableStateOf("") }
    var selectedEpochOption by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Group of filter chips with predefined array of values
            FilterChips(
                title = "Тема",
                options = subjectOptions,
                optionsTranslation = subjectOptions.map { ExhibitSubjects.valueOf(it).ukr },
                selectedOption = selectedSubjectOption,
                onSelect = {it :String -> selectedSubjectOption = it},
                modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)
            )

            FilterChips(
                title = "Тип",
                options = typeOptions,
                optionsTranslation = typeOptions.map { ExhibitType.valueOf(it).ukr },
                selectedOption = selectedTypeOption,
                onSelect = {it :String -> selectedTypeOption = it},
                modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)
            )

            FilterChips(
                title = "Епоха",
                options = epochOptions,
                optionsTranslation = epochOptions.map { Epoch.valueOf(it).ukr },
                selectedOption = selectedEpochOption,
                onSelect = {it :String -> selectedEpochOption = it},
                modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)
            )

            // Apply button
            Button(
                onClick = {onApply(FilterData("", subject = selectedSubjectOption, type = selectedTypeOption, epoch =  selectedEpochOption))},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.blue_500))
            ) {
                Text(text = "Продовжити", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterChips(
    title: String,
    options: List<String>,
    optionsTranslation: List<String>,
    selectedOption: String,
    modifier: Modifier = Modifier,
    onSelect: (String) -> Unit
) {

    Text(
        text = title,
        color = colorResource(id = R.color.dark_blue_700),
        fontSize = 18.sp,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        textAlign = TextAlign.Start,
    )
    FlowRow(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
    ) {
        options.forEachIndexed { index, option ->
            FilterChip(
                text = optionsTranslation[index],
                isSelected = option == selectedOption,
                onSelectedChange = { selected ->
                    if (selected) {
                        onSelect(option)
                    } else onSelect("")
                }
            )
        }
    }
}

@Composable
fun FilterChip(
    text: String,
    isSelected: Boolean,
    onSelectedChange: (Boolean) -> Unit
) {
    val chipBackgroundColor = if (isSelected) {
        colorResource(id = R.color.blue_500) // Selected color
    } else {
        Color(0xFFF5F5F5) // Unselected color
    }
    val chipContentColor = if (isSelected) {
        Color.White // Text color for selected chip
    } else {
        Color(0xFF616161) // Text color for unselected chip
    }

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = chipBackgroundColor,
        modifier = Modifier.padding(end = 8.dp, bottom = 8.dp).fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { onSelectedChange(!isSelected) })
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = text,
                color = chipContentColor,
                modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilterScreenPreview() {
    FilterScreen({ })
}