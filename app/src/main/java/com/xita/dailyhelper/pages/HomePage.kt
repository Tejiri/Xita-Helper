package com.xita.dailyhelper.pages


import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.learn.customComponents.FixedPages
import com.xita.dailyhelper.R
import com.xita.dailyhelper.constants.Constants
import com.xita.dailyhelper.constants.DrawerItem
import com.xita.dailyhelper.models.Category
import com.xita.dailyhelper.ui.theme.Purple80

import com.xita.dailyhelper.ui.theme.SearchBoxGrey
import com.xita.dailyhelper.viewModels.HomePageViewModel


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomePage(
    navController: NavController,
    innerPadding: PaddingValues,
    homePageViewModel: HomePageViewModel = viewModel()
) {


    FixedPages().PageWithAppBarAndDrawer(navController,DrawerItem.NON_SELECTED) {
        var searchText by remember { mutableStateOf("") }

        Box( ) {
            Column(
                Modifier

                    .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            ) {

                Box(
                    Modifier
                        .padding(bottom = 10.dp)
                        .clip(RoundedCornerShape(30))
                        .background(SearchBoxGrey)
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(Icons.Default.Search, "", Modifier.padding(end = 10.dp))


                        BasicTextField(

                            searchText, { value -> searchText = value }, Modifier.weight(1f)
                        )

                    }
                }

                Box(
                    Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    if (homePageViewModel.isLoading) {
                        CircularProgressIndicator(
                            color = Color.Red,
                            strokeWidth = 3.dp,
                            modifier = Modifier.align(Alignment.Center)
                        )

                    } else {

                        LazyColumn {

                            items(1){

                            }

                            items(homePageViewModel.categoriesResponse){category ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp)
                                ) {
                                    Column {
                                        Row(verticalAlignment = Alignment.CenterVertically) {

                                            Box(Modifier.weight(0.2f)) {
                                                GlideImage(
                                                    model = category.strCategoryThumb,
                                                    contentDescription = "",
                                                    loading = placeholder(
                                                        R.drawable.ic_launcher_background
                                                    ),
                                                    modifier = Modifier.align(Alignment.Center)
                                                )
                                            }

                                            Column(Modifier.weight(0.8f)) {
                                                Text(
                                                    category.strCategory,
                                                    style = Constants.Texts.TITLE_TEXT
                                                )

                                                Text(
                                                    if (category.strCategoryDescription.length >= 70) category.strCategoryDescription.substring(
                                                        0,
                                                        70
                                                    ) + " .........." else category.strCategoryDescription,
                                                    style = Constants.Texts.HOME_RECIPE_SUB_TEXT
                                                )
                                            }

                                            Icon(Icons.Default.KeyboardArrowRight, "")

                                        }

                                        HorizontalDivider(
                                            thickness = 3.dp,
                                            color = Color.Black,
                                            modifier = Modifier.padding(top = 5.dp)
                                        )
                                    }
                                }
                            }




                        }

                    }


                }

            }
            FloatingActionButton(onClick = {
                navController.navigate("addRecipe")
            },Modifier.align(Alignment.BottomEnd).padding(10.dp)) { Icon(Icons.Default.Add,"") }

        }

    }



}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RecipeItem(category: Category){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.weight(0.2f)) {
                    GlideImage(
                        model = category.strCategoryThumb,
                        contentDescription = "",
                        loading = placeholder(
                            R.drawable.ic_launcher_background
                        ),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Column(Modifier.weight(0.8f)) {
                    Text(
                        category.strCategory,
                        style = Constants.Texts.TITLE_TEXT
                    )

                    Text(
                        if (category.strCategoryDescription.length >= 70) category.strCategoryDescription.substring(
                            0,
                            70
                        ) + " .........." else category.strCategoryDescription,
                        style = Constants.Texts.HOME_RECIPE_SUB_TEXT
                    )
                }

                Icon(Icons.Default.KeyboardArrowRight, "")

            }

            HorizontalDivider(
                thickness = 3.dp,
                color = Color.Black,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}



@Composable
fun DrawerItem() {
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

                ) {

                }

                Column(Modifier.padding(start = 10.dp)) {
                    Text("Tejiri Emoghene-Ijatomi", style = Constants.Texts.DRAWER_ITEM_TEXT)

                    Text("Logout", style = Constants.Texts.HOME_RECIPE_SUB_TEXT)
                }
            }

            LazyColumn(Modifier.weight(1f)) {
                items(1) {
                    Box(
                        Modifier
                            .padding(bottom = 15.dp)
                            .clip(RoundedCornerShape(50))

                            .background(Color.White)
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
                                    .size(35.dp)
                            )
                            Text("My Recipes", style = Constants.Texts.DRAWER_ITEM_TEXT)
                        }
                    }


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
                Text("Settings", style = Constants.Texts.TITLE_TEXT)
            }
        }
    }
}


@Composable
fun TestLayout() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column {

            Row(verticalAlignment = Alignment.CenterVertically) {
//        GlideImage()

                Box(Modifier.weight(0.2f)) {

                }

                Column(Modifier.weight(0.8f)) {
                    Text("Vegan", style = Constants.Texts.TITLE_TEXT)

                    Text("Vege Biryani", style = Constants.Texts.SUB_TITLE_TEXT)
                }

                Icon(Icons.Default.KeyboardArrowRight, "")

            }

            HorizontalDivider(
                thickness = 3.dp,
                color = Color.Black,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {

    DrawerItem()
//    TestLayout()
//    HomePage(rememberNavController(), innerPadding = PaddingValues(0.dp))
}