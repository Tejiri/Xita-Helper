package com.xita.dailyhelper

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.xita.dailyhelper.constants.Constants
import com.xita.dailyhelper.pages.HomePage
import com.xita.dailyhelper.pages.LoginPage
import com.xita.dailyhelper.pages.SignUpPage
import com.xita.dailyhelper.services.FirebaseServices
import com.xita.dailyhelper.ui.theme.Purple80
import com.xita.dailyhelper.ui.theme.XitaDailyHelperTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XitaDailyHelperTheme {
                val navController = rememberNavController()

                // Root navigation logic
                NavigationGraphWithConditionalUI(navController)

            }
        }
    }
}



@Composable
fun NavigationGraphWithConditionalUI(navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
Log.i("MYINFO - Current route", currentRoute.toString())
    if (currentRoute == "home") {
        AppWithAppBarAndDrawer(navController)
    } else {
        Log.i("MYINFO - Current route","Entered here")
        AppWithoutAppBarAndDrawer(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppWithAppBarAndDrawer(navController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(navController)
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("COOKBOOK", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 25.sp)) },
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
            HomePage(navController, innerPadding)
        }
    }
}

@Composable
fun AppWithoutAppBarAndDrawer(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavigationGraph(navController, innerPadding)
    }
}


@Composable
fun NavigationGraph(navController: NavHostController, innerPaddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("home") { HomePage(navController, innerPaddingValues) }
        composable("login") { LoginPage(navController, innerPaddingValues) }
        composable("signup") { SignUpPage(navController, innerPaddingValues) }
    }
}


@Composable
fun DrawerContent(navController: NavHostController) {
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
                    DrawerItem(isSelected = false)
                    DrawerItem(isSelected = false)
                    DrawerItem(isSelected = false)
                    DrawerItem(isSelected = true)
                    DrawerItem(isSelected = false)
                    DrawerItem(isSelected = false)
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
fun DrawerItem(isSelected:Boolean) {
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
            Text("My Recipes", style = Constants.Texts.DRAWER_ITEM_TEXT)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    XitaDailyHelperTheme  {
        HomePage(rememberNavController(), PaddingValues(0.dp))
    }
}