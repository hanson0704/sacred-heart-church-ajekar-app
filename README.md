# ⛪ Sacred Heart Ajekar Parish App

An Android application built to digitally connect parish members with the **Sacred Heart of Jesus Church, Ajekar**.
The app provides real-time parish updates, announcements, and important church information in a simple and modern interface.

---

## 📱 Features

### 👥 For Parish Members

* 📢 Latest parish announcements
* 🕊️ Obituary updates
* 📰 Parish bulletins
* ⛪ Mass timings
* 📍 Church contact details with:

    * Call button
    * Email button
    * Google Maps navigation
* 🖼️ Gallery (upcoming)
* 🔔 Push notifications for new updates

---

### 🔐 Admin Features (Priest Access)

* Firebase Authentication login
* Post:

    * Announcements
    * Obituaries
    * Bulletins
* Real-time Firestore updates
* Logout with session persistence
* Push notifications via Firebase Cloud Messaging

---

## 🧰 Tech Stack

### Frontend

* **Kotlin**
* **Jetpack Compose**
* Material 3 UI

### Backend

* **Firebase Firestore** – Data storage
* **Firebase Authentication** – Admin login
* **Firebase Cloud Messaging (FCM)** – Notifications
* **Firebase Cloud Functions** – Auto push alerts

---

## 🏗️ Architecture

* MVVM (Model–View–ViewModel)
* Repository Pattern
* Clean modular structure

```
ui → viewmodel → repository → firebase
```

---

## 📂 Project Structure

```
com.example.sacredheartajekar
│
├── about/           # About parish screens
├── admin/           # Admin panel & login
├── model/           # Data classes
├── viewmodel/       # MVVM logic
├── data/repository/ # Firebase repository
├── notification/    # FCM service
└── ui/theme/        # App theme
```

---

## 🔔 Notifications

* Auto push when admin posts update
* Topic-based FCM notifications
* Works in foreground and background

---

## 🚀 Future Improvements

* 📷 Parish gallery upload
* 🧾 Admin edit/delete posts
* ⏳ Auto-delete old announcements (48 hrs)
* 👥 Multi-admin support
* 🌐 Web admin dashboard

---

## 👨‍💻 Developed By

**Hanson Vaz**
MCA Student
Android Developer

---

## 📜 License

This project is developed for educational and parish use.
You are free to modify and adapt it for church communities.

---

## 🙏 Acknowledgement

Special thanks to the parish community of
**Sacred Heart of Jesus Church, Ajekar**
for inspiration and support.
