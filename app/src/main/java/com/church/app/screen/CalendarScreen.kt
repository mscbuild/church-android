package com.church.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

data class ChurchEvent(
    val id: String,
    val title: String,
    val description: String,
    val date: Date,
    val time: String,
    val location: String,
    val eventType: EventType
)

enum class EventType {
    WORSHIP, BIBLE_STUDY, YOUTH, PRAYER, SPECIAL
}

@Composable
fun CalendarScreen() {
    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }
    var showDatePicker by remember { mutableStateOf(false) }
    
    val events = remember {
        listOf(
            ChurchEvent(
                "1", 
                "Воскресная служба", 
                "Еженедельное богослужение с проповедью и поклонением",
                Calendar.getInstance().apply {
                    set(Calendar.DAY_OF_MONTH, 28)
                    set(Calendar.MONTH, Calendar.JANUARY)
                }.time,
                "10:00",
                "Главный зал",
                EventType.WORSHIP
            ),
            ChurchEvent(
                "2",
                "Молодежное собрание",
                "Встреча для молодежи с обсуждением актуальных тем",
                Calendar.getInstance().apply {
                    set(Calendar.DAY_OF_MONTH, 29)
                    set(Calendar.MONTH, Calendar.JANUARY)
                }.time,
                "18:00",
                "Молодежный центр",
                EventType.YOUTH
            ),
            ChurchEvent(
                "3",
                "Библейская группа",
                "Изучение Писаний в малых группах",
                Calendar.getInstance().apply {
                    set(Calendar.DAY_OF_MONTH, 30)
                    set(Calendar.MONTH, Calendar.JANUARY)
                }.time,
                "19:00",
                "Комната 201",
                EventType.BIBLE_STUDY
            ),
            ChurchEvent(
                "4",
                "Вечер молитвы",
                "Совместная молитва за нужды церкви и прихожан",
                Calendar.getInstance().apply {
                    set(Calendar.DAY_OF_MONTH, 31)
                    set(Calendar.MONTH, Calendar.JANUARY)
                }.time,
                "20:00",
                "Молитвенный зал",
                EventType.PRAYER
            ),
            ChurchEvent(
                "5",
                "Конференция",
                "Ежегодная церковная конференция с приглашенными спикерами",
                Calendar.getInstance().apply {
                    set(Calendar.DAY_OF_MONTH, 3)
                    set(Calendar.MONTH, Calendar.FEBRUARY)
                }.time,
                "09:00",
                "Главный зал",
                EventType.SPECIAL
            )
        )
    }
    
    // Фильтруем события по выбранной дате
    val filteredEvents = events.filter { event ->
        val eventCalendar = Calendar.getInstance().apply {
            time = event.date
        }
        eventCalendar.get(Calendar.DAY_OF_MONTH) == selectedDate.get(Calendar.DAY_OF_MONTH) &&
        eventCalendar.get(Calendar.MONTH) == selectedDate.get(Calendar.MONTH) &&
        eventCalendar.get(Calendar.YEAR) == selectedDate.get(Calendar.YEAR)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Календарь событий",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Выбор даты
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        selectedDate.add(Calendar.DAY_OF_MONTH, -1)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Предыдущий день"
                    )
                }
                
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = SimpleDateFormat("EEEE", Locale("ru")).format(selectedDate.time),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = SimpleDateFormat("d MMMM yyyy", Locale("ru")).format(selectedDate.time),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                IconButton(
                    onClick = {
                        selectedDate.add(Calendar.DAY_OF_MONTH, 1)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Следующий день"
                    )
                }
            }
            
            Button(
                onClick = { showDatePicker = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text("Выбрать дату")
            }
        }
        
        // Список событий на выбранную дату
        Text(
            text = "События на день (${filteredEvents.size})",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (filteredEvents.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = null,
                                modifier = Modifier.size(48.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Нет событий на этот день",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            } else {
                items(filteredEvents) { event ->
                    EventCard(event = event)
                }
            }
        }
    }
    
    // Диалог выбора даты (заглушка)
    if (showDatePicker) {
        AlertDialog(
            onDismissRequest = { showDatePicker = false },
            title = { Text("Выбрать дату") },
            text = { Text("Здесь будет календарь для выбора даты") },
            confirmButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("OK")
                }
            }
        )
    }
}

@Composable
fun EventCard(event: ChurchEvent) {
    val (icon, iconColor) = when (event.eventType) {
        EventType.WORSHIP -> Icons.Default.Church to MaterialTheme.colorScheme.primary
        EventType.BIBLE_STUDY -> Icons.Default.Book to MaterialTheme.colorScheme.secondary
        EventType.YOUTH -> Icons.Default.Group to MaterialTheme.colorScheme.tertiary
        EventType.PRAYER -> Icons.Default.PrayerChant to MaterialTheme.colorScheme.primary
        EventType.SPECIAL -> Icons.Default.Star to MaterialTheme.colorScheme.secondary
    }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = iconColor
                )
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = event.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Text(
                        text = event.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = event.time,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        
                        Spacer(modifier = Modifier.width(16.dp))
                        
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = event.location,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}