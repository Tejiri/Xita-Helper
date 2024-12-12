package com.xita.dailyhelper.pages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learn.customComponents.CustomFormComposables
import com.example.learn.customComponents.FixedPages
import com.example.learn.customComponents.UIComponents
import com.xita.dailyhelper.R
import com.xita.dailyhelper.constants.Constants
import com.xita.dailyhelper.viewModels.SignUpPageViewModel

@Composable
fun SignUpPage(
    navController: NavController,
    innerPadding: PaddingValues,
    signUpPageViewModel: SignUpPageViewModel = viewModel()
) {
    FixedPages().BasePage {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(10.dp), verticalArrangement = Arrangement.Center
        ) {

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Box(Modifier.padding(0.dp)) {
                        Icon(
                            Icons.Default.KeyboardArrowLeft,
                            contentDescription = "",
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                                .padding(0.dp).clickable {
                                    navController.popBackStack()
                                },
                        )

                    }
                    Text("Sign Up", style = Constants.Texts.TITLE_TEXT)
                }
            }


            item {
                Text(
                    "Get the most out of your favourite recipe app",
                    modifier = Modifier.fillMaxWidth(0.9f),
                    style = Constants.Texts.SUB_TITLE_TEXT
                )
            }

            items(1) {

                CustomFormComposables().CustomTextField(
                    value = signUpPageViewModel.name,
                    onValueChange = { name -> signUpPageViewModel.onNameChange(name) },
                    placeHolder = { Text("Name") })
                CustomFormComposables().CustomTextField(
                    value = signUpPageViewModel.email,
                    onValueChange = { email -> signUpPageViewModel.onEmailChange(email) },
                    placeHolder = { Text("Email") })
                CustomFormComposables().CustomTextField(
                    value = signUpPageViewModel.password,
                    onValueChange = { password -> signUpPageViewModel.onPasswordChange(password) },
                    placeHolder = { Text("Password") },
                    obscureText = true
                )
                CustomFormComposables().CustomTextField(
                    value = signUpPageViewModel.repeatPassword,
                    onValueChange = { repeatPassword ->
                        signUpPageViewModel.onRepeatPasswordChange(
                            repeatPassword
                        )
                    },
                    placeHolder = { Text("Repeat Password") },
                    obscureText = true
                )
            }

            item {

                CustomFormComposables().CustomButton(
                    text = "Sign Up",
                    onClick = { signUpPageViewModel.submitForm()
//                              navController.navigate("home")
                              },
                    isLoading = signUpPageViewModel.isLoading
                )
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

        if (signUpPageViewModel.error) {
            UIComponents().BottomAlertDialog(
                isError = true,
                title = "Error",
                message = signUpPageViewModel.errorMessage,
                ondismiss = {
                    signUpPageViewModel.error = false
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignUpPagePreview() {
//    BottomSheetDialog()
    SignUpPage(rememberNavController(),innerPadding = PaddingValues(0.dp), viewModel())
}