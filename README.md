# ⛪ Sacred Heart Ajekar Parish App

An Android application built to digitally connect parish members with the **Sacred Heart of Jesus Church, Ajekar**.
The app delivers real-time parish updates, events, and media in a clean, modern, and user-friendly interface.

---

## 📱 Features

### 👥 For Parish Members

* 📢 Latest parish announcements (real-time)
* 🕊️ Obituary updates
* 📰 Parish bulletins
* 📅 Upcoming parish events
* ⛪ Mass timings
* 📍 Church contact details with:

    * Call button
    * Email button
    * Google Maps navigation
* 🖼️ Interactive Gallery:

    * Grid view with titles
    * Full-screen slideshow (swipe left/right)
* 🔔 Push notifications for new updates

---

### 🔐 Admin Features (Priest Access)

* Firebase Authentication login
* Secure admin session (no repeated login until logout)
* Post:

    * Announcements
    * Obituaries
    * Bulletins
    * Events
    * Gallery updates (multiple images per post)
* Edit & Delete:

    * News updates
    * Events
    * Gallery items
* Real-time updates using Firestore
* Logout functionality
* Auto push notifications via Firebase Cloud Messaging

---

## 🧰 Tech Stack

### Frontend

* **Kotlin**
* **Jetpack Compose**
* **Material 3 UI**
* Modern UI components (TopAppBar, Cards, Grid, Pager)

### Backend

* **Firebase Firestore** – Real-time database
* **Firebase Authentication** – Admin access control
* **Firebase Storage** – Image uploads (gallery)
* **Firebase Cloud Messaging (FCM)** – Notifications
* **Firebase Cloud Functions** – Auto notification trigger

---

## 🏗️ Architecture

* MVVM (Model–View–ViewModel)
* Repository Pattern
* Clean modular architecture

```
UI → ViewModel → Repository → Firebase
```

---

## 📂 Project Structure

```
com.example.sacredheartajekar
│
├── about/             # About parish screens
├── admin/             # Admin login & admin panel
├── model/             # Data models (News, Events, Gallery)
├── viewmodel/         # Business logic (MVVM)
├── data/repository/   # Firebase interaction layer
├── notification/      # FCM service
├── ui/theme/          # App theme
└── screens/           # Home, News, Events, Gallery
```

---

## 🖼️ Gallery Highlights

* Grid layout with titles
* Full-screen image viewer
* Swipeable slideshow (HorizontalPager)
* Admin delete control
* Multiple images per gallery post

---

## 🔔 Notifications

* Automatic push when admin posts updates
* Uses FCM topic subscription
* Works in foreground & background
* Cloud Functions trigger notifications

---

## 🚀 Future Improvements

* ⏳ Auto-delete announcements after 48 hours
* ✏️ Advanced edit UI for all modules
* 👥 Role-based admin (multiple admins)
* 🌐 Web-based admin dashboard
* 🎨 UI animations & premium transitions
* 📊 Analytics for user engagement

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

Special thanks to **Rev. Fr Henry Mascarenhas** the parish priest of
**Sacred Heart of Jesus Church, Ajekar**
for inspiration and support along with my project guide
**Dr. Spoorthi Shetty, Department of MCA, NMAMIT NITTE**.
