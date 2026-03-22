# Церковное приложение
 ![](https://komarev.com/ghpvc/?username=mscbuild) 
 [![Author](https://img.shields.io/badge/Author-Yuri%20Dev-blue.svg)](http://mscbuild.github.io/)
 ![](https://img.shields.io/github/license/mscbuild/church-android) 
 ![](https://img.shields.io/github/repo-size/mscbuild/church-android)
![](https://img.shields.io/badge/PRs-Welcome-green)
![](https://img.shields.io/badge/code%20style-kotlin-green)
![](https://img.shields.io/github/stars/mscbuild)
![](https://img.shields.io/badge/Topic-Github-lighred)
![](https://img.shields.io/website?url=https%3A%2F%2Fgithub.com%2Fmscbuild)

Android приложение для церкви с функциями:
- **Библия**: Полный список книг Ветхого и Нового Завета
- **MP3 плеер**: Прослушивание проповедей и религиозной музыки
- **Календарь**: Церковные события и расписание служб
- **Главная**: Предстоящие проповеди и события

## Структура проекта

```
ChurchApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/church/app/
│   │   │   ├── MainActivity.kt
│   │   │   ├── navigation/
│   │   │   │   └── ChurchNavigation.kt
│   │   │   ├── screens/
│   │   │   │   ├── HomeScreen.kt
│   │   │   │   ├── BibleScreen.kt
│   │   │   │   ├── MusicPlayerScreen.kt
│   │   │   │   └── CalendarScreen.kt
│   │   │   ├── services/
│   │   │   │   └── MusicPlayerService.kt
│   │   │   └── ui/theme/
│   │   │       ├── Theme.kt
│   │   │       └── Type.kt
│   │   ├── res/
│   │   │   ├── values/
│   │   │   │   ├── strings.xml
│   │   │   │   ├── colors.xml
│   │   │   │   └── themes.xml
│   │   │   └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
```

## Основные функции

### 📖 Библия
- Разделение на Ветхий и Новый Завет
- Список всех книг с количеством глав
- Навигация по главам
- Удобный интерфейс для чтения

### 🎵 MP3 плеер
- Воспроизведение проповедей
- Управление воспроизведением
- Отображение текущего трека
- Фоновое воспроизведение

### 📅 Календарь событий
- Церковные мероприятия
- Расписание служб
- Фильтрация по дате
- Информация о времени и месте

### 🏠 Главная страница
- Предстоящие проповеди
- Ближайшие события
- Быстрая навигация

## Технологии

- **Kotlin** - основной язык программирования
- **Jetpack Compose** - современный UI фреймворк
- **Navigation Component** - навигация между экранами
- **Material Design 3** - современный дизайн
- **Android Architecture Components** - ViewModel, LiveData
- **Media Player API** - воспроизведение аудио

## Требования

- Android SDK 24 (Android 7.0) и выше
- Kotlin 1.9.10+
- Android Studio Hedgehog | 2023.1.1 или новее

## Установка и запуск

1. Клонируйте репозиторий
2. Откройте проект в Android Studio
3. Синхронизируйте Gradle
4. Запустите на эмуляторе или устройстве

## Лицензия

Проект разработан для образовательных целей.
