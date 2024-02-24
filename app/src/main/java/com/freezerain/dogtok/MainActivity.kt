package com.freezerain.dogtok


import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.ui.AppBarConfiguration
import com.freezerain.dogtok.composables.MainFeed
import com.freezerain.dogtok.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var appBarConfiguration: AppBarConfiguration? = null
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // LOG policy violation like resource fail to close
        // Remove from prod build
        val policy = VmPolicy.Builder().detectAll().penaltyLog().build()
        StrictMode.setVmPolicy(policy)

        setContent { // TODO Finish app styling
            MaterialTheme {
                Column(Modifier.fillMaxSize()) {
                    //TODO Buttons are pusshed out by main canvas
                    BottomAppBar()
                    Box() {
                        //Old implementations
                        //MainCanvas(Modifier.align(Alignment.Center).fillMaxSize())
                        //MainPager(Modifier.fillMaxSize())
                        MainFeed(Modifier.fillMaxSize())
                        CanvasControl(Modifier.align(Alignment.BottomEnd))
                    }
                }
            }
        }
    }
}

@Composable
fun BottomAppBar(modifier: Modifier = Modifier) {
    val ctx = LocalContext.current
    BottomAppBar(
        modifier,
        containerColor = Color(0.0f, 0.0f, 0.0f, 1.0f),
        contentColor = Color.White
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val btnMod = Modifier.padding(16.dp)
            val iconMod = Modifier.size(32.dp)
            IconButton(modifier = btnMod, onClick = { onBtnPressed(ctx, "Home") }) {
                Icon(Icons.Filled.Home, contentDescription = "Home button", modifier = iconMod)
            }
            IconButton(modifier = btnMod, onClick = { onBtnPressed(ctx, "Search") }) {
                Icon(Icons.Filled.Search, contentDescription = "Search button", modifier = iconMod)
            }
            FloatingActionButton(
                onClick = { onBtnPressed(ctx, "Add") },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, "Add button")
            }
            IconButton(modifier = btnMod, onClick = { onBtnPressed(ctx, "Chat") }) {
                Icon(
                    Icons.Filled.Email,
                    contentDescription = "Localized description",
                    modifier = iconMod
                )
            }
            IconButton(modifier = btnMod, onClick = { onBtnPressed(ctx, "Person") }) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Localized description",
                    modifier = iconMod
                )
            }
        }

    }

}

@Composable
fun CanvasControl(modifier: Modifier = Modifier) {
    Column(modifier) {
        val ctx = LocalContext.current
        IconButton(onClick = { onBtnPressed(ctx, "Favourite") }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
        }
        IconButton(onClick = { onBtnPressed(ctx, "Comments") }) {
            Icon(Icons.Filled.Menu, contentDescription = "Localized description")
        }
        IconButton(onClick = { onBtnPressed(ctx, "Send to") }) {
            Icon(Icons.Filled.Send, contentDescription = "Localized description")
        }
    }
}

fun onBtnPressed(ctx: Context, btnName: String) {
    Toast.makeText(ctx, "Button: \"$btnName\" pressed!", Toast.LENGTH_SHORT).show()
}
