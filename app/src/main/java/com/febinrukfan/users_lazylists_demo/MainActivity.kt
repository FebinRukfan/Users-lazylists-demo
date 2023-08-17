package com.febinrukfan.users_lazylists_demo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febinrukfan.users_lazylists_demo.data.user
import com.febinrukfan.users_lazylists_demo.data.users
import com.febinrukfan.users_lazylists_demo.ui.theme.UserslazylistsdemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserslazylistsdemoTheme{
                usersListApplication()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun usersListApplication() {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Surface(shadowElevation = 3.dp) {
                SmallTopAppBar(
                    title = { Text(
                        text = stringResource(id = R.string.users)
                    ) },
                    colors = TopAppBarDefaults.smallTopAppBarColors())
            }
        }
        ) { paddingValues ->

        val users = remember {
            mutableStateListOf(*user.toTypedArray())
        }
        val context = LocalContext.current

        LazyColumn(
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(8.dp)

        ){
            itemsIndexed(users){ index, user->

                UserItem(user = user, index = index){
                    users.removeAt(index)
                    Toast.makeText(context, "User Deleted: ${user.userId}", Toast.LENGTH_SHORT).show()
                }



            }

        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserItem(modifier: Modifier = Modifier, user: users, index: Int, onItemClick: () -> Unit) {
    ElevatedCard(
        modifier = modifier
            .clickable { onItemClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(bottom = 8.dp), horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Column(
                horizontalAlignment = Alignment.Start) {
                Text(style = TextStyle(
                    color = Color.White
                ), text = stringResource(id = R.string.user_id) + " ${user.userId}")
                Text(style = TextStyle(
                    color = Color.White
                ),text = stringResource(id = R.string.username) + " ${user.username}")
                Text(style = TextStyle(
                    color = Color.White
                ),text = stringResource(id = R.string.full_name) + " ${user.fullName}")
                Text(style = TextStyle(
                    color = Color.White
                ),text = stringResource(id = R.string.email) + " ${user.email}")
            }

            Box(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .background(color = Color.Black, shape = CircleShape)
                    .padding(3.dp)
            ) {
                Badge(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ) {
                    Text(
                        modifier = Modifier
                            .padding(4.dp),
                        text = "${index + 1}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }

        }



    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    usersListApplication()
}