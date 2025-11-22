package com.example.plantcare.presentation.mygarden.comps

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.plantcare.ui.theme.SoftMint
import kotlin.math.exp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryField(
    modifier: Modifier = Modifier,
    text : String,
    onClick: (String) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    val categories = listOf(
        "Indoor",
        "Outdoor",
        "Succulent"
    )
    Text(
        text = "Category",
        style = MaterialTheme.typography.bodyLarge
    )
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(55.dp)
                .clickable{
                    expanded = true
                },
            value = text,
            enabled = false,
            onValueChange = {  },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            placeholder = {
                Text(
                    "Indoor",
                    style = MaterialTheme.typography.bodySmall
                )
            },
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.7f),
                focusedBorderColor = Color.Gray
            ),
            singleLine = true,
            textStyle = MaterialTheme.typography.bodySmall,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false},
            containerColor = SoftMint
        ) {
            categories.forEach {
                DropdownMenuItem(
                    text = {
                        Text(it)
                    },
                    onClick = {
                        onClick(it)
                        expanded = false
                    }
                )
            }
        }

    }

}