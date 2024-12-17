package com.example.learn.customComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.xita.dailyhelper.R
import com.xita.dailyhelper.constants.Constants
import com.xita.dailyhelper.constants.DrawerItem
import com.xita.dailyhelper.pages.HomePage
import com.xita.dailyhelper.services.FirebaseServices
import com.xita.dailyhelper.ui.theme.Purple80
import kotlinx.coroutines.launch


//Fixed pages
class FixedPages {

    @Composable
    fun BasePage(content: @Composable () -> Unit) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Image(
                painterResource(R.drawable.register_background),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(alpha = 0.3f)
            )

            content()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PageWithAppBarAndDrawer(navController: NavController, drawerItem:DrawerItem,content: @Composable () -> Unit,) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    DrawerContent(navController,drawerItem)
                }
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                "COOKBOOK",
                                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 25.sp)
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu Icon")
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple80)
                    )
                },
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->

                Box(Modifier.padding(innerPadding).fillMaxSize()) {
                    content()
                }
            }
        }
    }

    @Composable
    fun DrawerContent(navController: NavController,drawerItem: DrawerItem) {


        Box(
            Modifier
                .fillMaxSize()
                .background(Purple80)
                .padding(15.dp)
        ) {
            Column(verticalArrangement = Arrangement.Top) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 15.dp)
                ) {
                    Box(
                        Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    )
                    Column(Modifier.padding(start = 10.dp)) {
                        Text("Tejiri Emoghene-Ijatomi", style = Constants.Texts.DRAWER_ITEM_TEXT)
                        Text("Logout", style = Constants.Texts.HOME_RECIPE_SUB_TEXT, modifier =  Modifier.clickable {
                            FirebaseServices().logUserOut()
                            navController.navigate("login"){
                                popUpTo("home") { inclusive = true }
                            }

                        })
                    }
                }
                LazyColumn(Modifier.weight(1f)) {
                    items(1) {
                        DrawerItem(DrawerItem.FREE_RECIPES.name,isSelected = drawerItem == DrawerItem.FREE_RECIPES)
                        DrawerItem(DrawerItem.NOTES.name,isSelected = drawerItem == DrawerItem.NOTES)

                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Settings,
                        contentDescription = "",
                        Modifier
                            .padding(end = 7.dp)
                            .size(40.dp)
                    )
                    Text("Settings", style = Constants.Texts.DRAWER_ITEM_TEXT)
                }
            }
        }
    }

    @Composable
    fun DrawerItem(displayText:String,isSelected:Boolean) {
        Box(
            Modifier
                .padding(bottom = 10.dp)
                .clip(RoundedCornerShape(50))
                .background(if(isSelected) Color.White else Color.Transparent)
                .padding(10.dp)
                .fillMaxWidth()

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    Icons.Default.Refresh,
                    contentDescription = "",
                    Modifier
                        .padding(end = 7.dp)
                        .size(40.dp)
                )
                Text(displayText, style = Constants.Texts.DRAWER_ITEM_TEXT)
            }
        }

    }


}