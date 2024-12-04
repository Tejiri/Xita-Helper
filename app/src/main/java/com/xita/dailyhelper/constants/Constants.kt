package com.xita.dailyhelper.constants

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.xita.dailyhelper.ui.theme.Purple40

class Constants {
    object Texts {
        val TITLE_TEXT = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold)
        val SUB_TITLE_TEXT = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Purple40)
        val HOME_RECIPE_SUB_TEXT = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
        val DRAWER_ITEM_TEXT = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}