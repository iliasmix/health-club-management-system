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
