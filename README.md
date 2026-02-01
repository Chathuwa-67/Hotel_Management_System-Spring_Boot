# Hotel Management System - Spring Boot

A comprehensive Hotel Management System built with Spring Boot, Thymeleaf, JPA, and MySQL.

## Features

### Visitor Features
- 🏠 **Home Page**: View all available rooms with modern card-based design
- 🛏️ **Room Booking**: Book rooms with guest information
- 📅 **Date Selection**: Select check-in and check-out dates
- 💰 **Automatic Price Calculation**: Total price calculated based on number of nights
- ✅ **Booking Confirmation**: Receive booking confirmation with details

### Admin Features
- 🔐 **Secure Login**: Admin authentication (Username: admin, Password: admin123)
- 📊 **Dashboard**: Overview of system with quick actions
- 🛏️ **Room Management**: Full CRUD operations for rooms
  - Add new rooms
  - Edit room details
  - Delete rooms
  - View all rooms with status
- 📅 **Booking Management**: Full CRUD operations for bookings
  - View all bookings
  - Edit booking details
  - Cancel bookings
  - Delete bookings
- 👥 **Guest Management**: Manage guest information
  - View all guests
  - View guest booking history
  - Delete guests

## Technology Stack

- **Backend**: Spring Boot 3.2.1
- **Frontend**: Thymeleaf, HTML5, CSS3
- **Database**: MySQL 8.0
- **ORM**: Spring Data JPA (Hibernate)
- **Build Tool**: Maven
- **Java Version**: 17

## Prerequisites

Before running this application, ensure you have the following installed:

1. **Java Development Kit (JDK) 17 or higher**
   - Download from: https://www.oracle.com/java/technologies/downloads/
   - Verify installation: `java -version`

2. **Apache Maven 3.6 or higher**
   - Download from: https://maven.apache.org/download.cgi
   - Verify installation: `mvn -version`

3. **MySQL Server 8.0 or higher**
   - Download from: https://dev.mysql.com/downloads/mysql/
   - Verify installation: `mysql --version`

4. **IDE (Optional but recommended)**
   - IntelliJ IDEA, Eclipse, or VS Code with Java extensions

## Database Setup

### Step 1: Create Database

1. Open MySQL Command Line Client or MySQL Workbench
2. Login with your MySQL credentials (default: root with no password)
3. Execute the following SQL command:

```sql
CREATE DATABASE hotel_management_db;
```

4. Verify database creation:
```sql
SHOW DATABASES;
```

You should see `hotel_management_db` in the list.

### Step 2: Configure Database Connection (If Different from Default)

If your MySQL credentials are different from the default (root with no password), edit the `application.properties` file:

**Location**: `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_management_db
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

## Installation & Running Instructions

### Method 1: Using IDE (Recommended for Development)

#### IntelliJ IDEA:
1. Open IntelliJ IDEA
2. Click `File` → `Open`
3. Navigate to the `hotel-management-system` folder and select it
4. Wait for Maven to download dependencies (check bottom right corner)
5. Locate `HotelManagementApplication.java` in `src/main/java/com/hotel/management/`
6. Right-click the file and select `Run 'HotelManagementApplication'`
7. Wait for the application to start (you'll see "Started HotelManagementApplication" in console)
8. Open browser and go to: `http://localhost:8080`

#### Eclipse:
1. Open Eclipse
2. Click `File` → `Import` → `Maven` → `Existing Maven Projects`
3. Browse to the `hotel-management-system` folder
4. Click `Finish` and wait for dependencies to download
5. Right-click on project → `Run As` → `Spring Boot App`
6. Open browser and go to: `http://localhost:8080`

### Method 2: Using Command Line

1. **Navigate to Project Directory**:
```bash
cd path/to/hotel-management-system
```

2. **Build the Project**:
```bash
mvn clean install
```

3. **Run the Application**:
```bash
mvn spring-boot:run
```

Or alternatively:
```bash
java -jar target/hotel-management-system-0.0.1-SNAPSHOT.jar
```

4. **Access the Application**:
- Open your web browser
- Go to: `http://localhost:8080`

## Application URLs

### Visitor (Public) Pages:
- **Home Page**: `http://localhost:8080/`
- **Book Room**: `http://localhost:8080/book/{roomId}`

### Admin Pages:
- **Admin Login**: `http://localhost:8080/admin/login`
  - **Username**: `admin`
  - **Password**: `admin123`
- **Dashboard**: `http://localhost:8080/admin/dashboard`
- **Manage Rooms**: `http://localhost:8080/admin/rooms`
- **Manage Bookings**: `http://localhost:8080/admin/bookings`
- **Manage Guests**: `http://localhost:8080/admin/guests`

## Initial Setup & Testing

### Step 1: Add Sample Rooms (Admin)

1. Login as admin
2. Go to "Rooms" → "Add New Room"
3. Add sample rooms with following details:

**Room 1:**
- Room Number: 101
- Room Type: Single
- Floor: 1
- Capacity: 1
- Price per Night: 5000
- Status: Available
- Description: Cozy single room with AC and WiFi

**Room 2:**
- Room Number: 201
- Room Type: Double
- Floor: 2
- Capacity: 2
- Price per Night: 8000
- Status: Available
- Description: Comfortable double room with city view

**Room 3:**
- Room Number: 301
- Room Type: Suite
- Floor: 3
- Capacity: 4
- Price per Night: 15000
- Status: Available
- Description: Luxurious suite with separate living area

### Step 2: Test Booking (Visitor)

1. Go to home page: `http://localhost:8080/`
2. Click "Book Now" on any available room
3. Fill in guest details and booking dates
4. Submit the booking
5. You'll receive a confirmation page with booking details

### Step 3: Verify Booking (Admin)

1. Login to admin panel
2. Go to "Bookings"
3. You should see your test booking
4. Go to "Guests" to see the guest information
5. Click "View History" to see guest's booking history

## Project Structure

```
hotel-management-system/
├── src/
│   ├── main/
│   │   ├── java/com/hotel/management/
│   │   │   ├── HotelManagementApplication.java    # Main application class
│   │   │   ├── controller/
│   │   │   │   ├── AdminController.java           # Admin authentication
│   │   │   │   ├── RoomController.java            # Room CRUD operations
│   │   │   │   ├── BookingController.java         # Booking CRUD operations
│   │   │   │   ├── GuestController.java           # Guest management
│   │   │   │   └── VisitorController.java         # Public visitor pages
│   │   │   ├── entity/
│   │   │   │   ├── Room.java                      # Room entity
│   │   │   │   ├── Booking.java                   # Booking entity
│   │   │   │   └── Guest.java                     # Guest entity
│   │   │   ├── repository/
│   │   │   │   ├── RoomRepository.java            # Room data access
│   │   │   │   ├── BookingRepository.java         # Booking data access
│   │   │   │   └── GuestRepository.java           # Guest data access
│   │   │   └── service/
│   │   │       ├── RoomService.java               # Room business logic
│   │   │       ├── BookingService.java            # Booking business logic
│   │   │       └── GuestService.java              # Guest business logic
│   │   └── resources/
│   │       ├── application.properties              # Configuration
│   │       ├── static/css/
│   │       │   └── style.css                      # Modern CSS styling
│   │       └── templates/
│   │           ├── visitor/
│   │           │   ├── home.html                  # Home page with room cards
│   │           │   ├── booking-form.html          # Booking form
│   │           │   └── booking-success.html       # Confirmation page
│   │           └── admin/
│   │               ├── login.html                 # Admin login
│   │               ├── dashboard.html             # Admin dashboard
│   │               ├── rooms.html                 # Room list
│   │               ├── room-form.html             # Add/Edit room
│   │               ├── bookings.html              # Booking list
│   │               ├── booking-edit.html          # Edit booking
│   │               ├── guests.html                # Guest list
│   │               └── guest-history.html         # Guest booking history
└── pom.xml                                         # Maven dependencies
```

## Database Schema

The application automatically creates the following tables:

### rooms
- id (Primary Key, Auto Increment)
- room_number (Unique)
- room_type (Single/Double/Suite)
- price_per_night (Decimal)
- floor (Integer)
- capacity (Integer)
- status (Available/Booked)
- description (Text)

### guests
- id (Primary Key, Auto Increment)
- name (VARCHAR)
- email (VARCHAR)
- phone (VARCHAR)
- address (TEXT)

### bookings
- id (Primary Key, Auto Increment)
- room_id (Foreign Key → rooms)
- guest_id (Foreign Key → guests)
- check_in_date (DATE)
- check_out_date (DATE)
- number_of_guests (Integer)
- total_price (Decimal)
- status (Confirmed/Pending/Cancelled)
- booking_date (DATE)

## Key Features Explained

### Automatic Price Calculation
- System automatically calculates total price based on:
  - Number of nights (check-out date - check-in date)
  - Room's price per night
  - Formula: `Total = Number of Nights × Price per Night`

### Room Status Management
- When a booking is created, room status changes to "Booked"
- When a booking is cancelled/deleted, room status changes to "Available"
- Prevents double-booking of rooms

### Guest History Tracking
- System maintains complete booking history for each guest
- Admins can view all past and current bookings for any guest
- Guest information is linked to all their bookings

### Responsive Design
- Modern, gradient-based UI
- Card-based layout for rooms
- Fully responsive (works on mobile, tablet, desktop)
- Smooth hover effects and transitions

## Troubleshooting

### Issue: Application won't start

**Solution 1**: Check if MySQL is running
```bash
# Windows
net start MySQL80

# Linux/Mac
sudo service mysql start
```

**Solution 2**: Verify database exists
```sql
SHOW DATABASES;
```

**Solution 3**: Check application.properties credentials

### Issue: Port 8080 is already in use

**Solution**: Change port in `application.properties`:
```properties
server.port=8081
```

### Issue: Maven dependencies not downloading

**Solution**:
```bash
mvn clean install -U
```

### Issue: Lombok errors

**Solution**: Install Lombok plugin in your IDE
- IntelliJ: File → Settings → Plugins → Search "Lombok"
- Eclipse: Download lombok.jar and run it

### Issue: Can't login to admin

**Solution**: Verify credentials:
- Username: `admin` (lowercase, no spaces)
- Password: `admin123` (no spaces)

## API Endpoints (For Testing)

### Room API
- GET `/admin/rooms/api/all` - Get all rooms
- GET `/admin/rooms/api/available` - Get available rooms
- GET `/admin/rooms/api/{id}` - Get room by ID

### Booking API
- GET `/admin/bookings/api/all` - Get all bookings
- GET `/admin/bookings/api/{id}` - Get booking by ID
- GET `/admin/bookings/api/guest/{guestId}` - Get bookings by guest

### Guest API
- GET `/admin/guests/api/all` - Get all guests
- GET `/admin/guests/api/{id}` - Get guest by ID

## Future Enhancements (Optional)

- Email notifications for booking confirmations
- Payment gateway integration
- Room availability calendar
- Advanced search and filtering
- Report generation (PDF)
- Multi-language support
- User registration for visitors
- Review and rating system

## Support

For any issues or questions:
1. Check the troubleshooting section
2. Verify all prerequisites are installed
3. Ensure MySQL is running and database is created
4. Check application logs in the console

## License

This project is for educational purposes.

## Author

Hotel Management System - Spring Boot Application

---

**Enjoy managing your hotel! 🏨**
