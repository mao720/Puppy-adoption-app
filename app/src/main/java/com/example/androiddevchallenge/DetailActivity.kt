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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imageResId = intent.getIntExtra("imageResId", R.mipmap.puppy_01)
        val name = intent.getStringExtra("name")
        setContent {
            MyTheme {
                MyDetail(imageResId, name)
            }
        }
    }
}

@Composable
fun MyDetail(imageResId: Int, name: String?) {
    Surface(color = MaterialTheme.colors.background) {
        Column {
            TopAppBar(
                title = {
                    Text(
                        text = "Puppy Detail",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
            Row {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = name,
                    Modifier
                        .width(180.dp)
                        .padding(30.dp, 30.dp)
                )
                Text(
                    text = "Name : $name\r\nAge : 3\r\nAddress : Eskimo",
                    Modifier.padding(top = 30.dp)
                )
            }
            Text(
                text = "Dogs are sometimes referred to as \"man's best friend\" because they are kept as domestic pets and are usually loyal and like being around humans. Dogs like to be petted, but only when they can first see the petter's hand before petting; one should never pet a dog from behind.\n" +
                    "\n" +
                    "August 26 is National Dog Day. While March 26 is National Puppy Day.",
                Modifier.padding(horizontal = 30.dp, vertical = 30.dp),
                style = MaterialTheme.typography.body2
            )
        }
    }
}
