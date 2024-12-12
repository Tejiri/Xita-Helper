package com.xita.dailyhelper.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learn.customComponents.CustomFormComposables
import com.example.learn.customComponents.FixedPages
import com.example.learn.customComponents.UIComponents
import com.xita.dailyhelper.R
import com.xita.dailyhelper.constants.Constants
import com.xita.dailyhelper.services.FirebaseServices
import com.xita.dailyhelper.viewModels.LoginPageViewModel


@Composable
fun LoginPage(
    navController: NavController,
    innerPadding: PaddingValues,
    loginPageViewModel: LoginPageViewModel = viewModel()
) {


    if (FirebaseServices().checkUserLoggedIn() != null) {
        navController.navigate("home")
    }
    FixedPages().BasePage {

        LazyColumn(
            Modifier
                .fillMaxHeight()
                .padding(10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Text("Login", style = Constants.Texts.TITLE_TEXT)
            }
            items(1) {
                CustomFormComposables().CustomTextField(
                    onValueChange = { newEmail -> loginPageViewModel.onEmailChange(newEmail) },
                    placeHolder = { Text("Email") },
                    value = loginPageViewModel.email

                )

                CustomFormComposables().CustomTextField(
                    obscureText = true,
                    onValueChange = { newPassword -> loginPageViewModel.onPasswordChange(newPassword) },
                    placeHolder = { Text("Password") },
                    value = loginPageViewModel.password

                )

                CustomFormComposables().CustomButton(text = "Login", onClick = {

                    loginPageViewModel.logUserIn()


                }, isLoading = loginPageViewModel.isLoading)

                CustomFormComposables().CustomButton(
                    text = "Register",
                    transparent = true,
                    onClick = {
                        navController.navigate("signUp")
                    })


            }

            item {
                Row(
                    Modifier.padding(top = 20.dp, bottom = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        thickness = 2.dp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Text("or", modifier = Modifier.padding(start = 10.dp, end = 10.dp))
                    HorizontalDivider(
                        thickness = 2.dp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )

                }
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painterResource(R.drawable.facebook),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .height(50.dp)
                            .width(50.dp)
                            .clickable {

                            }
                    )

                    Image(
                        painterResource(R.drawable.google),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .height(50.dp)
                            .width(50.dp)
                            .clickable {

                            }
                    )

                    Image(
                        painterResource(R.drawable.phone),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .height(50.dp)
                            .width(50.dp)
                            .clickable {

                            }
                    )
                }
            }
        }
        if (loginPageViewModel.logInSuccess) {
            navController.navigate("home")
        }
        if (loginPageViewModel.error) {
            UIComponents().BottomAlertDialog(
                isError = true,
                title = "Error",
                message = loginPageViewModel.errorMessage,
                ondismiss = {
                    loginPageViewModel.error = false
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginPage() {
    LoginPage(rememberNavController(), PaddingValues(0.dp))
}