package com.xita.dailyhelper.pages

import android.net.Uri
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.learn.customComponents.FixedPages
import com.xita.dailyhelper.R
import com.xita.dailyhelper.services.FirebaseServices

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AddRecipePage(navController: NavHostController, innerPaddingValues: PaddingValues) {


    BackHandler {

    }

  FixedPages().PageWithAppBarAndDrawer(navController) {
      var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
      val pickMedia =
          rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->

              // Callback is invoked after the user selects a media item or closes the
              // photo picker.
              if (uri != null) {
                  selectedImageUri = uri
                  Log.d("PhotoPicker", "Selected URI: $uri")
              } else {
                  Log.d("PhotoPicker", "No media selected")
              }
          }

      Column(Modifier.fillMaxSize().background(Color.Gray)) {

          Box(Modifier.weight(0.3f)) {
              GlideImage(
                  selectedImageUri,
                  "",
                  loading = placeholder(R.drawable.ic_launcher_background),
                  contentScale = ContentScale.FillBounds,
                  modifier = Modifier.fillMaxWidth().height(300.dp).background(Color.Red)
              )
//
          }

          Box(Modifier.weight(0.7f)) {  }
      }

  }
}

//}
//             Button(onClick = {
//              // Registers a photo picker activity launcher in single-select mode.
//
//
//// Include only one of the following calls to launch(), depending on the types
//// of media that you want to let the user choose from.
//
//// Launch the photo picker and let the user choose images and videos.
////            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
//
//// Launch the photo picker and let the user choose only images.
//              pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//
//// Launch the photo picker and let the user choose only videos.
////            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
////
////// Launch the photo picker and let the user choose only images/videos of a
////// specific MIME type, such as GIFs.
////            val mimeType = "image/gif"
////            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.SingleMimeType(mimeType)))
//          }) { }

//          if (selectedImageUri != null) {
//
//          } else {
//              Text("No Image", color = Color.Black)
//          }
//
//          Button(onClick = {
//              FirebaseServices().addRecipe(selectedImageUri)
//          }) { }
//      }
//  }
//}

@Preview(showBackground = true)
@Composable
fun AddRecipePreview() {
    AddRecipePage(rememberNavController(), PaddingValues(0.dp))
}