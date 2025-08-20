# 📚 BetterReads

BetterReads is a modern reimagining of [Goodreads](https://www.goodreads.com/).  
It’s a full-stack web application built with **Spring Boot** (backend) and **React** (frontend), designed to help users discover, review, and share books in a clean, modern interface.

---

## 🚀 Features

- 🔍 **Search & Discover**: Browse books using a public book API.
- ✍️ **User Reviews**: Write, edit, and share book reviews.
- ⭐ **Ratings**: Rate books and see average community ratings.
- 👤 **Profiles**: Each user has their own bookshelf & review board.
- 🗂 **Collections**: Organize books into custom lists (e.g. “To Read”, “Favorites”).
- 💬 **Community**: Comment on and engage with other readers’ reviews.

---

## 🛠 Tech Stack

**Frontend**  
- ⚛️ React (Vite / CRA)  
- TailwindCSS or ShadCN for styling  
- React Router for navigation  

**Backend**  
- ☕ Spring Boot (Java)  
- RESTful API with JWT authentication  
- PostgreSQL (primary database)  

**DevOps / Infra** (future plans)  
- Docker & Kubernetes for containerization and scaling  
- AWS / GCP / Azure for deployment  

---

---

## ⚡ Getting Started

### Prerequisites
- [Java 17+](https://adoptium.net/)  
- [Node.js 18+](https://nodejs.org/)  
- [PostgreSQL](https://www.postgresql.org/)  

### Backend Setup
```bash
cd backend
./mvnw spring-boot:run
```
Backend will run at: http://localhost:8080

### Frontend Setup
```bash
cd frontend
npm install
npm run dev
```
Frontend will run at: http://localhost:5173

