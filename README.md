Project Title: Food-Finder & Hotel Management System
​📌 Overview
​A robust Java Swing-based Desktop Application designed to bridge the gap between hotel managers and customers. This project implements a dual-interface system allowing hotel owners to manage their digital menus and customers to discover, sort, and rate dishes in real-time.
​🚀 Key Features
​Dual-User Authentication: Separate portals for Hotel Management and Customers with secure sign-up and sign-in flows.
​Secure Credential Management: Implements SHA-256 Hashing to protect user passwords, ensuring that sensitive data is never stored in plain text.
​Interactive GUI: Built using Java Swing and AWT, featuring dynamic components like JTable, JComboBox, and custom JDialog windows for a smooth user experience.
​Advanced Data Operations:
​Sorting Algorithms: Includes Merge Sort implementations to sort menu items by price or customer rating.
​Persistent Storage: Uses File I/O to maintain user credentials and hotel data across sessions.
​Multithreaded Architecture: Utilizes the Runnable interface to handle sign-in/sign-up processes on separate threads, keeping the UI responsive.
​🛠️ Tech Stack
​Language: Java
​GUI Framework: Swing, AWT
​Security: java.security.MessageDigest (SHA-256)
​Data Structure/Logic: Merge Sort, File I/O (BufferedReader/PrintWriter)
​📂 File Structure Highlights
​security/: Contains UsignIn.java and UsignUp.java for handling authentication and hashing.
​gui/: Contains all visual components like Ulobby.java (the main user dashboard) and Usigningui.java.
​customer/ & hotel/: Data models representing the primary entities of the system.
​test/: Contains TestMenu.java, the main entry point of the application.
​How to Use This on Your Profile
​Repository Name: Hotel-Management-System-Java or Java-Swing-FoodApp.
​License: Consider adding an MIT License.
​Future Enhancements: Mention that you plan to migrate the file-based storage to a SQL Database (like MySQL or PostgreSQL) to demonstrate database management skills.Hotel-menu-management
