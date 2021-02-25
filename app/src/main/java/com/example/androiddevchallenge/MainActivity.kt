/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

val imageArray = intArrayOf(
    R.mipmap.puppy_01,
    R.mipmap.puppy_02,
    R.mipmap.puppy_03,
    R.mipmap.puppy_04,
    R.mipmap.puppy_05,
    R.mipmap.puppy_06,
    R.mipmap.puppy_07,
    R.mipmap.puppy_01,
    R.mipmap.puppy_02,
    R.mipmap.puppy_03,
    R.mipmap.puppy_04,
    R.mipmap.puppy_05,
    R.mipmap.puppy_06,
    R.mipmap.puppy_07,
)

val nameArray = arrayOf(
    "AaKa",
    "BaKa",
    "CaKa",
    "DaKa",
    "EaKa",
    "FaKa",
    "GaKa",
    "KaAa",
    "KaBa",
    "KaCa",
    "KaDa",
    "KaEa",
    "KaFa",
    "KaGa",
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp { imageResId, name -> gotoDetail(imageResId, name) }
            }
        }
    }

    private fun gotoDetail(imageResId: Int, name: String) {
        startActivity(
            Intent(this, DetailActivity::class.java)
                .putExtra("imageResId", imageResId)
                .putExtra("name", name)
        )
    }
}

// Start building your app here!
@Composable
fun MyApp(onClick: (imageResId: Int, name: String) -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Column {
            TopAppBar(
                title = {
                    Text(
                        text = "Puppy Adoption",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
            LazyColumn(state = rememberLazyListState()) {
                items(imageArray.size) {
                    ListItem(it, onClick)
                }
            }
        }
    }
}

@Composable
fun ListItem(index: Int, onClick: (imageResId: Int, name: String) -> Unit) {
    Row(
        Modifier
            .height(100.dp)
            .padding(top = 2.dp)
            .fillMaxWidth(1f)
            .background(color = Color.LightGray)
            .clickable { onClick(imageArray[index], nameArray[index]) }
    ) {
        Surface(
            modifier = Modifier
                .size(50.dp)
                .offset(20.dp)
                .align(Alignment.CenterVertically),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Image(
                painter = painterResource(id = imageArray[index]),
                contentDescription = ("" + imageArray[index])
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 40.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(nameArray[index], fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("$index minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp { _, _ -> }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp { _, _ -> }
    }
}
