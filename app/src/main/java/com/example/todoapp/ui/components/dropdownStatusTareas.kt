package com.example.todoapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun dropdownStatusTareas(statusList: List<String>, onStatusChanged: (String) -> Unit) {
    var brandDropDownControl by remember { mutableStateOf(false) }
    var initialValueIndex by remember { mutableStateOf(0) }
    statusList.forEachIndexed { index, value ->
        if (value == "Pendiente") {
            initialValueIndex = index
        } else if(value == "Finalizado") {
            initialValueIndex = index
        }
    }
    var selectedBrandIndex by remember { mutableStateOf(initialValueIndex) }

    OutlinedCard(
        modifier = Modifier
            .padding(16.dp),
        colors = CardDefaults.cardColors(Color(0xFF95bbb6))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(125.dp)
                .height(50.dp)
                .padding(5.dp)
                .clickable {
                    brandDropDownControl = true
                }
        ) {
            Text(text = statusList[selectedBrandIndex])
        }

        DropdownMenu(
            expanded = brandDropDownControl,
            onDismissRequest = { brandDropDownControl = false }
        ) {
            statusList.forEachIndexed { index, brand ->
                DropdownMenuItem(
                    text = { Text(text = brand) },
                    onClick = {
                        brandDropDownControl = false
                        selectedBrandIndex = index
                        onStatusChanged(brand)
                    }
                )
            }
        }
    }
}