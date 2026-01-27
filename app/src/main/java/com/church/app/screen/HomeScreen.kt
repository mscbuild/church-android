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

data class Sermon(
    val title: String,
    val preacher: String,
    val date: String,
    val duration: String
)

data class Event(
    val title: String,
    val date: String,
    val time: String,
    val location: String
)

@Composable
fun HomeScreen() {
    val upcomingSermons = remember {
        listOf(
            Sermon("Сила веры", "Иван Петров", "28 января", "45 мин"),
            Sermon("Молитва и ответ", "Мария Иванова", "29 января", "50 мин"),
            Sermon("Путь к спасению", "Алексей Сидоров", "30 января", "40 мин")
        )
    }
    
    val upcomingEvents = remember {
        listOf(
            Event("Воскресная служба", "28 января", "10:00", "Главный зал"),
            Event("Молодежное собрание", "29 января", "18:00", "Молодежный центр"),
            Event("Библейская группа", "30 января", "19:00", "Комната 201")
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Добро пожаловать!",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
        
        item {
            Text(
                text = "Предстоящие проповеди",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        
        items(upcomingSermons) { sermon ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = sermon.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Проповедник: ${sermon.preacher}")
                    Text(text = "Дата: ${sermon.date}")
                    Text(text = "Длительность: ${sermon.duration}")
                }
            }
        }
        
        item {
            Text(
                text = "Ближайшие события",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        
        items(upcomingEvents) { event ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = event.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Дата: ${event.date}")
                    Text(text = "Время: ${event.time}")
                    Text(text = "Место: ${event.location}")
                }
            }
        }
    }
}