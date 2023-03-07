package com.ianschoenrock.tylerslauncher

import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ianschoenrock.tylerslauncher.ui.theme.TylersLauncherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TylersLauncherTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val context = LocalContext.current
    val pManager = context.packageManager
    val mainIntent = Intent(Intent.ACTION_MAIN, null)
    mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
    val myApps = pManager.queryIntentActivities(mainIntent, 0)
    val approvedApps = remember {mutableListOf<ResolveInfo>()}
    val appLabels = remember { mutableListOf<String>() }
    LaunchedEffect(key1 = true){

    }

    Column(modifier = Modifier.background(Color.Transparent),

        verticalArrangement = Arrangement.spacedBy(10.dp)){
        myApps.forEach {

            when (it.loadLabel(pManager).toString()){
                "Camera" -> approvedApps.add(it)
                "Maps" -> approvedApps.add(it)
                "Messages" -> approvedApps.add(it)
                "Phone" -> approvedApps.add(it)
                "Photos" -> approvedApps.add(it)
                "Spotify" -> approvedApps.add(it)


            }
        }

        Spacer(modifier = Modifier.weight(1F))
        approvedApps.forEach {
            Text(
                it.loadLabel(pManager).toString(),
                style = TextStyle(
                    fontSize = 48.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        val clickedIntent =
                            pManager.getLaunchIntentForPackage(it.activityInfo.packageName.toString())
                        context.startActivity(clickedIntent)
                    }
            )
            Spacer(modifier = Modifier.weight(1F))
        }
        Spacer(modifier = Modifier.weight(1F))



    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TylersLauncherTheme {
        Greeting("Android")
    }
}