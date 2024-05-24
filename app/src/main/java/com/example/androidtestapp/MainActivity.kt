import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidtestapp.ui.CharacterListScreen
import com.example.androidtestapp.ui.theme.MyApplicationTheme

/**
 * MainActivity is the entry point of the application.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Apply the theme to the entire app
            MyApplicationTheme {
                // Surface is a container for other composables
                Surface(
                    modifier = Modifier.fillMaxSize(), // Fill the entire available size
                    color = MaterialTheme.colorScheme.background // Set the background color based on MaterialTheme
                ) {
                    // Display the CharacterListScreen composable
                    CharacterListScreen()
                }
            }
        }
    }
}

/**
 * Preview function to display a preview of the UI.
 */
@Preview(showBackground = true)
@Composable
fun Preview() {
    // Apply the theme to the preview
    MyApplicationTheme {
        // Display the CharacterListScreen composable
        CharacterListScreen()
    }
}
