package com.church.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class BibleBook(
    val name: String,
    val chapters: Int,
    val testament: String // "Ветхий Завет" or "Новый Завет"
)

data class Chapter(
    val number: Int,
    val verses: List<String>
)

@Composable
fun BibleScreen() {
    var selectedTestament by remember { mutableStateOf("Ветхий Завет") }
    var selectedBook by remember { mutableStateOf<BibleBook?>(null) }
    
    val oldTestamentBooks = remember {
        listOf(
            BibleBook("Бытие", 50, "Ветхий Завет"),
            BibleBook("Исход", 40, "Ветхий Завет"),
            BibleBook("Левит", 27, "Ветхий Завет"),
            BibleBook("Числа", 36, "Ветхий Завет"),
            BibleBook("Второзаконие", 34, "Ветхий Завет"),
            BibleBook("Иисус Навин", 24, "Ветхий Завет"),
            BibleBook("Судьи", 21, "Ветхий Завет"),
            BibleBook("Руфь", 4, "Ветхий Завет"),
            BibleBook("1-я Царств", 31, "Ветхий Завет"),
            BibleBook("2-я Царств", 24, "Ветхий Завет"),
            BibleBook("Псалтирь", 150, "Ветхий Завет"),
            BibleBook("Притчи", 31, "Ветхий Завет"),
            BibleBook("Исаия", 66, "Ветхий Завет"),
            BibleBook("Иеремия", 52, "Ветхий Завет"),
            BibleBook("Иезекииль", 48, "Ветхий Завет"),
            BibleBook("Даниил", 12, "Ветхий Завет")
        )
    }
    
    val newTestamentBooks = remember {
        listOf(
            BibleBook("Матфея", 28, "Новый Завет"),
            BibleBook("Марка", 16, "Новый Завет"),
            BibleBook("Луки", 24, "Новый Завет"),
            BibleBook("Иоанна", 21, "Новый Завет"),
            BibleBook("Деяния", 28, "Новый Завет"),
            BibleBook("Римлянам", 16, "Новый Завет"),
            BibleBook("1-я Коринфянам", 16, "Новый Завет"),
            BibleBook("2-я Коринфянам", 13, "Новый Завет"),
            BibleBook("Галатам", 6, "Новый Завет"),
            BibleBook("Ефесянам", 6, "Новый Завет"),
            BibleBook("Филиппийцам", 4, "Новый Завет"),
            BibleBook("Колоссянам", 4, "Новый Завет"),
            BibleBook("Откровение", 22, "Новый Завет")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Библия",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { selectedTestament = "Ветхий Завет" },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedTestament == "Ветхий Завет") 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.surface
                )
            ) {
                Text("Ветхий Завет")
            }
            
            Button(
                onClick = { selectedTestament = "Новый Завет" },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedTestament == "Новый Завет") 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.surface
                )
            ) {
                Text("Новый Завет")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        if (selectedBook == null) {
            val books = if (selectedTestament == "Ветхий Завет") oldTestamentBooks else newTestamentBooks
            
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(books) { book ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clickable { selectedBook = book }
                        ) {
                            Text(
                                text = book.name,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${book.chapters} глав",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        } else {
            BookContent(
                book = selectedBook!!,
                onBack = { selectedBook = null }
            )
        }
    }
}

@Composable
fun BookContent(book: BibleBook, onBack: () -> Unit) {
    var selectedChapter by remember { mutableStateOf(1) }
    
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.ArrowBack,
                    contentDescription = "Назад"
                )
            }
            Text(
                text = book.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(book.chapters) { chapterNumber ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable { selectedChapter = chapterNumber }
                    ) {
                        Text(
                            text = "Глава $chapterNumber",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Нажмите чтобы прочитать",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}