# 📊 TaskPrioritizer

**TaskPrioritizer** is an Android application built in Kotlin using Jetpack Compose.  
It helps users prioritize tasks based on weighted criteria such as benefit, urgency, complexity, and risk — using a simplified MCDA (Multi-Criteria Decision Analysis) approach.

---

## ✨ Features

- ✅ Create, edit, and delete tasks
- 📈 Tasks automatically ranked by priority score
- 🧮 Adjustable weights for individual criteria
- 🔍 Search and filter tasks by name
- 📤 Export task list to CSV or JSON format
- 🎨 Clean and intuitive UI using Jetpack Compose

---

## ⚠️ Note on Commits

Please note that during early development phases, some commit messages may be inconsistent or unclear.  
This reflects the iterative nature of the work and learning process, especially during initial experiments and prototyping.

---

## 🧪 Testing

Unit tests are included for:

- Title validation
- Duplicate task detection (including during editing)
- Weight sum validation (must equal 1)
- Presence check before allowing export

Tests are written using `kotlin.test` and can be found in the `ValidationUtilsTest` class.

---

## 🔧 Requirements & Setup

To run this project locally, follow the steps below:

### ✅ Requirements

- **Android Studio Hedgehog | Giraffe (2023.x) or newer**
- **Kotlin Multiplatform Plugin installed** (via Plugins tab in Android Studio)
- **Java 17+**
- **Gradle 8+** (bundled with Android Studio)
- Internet connection (for Gradle sync + dependency resolution)

### 📱 Recommended Emulator

- **Pixel 4 (API 30)**

---

## 🚀 Installation

1. **Clone the repository**
2. **Open the project in Android Studio**
3. **Wait for Gradle sync to complete**
4. **Setup emulator**

---

📤 Export Details

- Tasks can be exported via the UI (CSV / JSON)
- Files are saved to Downloads/ via Android's MediaStore API
- Export only activates when at least one task exists
