package com.example.learn.customComponents

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xita.dailyhelper.ui.theme.Purple40

class CustomFormComposables {

    @Composable
    fun CustomTextField(
        onValueChange: (value: String) -> Unit,
        placeHolder: @Composable () -> Unit,
        value: String,
        obscureText: Boolean = false
    ) {
        OutlinedTextField(
            visualTransformation = if (obscureText) PasswordVisualTransformation() else VisualTransformation.None,
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(45),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),

            placeholder = placeHolder,
        )
    }

    @Composable
    fun CustomButton(
        text: String = "",
        onClick: () -> Unit = {},
        isLoading: Boolean = false,
        transparent:Boolean = false
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
                .clip(RoundedCornerShape(40))
                .background(if (transparent) Color.Transparent else Purple40, shape = RoundedCornerShape(40))
                .border(3.dp, Purple40, RoundedCornerShape(40))
                .padding(12.dp)
                .clickable(onClick = onClick),

            ) {

            if (isLoading) CircularProgressIndicator(
                modifier = Modifier
                    .align(alignment = Alignment.Center), color = Color.White
            ) else Text(
                text,
                color = if (transparent) Purple40 else Color.White,
                modifier = Modifier
                    .align(alignment = Alignment.Center),
                style = TextStyle(fontSize = 25.sp)
            )
        }
    }
}