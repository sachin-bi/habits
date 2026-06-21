# Habit Tracker KMP

A modern **Kotlin Multiplatform (KMP)** Habit Tracking application built using **Compose Multiplatform**, **Voyager Navigation**, **Koin Dependency Injection**, and a **Clean Architecture + MVVM** approach.

The project shares business logic, state management, and UI across Android and iOS while allowing platform-specific implementations when required.

---

## 📱 Features

* Create and manage habits
* Daily habit tracking
* Weekly progress visualization
* Clean and scalable architecture
* Shared UI for Android & iOS
* Dependency Injection with Koin
* Navigation using Voyager
* Local persistence using SQLDelight/Room
* Kotlin Multiplatform support

---

## 🏗️ Architecture

The project follows:

* **Clean Architecture**
* **MVVM Pattern**
* **Repository Pattern**
* **Use Case Driven Business Logic**
* **Dependency Injection (Koin)**
* **Voyager ScreenModel**

```
Presentation Layer
        │
        ▼
    Use Cases
        │
        ▼
 Repository Interface
        │
        ▼
 Repository Implementation
        │
        ▼
 Database / Local Storage
```

---

## 📂 Project Structure

```
composeApp/src/

├── commonMain/
│   ├── di/
│   ├── domain/
│   ├── data/
│   └── presentation/
│
├── androidMain/
│   ├── database/
│   ├── di/
│   └── MainActivity.kt
│
└── iosMain/
    ├── database/
    ├── di/
    └── MainViewController.kt
```

---

## 📦 Layer Breakdown

### Domain Layer

Contains pure business logic and has no dependency on frameworks.

#### Models

```kotlin
Habit.kt
HabitLog.kt
```

#### Repository Contracts

```kotlin
HabitRepository.kt
```

#### Use Cases

```kotlin
ObserveHabitsUseCase.kt
ToggleHabitUseCase.kt
```

Responsibilities:

* Business rules
* Data contracts
* Application use cases

---

### Data Layer

Handles data storage and repository implementations.

#### Database

```kotlin
DatabaseDriverFactory.kt
HabitDatabase.sq
```

#### Repository Implementation

```kotlin
HabitRepositoryImpl.kt
```

#### Mappers

```kotlin
HabitMapper.kt
```

Responsibilities:

* Database communication
* Entity mapping
* Repository implementation

---

### Presentation Layer

Contains all shared Compose UI.

#### Theme

```kotlin
Color.kt
Typography.kt
AppTheme.kt
```

#### Components

Reusable UI components:

```kotlin
HabitRowItem.kt
WeeklyCalendarGrid.kt
```

#### Screens

Each feature contains:

```kotlin
Screen
ScreenModel
UiState
```

Example:

```
daily/
├── DailyTrackerScreen.kt
├── DailyTrackerScreenModel.kt
└── DailyTrackerUiState.kt
```

Responsibilities:

* UI rendering
* State management
* User interactions

---

## 🔄 State Flow

```
UI
 ↓
ScreenModel
 ↓
Use Case
 ↓
Repository
 ↓
Database
 ↓
Repository
 ↓
ScreenModel
 ↓
UI State Update
```

---

## 🧩 Dependency Injection

Koin is used for dependency management.

### Shared Modules

```kotlin
KoinModules.kt
```

Registers:

* Repositories
* Use Cases
* ScreenModels

### Platform Modules

Android:

```kotlin
androidMain/di/ActualModule.kt
```

iOS:

```kotlin
iosMain/di/ActualModule.kt
```

---

## 🧭 Navigation

Voyager is used for navigation.

Example:

```kotlin
Navigator(DailyTrackerScreen())
```

Each screen implements:

```kotlin
Screen
```

and its corresponding

```kotlin
ScreenModel
```

for business logic.

---

## 💾 Database

The application supports local persistence using:

### Option 1: SQLDelight (Recommended)

Benefits:

* Shared database code
* Type-safe SQL
* Android + iOS support

### Option 2: Room (Android only)

Suitable if iOS persistence is not required.

---

## 🚀 Getting Started

### Prerequisites

* Android Studio Narwhal or newer
* Kotlin 2.x
* JDK 17+
* Xcode (for iOS)
* CocoaPods (if required)

---

### Clone Project

```bash
git clone https://github.com/yourusername/habit-tracker-kmp.git

cd habit-tracker-kmp
```

---

### Run Android

```bash
./gradlew :composeApp:installDebug
```

or directly from Android Studio.

---

### Run iOS

Open:

```bash
iosApp/iosApp.xcodeproj
```

Run the project from Xcode Simulator.

---

## 🛠️ Tech Stack

| Category         | Technology            |
| ---------------- | --------------------- |
| Language         | Kotlin                |
| UI               | Compose Multiplatform |
| Architecture     | Clean Architecture    |
| DI               | Koin                  |
| Navigation       | Voyager               |
| Database         | SQLDelight            |
| Async            | Kotlin Coroutines     |
| Reactive Streams | Flow                  |
| State Management | StateFlow             |
| Platform Support | Android, iOS          |

---

## 📈 Future Enhancements

* Habit reminders
* Streak tracking
* Monthly analytics
* Cloud synchronization
* Dark mode customization
* Wear OS support
* Widget support
* AI-powered habit suggestions

---

## 🤝 Contributing

Contributions are welcome.

1. Fork the repository
2. Create a feature branch
3. Commit changes
4. Push the branch
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License.

---

## 👨‍💻 Author

Built with Kotlin Multiplatform, Compose Multiplatform, and Clean Architecture principles.


---

This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop (JVM).



* [/iosApp](./iosApp/iosApp) contains an iOS application. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* [/shared](./shared/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - [commonMain](./shared/src/commonMain/kotlin) is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
      For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
      the [iosMain](./shared/src/iosMain/kotlin) folder would be the right place for such calls.
      Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./shared/src/jvmMain/kotlin)
      folder is the appropriate location.

### Running the apps

Use the run configurations provided by the run widget in your IDE's toolbar. You can also use these commands and
options:

- Android app: `./gradlew :androidApp:assembleDebug`
- Desktop app:
    - Hot reload: `./gradlew :desktopApp:hotRun --auto`
    - Standard run: `./gradlew :desktopApp:run`
- Web app:
    - Wasm target (faster, modern browsers): `./gradlew :webApp:wasmJsBrowserDevelopmentRun`
    - JS target (slower, supports older browsers): `./gradlew :webApp:jsBrowserDevelopmentRun`
- iOS app: open the [/iosApp](./iosApp) directory in Xcode and run it from there.

### Running tests

Use the run button in your IDE's editor gutter, or run tests using Gradle tasks:

- Android tests: `./gradlew :shared:testAndroidHostTest`
- Desktop tests: `./gradlew :shared:jvmTest`
- Web tests:
    - Wasm target: `./gradlew :shared:wasmJsTest`
    - JS target: `./gradlew :shared:jsTest`
- iOS tests: `./gradlew :shared:iosSimulatorArm64Test`

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack
channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [YouTrack](https://youtrack.jetbrains.com/newIssue?project=CMP).