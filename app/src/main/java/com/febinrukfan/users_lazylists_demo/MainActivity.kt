package com.febinrukfan.users_lazylists_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.febinrukfan.users_lazylists_demo.ui.theme.UserslazylistsdemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                usersList()
        }
    }
}

@Composable
fun usersList() {
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    usersList()
}