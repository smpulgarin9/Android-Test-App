package com.example.androidtestapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.androidtestapp.model.Character

/**
 * Composable function to display a character card with details.
 *
 * @param character The [Character] object containing the character details to display.
 */
@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterCard(character: Character) {
    // Create a card with rounded corners and padding
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                // Display character image with rounded corners
                Image(
                    painter = rememberImagePainter(character.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                // Display character details in a column
                Column {
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    BodyText("Status: ${character.status}")
                    BodyText("Species: ${character.species}")
                    BodyText("Gender: ${character.gender}")
                    BodyText("Origin: ${character.origin.name}")
                    BodyText("Location: ${character.location.name}")
                }
            }
        }
    }
}

/**
 * Composable function to display body text with a consistent style.
 *
 * @param text The text to display.
 */
@Composable
fun BodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium
    )
}