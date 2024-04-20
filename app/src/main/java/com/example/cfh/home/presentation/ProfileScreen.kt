package com.example.cfh.home.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.cfh.home.utils.AppBar
import com.example.cfh.util.PreferencesManager

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    drawerState: DrawerState,
) {
    val scrollState = rememberScrollState()

    val context = LocalContext.current

    val preferencesManager = remember { PreferencesManager(context) }

    Scaffold(
        topBar = { AppBar(drawerState = drawerState) }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            BoxWithConstraints(modifier = Modifier.weight(1f)) {
                Surface {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState),
                    ) {
                        ProfileContent(
                            preferencesManager.getData("firstname", "firstname"),
                            preferencesManager.getData("lastname", "lastname"),
                            preferencesManager.getData("age", "age"),
                            preferencesManager.getData("email", "email"),
                            this@BoxWithConstraints.maxHeight
                        )
                    }
                }
            }
        }
    }


}

@Composable
private fun ProfileContent(
    firstname: String,
    lastname: String,
    age: String,
    email: String,
    containerHeight: Dp
) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))

        Name(firstname)
        Name(lastname)
        ProfileProperty(age)

        ProfileProperty(email)


        // Add a spacer that always shows part (320.dp) of the fields list regardless of the device,
        // in order to always leave some content at the top.
        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}


@Composable
private fun Name(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ProfileProperty(label: String) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        HorizontalDivider()

        val style = MaterialTheme.typography.bodyMedium

        Text(
            text = label,
            modifier = Modifier.height(24.dp),
            style = style
        )
    }
}

