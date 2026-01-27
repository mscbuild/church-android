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

data class SermonTrack(
    val id: String,
    val title: String,
    val preacher: String,
    val date: String,
    val duration: String,
    val url: String
)

@Composable
fun MusicPlayerScreen() {
    var currentTrack by remember { mutableStateOf<SermonTrack?>(null) }
    var isPlaying by remember { mutableStateOf(false) }
    var currentPosition by remember { mutableStateOf(0f) }
    var duration by remember { mutableStateOf(100f) }
    
    val sermonTracks = remember {
        listOf(
            SermonTrack("1", "Сила веры", "Иван Петров", "15.01.2024", "45:30", "https://example.com/sermon1.mp3"),
            SermonTrack("2", "Молитва и ответ", "Мария Иванова", "22.01.2024", "38:15", "https://example.com/sermon2.mp3"),
            SermonTrack("3", "Путь к спасению", "Алексей Сидоров", "29.01.2024", "52:40", "https://example.com/sermon3.mp3"),
            SermonTrack("4", "Любовь и прощение", "Елена Козлова", "05.02.2024", "41:20", "https://example.com/sermon4.mp3"),
            SermonTrack("5", "Вера в действии", "Петр Николаев", "12.02.2024", "47:55", "https://example.com/sermon5.mp3"),
            SermonTrack("6", "Божье руководство", "Анна Смирнова", "19.02.2024", "44:10", "https://example.com/sermon6.mp3")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Проповеди",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Плеер
        currentTrack?.let { track ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = track.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = track.preacher,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Прогресс-бар
                    Column {
                        Slider(
                            value = currentPosition,
                            onValueChange = { currentPosition = it },
                            valueRange = 0f..duration,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = formatTime(currentPosition),
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = track.duration,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Кнопки управления
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { /* Предыдущий трек */ }
                        ) {
                            Icon(
                                imageVector = Icons.Default.SkipPrevious,
                                contentDescription = "Предыдущий",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        
                        IconButton(
                            onClick = { isPlaying = !isPlaying },
                            modifier = Modifier.size(64.dp)
                        ) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = if (isPlaying) "Пауза" else "Воспроизведение",
                                modifier = Modifier.size(48.dp)
                            )
                        }
                        
                        IconButton(
                            onClick = { /* Следующий трек */ }
                        ) {
                            Icon(
                                imageVector = Icons.Default.SkipNext,
                                contentDescription = "Следующий",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }
            }
        }
        
        // Список проповедей
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sermonTracks) { track ->
                SermonTrackItem(
                    track = track,
                    isPlaying = currentTrack?.id == track.id && isPlaying,
                    onClick = { 
                        currentTrack = track
                        isPlaying = true
                    }
                )
            }
        }
    }
}

@Composable
fun SermonTrackItem(
    track: SermonTrack,
    isPlaying: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Иконка воспроизведения
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = if (isPlaying) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = track.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "${track.preacher} • ${track.date}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Text(
                text = track.duration,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun formatTime(seconds: Float): String {
    val totalSeconds = seconds.toInt()
    val minutes = totalSeconds / 60
    val remainingSeconds = totalSeconds % 60
    return String.format("%d:%02d", minutes, remainingSeconds)
}