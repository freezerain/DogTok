package com.freezerain.dogtok


import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.ui.AppBarConfiguration
import com.freezerain.dogtok.composables.MainCanvas
import com.freezerain.dogtok.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var appBarConfiguration: AppBarConfiguration? = null
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Column (modifier = Modifier.fillMaxSize()){
                    Box(Modifier.weight(1.0f, true).fillMaxSize()){
                        MainCanvas(Modifier.align(Alignment.Center).fillMaxSize())
                        CanvasControl(Modifier.align(Alignment.BottomEnd))
                    }
                    BottomAppBar()
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Column (modifier = Modifier.fillMaxSize()){
            Box(Modifier.weight(1.0f, true).fillMaxSize()){
                MainCanvas(Modifier.align(Alignment.Center).fillMaxSize())
                CanvasControl(Modifier.align(Alignment.BottomEnd))
            }
            BottomAppBar()
        }
    }
}

@Composable
fun BottomAppBar(modifier: Modifier = Modifier) {
    val localModifier = Modifier
    val ctx = LocalContext.current
    BottomAppBar(localModifier.then(modifier)){
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()){
            IconButton(onClick = { onBtnPressed(ctx, "Home") }) {
                Icon(Icons.Filled.Home, contentDescription = "Home button")
            }
            IconButton(onClick = { onBtnPressed(ctx, "Search") }) {
                Icon(Icons.Filled.Search, contentDescription = "Search button")
            }
            FloatingActionButton(
                onClick = { onBtnPressed(ctx, "Add") },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.AddBox, "Add button")
            }
            IconButton(onClick = { onBtnPressed(ctx, "Chat") }) {
                Icon(Icons.Filled.Chat, contentDescription = "Localized description")
            }
            IconButton(onClick = { onBtnPressed(ctx, "Person") }) {
                Icon(Icons.Filled.Person, contentDescription = "Localized description")
            }
        }

    }

}

@Composable
fun CanvasControl(modifier: Modifier = Modifier) {
    val controlModifier = Modifier
    Column(modifier = controlModifier.then(modifier)) {
        val ctx = LocalContext.current
        IconButton(onClick = { onBtnPressed(ctx, "Favourite") }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
        }
        IconButton(onClick = { onBtnPressed(ctx, "Comments") }) {
            Icon(Icons.Filled.Comment, contentDescription = "Localized description")
        }
        IconButton(onClick = { onBtnPressed(ctx, "Send to") }) {
            Icon(Icons.Filled.Send, contentDescription = "Localized description")
        }
    }
}

fun onBtnPressed(ctx: Context, btnName: String) {
    Toast.makeText(ctx, "$btnName pressed", Toast.LENGTH_SHORT).show()
}
