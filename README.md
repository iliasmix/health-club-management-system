# Health Club Management System 🏋️‍♀️💪

## Description 📝
The **Health Club Management System** is a Java-based application designed to help health clubs manage their operations. It includes features for tracking members, assigning coaches, and managing subscriptions. The system does not use a database but instead uses text files for storing data. 📂

The system includes:
- **Admin functionalities**: Add new members, assign coaches, and manage subscriptions.
- **Member functionalities**: Add, update, and display member details.
- **Coach functionalities**: Track which members are assigned to which coaches.
- **Subscription management**: Track and manage membership status. 📅

## Features ✨
- **Admin Features:**
  - 🆕 Add new members.
  - 🏋️‍♀️ Assign coaches to members.
  - 💳 Manage subscriptions (add, update, delete).

- **Member Features:**
  - ✍️ Add, update, or display member data.
  - 📅 Track subscription end dates.

- **Coach Features:**
  - 🧑‍🏫 Assign multiple members to coaches.
  - 📋 Display coach schedules (could be added in future versions).

- **File-based storage**: Data is stored in text files (e.g., `MemberData.txt`, `CoachData.txt`, `AdminData.txt`). 📄

## Folder Structure 📁
The project follows this folder structure:
``` scss
health-club-management-system/
├── src/                         # Source code folder
│   ├── Admin.java                 # Admin class for managing admin-related tasks.
│   ├── Coach.java                 # Coach class for managing coach-related tasks.
│   ├── Member.java                # Member class for managing member-related tasks.
│   ├── Subscription.java          # Class for managing subscriptions.
│   ├── HealthClubSystem.java      # Main system management class.
│   ├── FileHandler.java           # Handles reading and writing to text files.
│   └── Main.java                  # Entry point for the application.
└── resources/                   # Folder for data files
    ├── MemberData.txt             # Text file for storing member information.
    ├── CoachData.txt              # Text file for storing coach information.
    └── AdminData.txt              # Text file for storing admin data.
```

## **Description of the Folder Structure:**

### **1. `src/`** — Source Code Folder 🖥️
- **Admin.java**: Contains methods and properties related to admin functionalities (e.g., adding members, managing subscriptions).
- **Coach.java**: Manages coach-related tasks, such as assigning coaches to members and maintaining coach data.
- **Member.java**: Defines member details, including adding and displaying member information, as well as managing subscription statuses.
- **Subscription.java**: Handles the logic of tracking subscriptions, including start dates, renewal, and status management.
- **HealthClubSystem.java**: This is the central control point of the system that integrates the various components (Admin, Member, Coach, Subscription).
- **FileHandler.java**: Responsible for reading and writing data to text files to persist member, coach, and admin information.
- **Main.java**: The starting point of the application. It initializes the program and runs the primary logic.

### **2. `resources/`** — Data Storage Files 💾
- **MemberData.txt**: A text file that stores member information, such as names, subscription statuses, and expiration dates.
- **CoachData.txt**: A text file for storing coach data, including details about each coach and their assigned members.
- **AdminData.txt**: A file to store admin-related information, such as login credentials and system configurations.

## Why This Structure?
- **Separation of Concerns**: By organizing the code into logical sections (Admin, Member, Coach, etc.), it's easier to manage and maintain.
- **File-Based Storage**: Using text files for data storage keeps the project simple and lightweight, avoiding the complexity of a database setup.
- **Easy Navigation**: Clear folder structure helps developers easily locate and modify specific files.

This folder structure ensures your project is scalable, maintainable, and easy to understand for both current and future contributors. 🚀


## Technologies 🛠️
- **Programming Language**: Java ☕
- **Storage**: Text files for storing member, coach, and admin data (no database). 📂
- **Libraries/Tools**: Standard Java libraries for file I/O and system operations.


## Getting Started 🚀

### Prerequisites 🖥️
- Java Development Kit (JDK) version 8 or higher installed.
- A GitHub account (for version control and collaboration).

### Installation 🔧
1. Clone the repository:
   ```bash
   git clone https://github.com/DORMODO/health-club-management-system.git
   ```
