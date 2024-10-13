package com.example.statemanagementanduiupdates

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val itemViewModel: ItemViewModel by viewModels()
            ItemApp(itemViewModel)
        }
    }
}

@Composable
fun ItemApp(viewModel: ItemViewModel) {
    var newItem by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BasicTextField(
            value = newItem,
            onValueChange = { newItem = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Button(
            onClick = {
                viewModel.addItem(newItem.text)
                newItem = TextFieldValue("") // Clear input field
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Add Item")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(viewModel.items.size) { index ->
                val item = viewModel.items[index]
                var updatedItem by remember { mutableStateOf(TextFieldValue(item)) }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    BasicTextField(
                        value = updatedItem,
                        onValueChange = { updatedItem = it },
                        modifier = Modifier.weight(1f)
                    )

                    Row {
                        Button(
                            onClick = { viewModel.updateItem(index, updatedItem.text) },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text("Update")
                        }

                        Button(
                            onClick = { viewModel.removeItem(index) },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text("Delete")
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
