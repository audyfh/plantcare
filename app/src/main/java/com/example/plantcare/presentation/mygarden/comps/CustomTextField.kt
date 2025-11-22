package com.example.plantcare.presentation.mygarden.comps

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    textLabel : String,
    text : String,
    placeholder : String,
    isNumber : Boolean = false,
    onValueChange : (String) -> Unit,
) {
    Text(
        text = textLabel,
        style = MaterialTheme.typography.bodyLarge
    )
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .height(55.dp),
        value = text,
        onValueChange = { onValueChange(it) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = if (isNumber) KeyboardType.Number else KeyboardType.Text
        ),
        placeholder = {
            Text(
                placeholder,
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

    )
}