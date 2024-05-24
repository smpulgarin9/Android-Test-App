package com.example.androidtestapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.androidtestapp.model.Character

/**
 * Composable function to display a character item in a list.
 * When the item is clicked, it toggles the display of additional character information.
 *
 * @param character The [Character] object containing the character details to display.
 */
@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterItem(character: Character) {
    // State to keep track of whether additional information should be shown
    var showInfo by remember { mutableStateOf(false) }

    // Card to display the character item with click functionality to toggle information display
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable { showInfo = !showInfo },
    ) {
        // Row to display character image and name
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Display character image
                Image(
                    painter = rememberImagePainter(character.image),
                    contentDescription = null, // Decorative image
                    modifier = Modifier.size(50.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Display character name
                Text(text = character.name, style = MaterialTheme.typography.titleLarge)
            }
            // Display an icon that indicates whether the additional information is shown
            val icon = if (showInfo) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
            Icon(icon, contentDescription = "Toggle Info")
        }
    }
    // Conditionally display additional character information
    if (showInfo) {
        CharacterCard(character = character)
    }
}