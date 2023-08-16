package com.febinrukfan.users_lazylists_demo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(modifier = Modifier, text = stringResource(id = R.string.user_id) + " ${user.userId}")
            Text(modifier = Modifier, text = stringResource(id = R.string.username) + " ${user.username}")
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    usersListApplication()
}